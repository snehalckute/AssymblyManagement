/**
 * 
 */
package com.assembly.management.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.assembly.management.bean.RawMaterialHolder;
import com.assembly.management.bean.RequestBean;
import com.assembly.management.bean.ResponseBean;
import com.assembly.management.config.AssemblyConfig;
import com.assembly.management.config.AssemblyConfig.Material;
import com.assembly.management.controller.AssemblyController;
import com.assembly.management.exception.AssemblyException;
import com.assembly.management.service.IAssemblyService;

/**
 * Service class for the assembly Service
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
@Service
public class AssemblyService implements IAssemblyService {

	@Autowired
	private TaskExecutor workerExecutor;

	@Autowired
	private Queue<Material> conveyer;

	@Autowired
	private Map<Integer, RawMaterialHolder> rawMaterialMapping;

	Logger logger = LoggerFactory.getLogger(AssemblyController.class);

	private static final String ERROR_CODE = "1";
	private static final String ERROR_MESSAGE = "Erro occured - ";

	/**
	 * This method picks RAw material from Conveyer belt
	 * 
	 * @param material
	 * @param rawMaterialHolder
	 * @return
	 */
	private Material pickRawMaterial(Material material,
			RawMaterialHolder rawMaterialHolder) {
		rawMaterialHolder.addRawMaterial(material);
		return conveyer.poll();
	}

	/**
	 * This material Checks if the required material is available on Belt
	 * 
	 * @return
	 */
	private Material checkRawMaterial() {
		return conveyer.peek();
	}

	/**
	 * This method assembles a product
	 * 
	 * @param assemblyTime
	 * @throws InterruptedException
	 */
	private void assembleProduct(Integer assemblyTime)
			throws InterruptedException {
		Thread.sleep(new Long(assemblyTime * 1000));
	}

	/**
	 * This method processes all product Processing
	 * 
	 * @param machines
	 * @param bolts
	 * @param assemblyTime
	 * @return
	 * @throws InterruptedException
	 */
	public ResponseBean processProduct(RequestBean requestBean) {

		logger.info("START - processProduct");
		ResponseBean responseBean = new ResponseBean();
		if (requestBean != null && requestBean.getTotalMachines() != null
				&& requestBean.getTotalBolts() != null
				&& requestBean.getTimeToAssemble() != null) {
			Integer machines = requestBean.getTotalMachines();
			Integer bolts = requestBean.getTotalBolts();
			Integer assemblyTime = requestBean.getTimeToAssemble();

			AssemblyConfig.countDownLatch = new CountDownLatch(machines);

			Date startTime = new Date();
			buildConveyer(machines, bolts);

			for (int i = 0; i < machines; i++) {
				assemble(assemblyTime);
				responseBean.setTotalProducts(i + 1);
			}

			try {
				AssemblyConfig.countDownLatch.await();
			} catch (InterruptedException e) {
				responseBean.setCode(ERROR_CODE);
				responseBean.setMessage(ERROR_MESSAGE + e.getMessage());
				return responseBean;
			}
			Long timeTaken = ((new Date()).getTime() - startTime.getTime()) / 1000;
			responseBean.setTotalTimeTaken(timeTaken);
		} else {
			new AssemblyException(null, ERROR_CODE, ERROR_MESSAGE);
		}
		logger.info("END - processProduct");

		return responseBean;
	}

	/**
	 * Starts assembly of single product
	 * 
	 * @param id
	 * @param assemblyTime
	 * @return
	 * @throws AssemblyException
	 */
	private boolean startAssembly(Integer id, Integer assemblyTime)
			throws AssemblyException {

		boolean successFlag = false;
		boolean isRawMaterialPicked = false;
		try {
			RawMaterialHolder rawMaterialHolder = rawMaterialMapping.get(id);
			for (Material material : rawMaterialHolder.getRequiredMaterial()) {
				if (material.equals(checkRawMaterial())) {
					Material materialPicked = pickRawMaterial(material,
							rawMaterialHolder);
					logger.info("Material Picked by Id : " + id + " is : "
							+ materialPicked);
					isRawMaterialPicked = true;
					break;
				}
			}

			if (isRawMaterialPicked && rawMaterialHolder.isRawMaterialReady()) {
				assembleProduct(assemblyTime);
				successFlag = true;
			}
		} catch (InterruptedException e) {
			throw new AssemblyException(e, ERROR_CODE, ERROR_MESSAGE
					+ "startAssembly");
		}
		return successFlag;
	}

	/**
	 * This method builds conveyer belt
	 * 
	 * @param machines
	 * @param bolt
	 */
	private void buildConveyer(Integer machines, Integer bolts) {
		logger.info("START - buildConveyer");
		Integer count = 0;
		for (int i = 0; i < machines; i++) {
			if (count <= bolts) {
				conveyer.add(Material.MACHINE);
				conveyer.add(Material.BOLT);
				conveyer.add(Material.BOLT);
			}
		}
		logger.info("END - buildConveyer");
	}

	/**
	 * Starts new thred for assembly
	 * 
	 * @param assemblyTime
	 */
	private void assemble(Integer assemblyTime) {

		workerExecutor.execute(new Runnable() {
			@Override
			public void run() {

				boolean isProductReady = false;
				Integer id = AssemblyConfig.populateNextVal();
				logger.info("START - assemble - for ID : " + id);
				RawMaterialHolder rawMaterialHolder = new RawMaterialHolder(id);
				rawMaterialMapping.put(id, rawMaterialHolder);
				while (!isProductReady) {
					try {
						isProductReady = startAssembly(id, assemblyTime);
					} catch (AssemblyException e) {
						logger.error(e.getErrorCode() + " - "
								+ e.getErrorMessage() + " - " + e.getMessage());
					}
				}
				AssemblyConfig.countDownLatch.countDown();
				logger.info("END - assemble for ID : " + id);
			}
		});
	}

}

/**
 * 
 */
package com.assembly.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assembly.management.bean.RequestBean;
import com.assembly.management.bean.ResponseBean;
import com.assembly.management.service.IAssemblyService;

/**
 * Assembly controller
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
@RestController
public class AssemblyController {

	@Autowired
	IAssemblyService assemblyService;

	Logger logger = LoggerFactory.getLogger(AssemblyController.class);

	/**
	 * Operation calculates time and number of products built
	 * 
	 * @param machines
	 * @param bolts
	 * @param assemblyTime
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/assemble", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	ResponseBean manageAssembly(@RequestBody RequestBean requestBean)
			throws InterruptedException {
		logger.info("START - Process");
		return assemblyService.processProduct(requestBean);
	}

}

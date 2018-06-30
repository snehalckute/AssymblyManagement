/**
 * 
 */
package com.assembly.management.bean;

import java.util.ArrayList;
import java.util.List;

import com.assembly.management.config.AssemblyConfig.Material;

/**
 * This class holds Raw material condition for one worker
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
public class RawMaterialHolder {

	private boolean isRawMaterialReady = false;
	private Integer id;
	private List<Material> requiredMaterialList;

	/**
	 * Constructor for this class
	 * 
	 * @param nextVal
	 */
	public RawMaterialHolder(Integer nextVal) {
		requiredMaterialList = new ArrayList<Material>();
		requiredMaterialList.add(Material.MACHINE);
		requiredMaterialList.add(Material.BOLT);
		requiredMaterialList.add(Material.BOLT);
		id = nextVal;
	}

	/**
	 * Populates list of required material for a product
	 * 
	 * @return
	 */
	public List<Material> getRequiredMaterial() {
		return requiredMaterialList;
	}

	/**
	 * Add Picked material for one worker
	 * 
	 * @param materialPicked
	 * @return
	 */
	public boolean addRawMaterial(Material materialPicked) {
		boolean isAdded = false;
		if (requiredMaterialList != null && requiredMaterialList.size() > 0) {
			for (Material material : requiredMaterialList) {
				if (material.name().equalsIgnoreCase(materialPicked.name())) {
					requiredMaterialList.remove(material);
					isAdded = true;
					break;
				}
			}
		}
		if (requiredMaterialList != null && requiredMaterialList.size() == 0) {
			isRawMaterialReady = true;
		}
		return isAdded;
	}

	/**
	 * Is raw material ready indicator
	 * 
	 * @return
	 */
	public boolean isRawMaterialReady() {
		return isRawMaterialReady;
	}

	/**
	 * Populate Id of the product being assembled
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}
}

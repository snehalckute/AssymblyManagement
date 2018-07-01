/**
 * 
 */
package com.assembly.management.bean;

/**
 * This is the request bean for the Operation
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
public class RequestBean {

	private Integer totalMachines;
	private Integer totalBolts;
	private Integer timeToAssemble;
	/**
	 * @return the totalMachines
	 */
	public Integer getTotalMachines() {
		return totalMachines;
	}
	/**
	 * @param totalMachines the totalMachines to set
	 */
	public void setTotalMachines(Integer totalMachines) {
		this.totalMachines = totalMachines;
	}
	/**
	 * @return the totalBolts
	 */
	public Integer getTotalBolts() {
		return totalBolts;
	}
	/**
	 * @param totalBolts the totalBolts to set
	 */
	public void setTotalBolts(Integer totalBolts) {
		this.totalBolts = totalBolts;
	}
	/**
	 * @return the timeToAssemble
	 */
	public Integer getTimeToAssemble() {
		return timeToAssemble;
	}
	/**
	 * @param timeToAssemble the timeToAssemble to set
	 */
	public void setTimeToAssemble(Integer timeToAssemble) {
		this.timeToAssemble = timeToAssemble;
	}

}

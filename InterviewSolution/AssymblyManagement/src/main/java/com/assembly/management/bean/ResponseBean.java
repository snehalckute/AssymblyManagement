/**
 * 
 */
package com.assembly.management.bean;

/**
 * This is the response bean for the Operation
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
public class ResponseBean {

	private int totalProducts;
	private Long totalTimeTaken;

	/**
	 * @return the totalProducts
	 */
	public int getTotalProducts() {
		return totalProducts;
	}

	/**
	 * @param totalProducts
	 *            the totalProducts to set
	 */
	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	/**
	 * @return the totalTimeTaken
	 */
	public Long getTotalTimeTaken() {
		return totalTimeTaken;
	}

	/**
	 * @param totalTimeTaken
	 *            the totalTimeTaken to set
	 */
	public void setTotalTimeTaken(Long totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}

}

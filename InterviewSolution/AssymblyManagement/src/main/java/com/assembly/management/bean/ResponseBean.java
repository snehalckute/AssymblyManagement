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

	private static final String SUCCESS_CODE="0";
	private int totalProducts;
	private Long totalTimeTaken;
	private String code=SUCCESS_CODE;
	private String message="SUCCESS";
	

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

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

/**
 * 
 */
package com.assembly.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assembly Exception
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
public class AssemblyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	
	Logger logger = LoggerFactory.getLogger(AssemblyException.class);

	public AssemblyException(Exception e, String errorCode, String errorMessage){
		super (e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		logger.error("Errro Occured while processing the request - Code : "+errorCode+" - Message : "+errorMessage);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}

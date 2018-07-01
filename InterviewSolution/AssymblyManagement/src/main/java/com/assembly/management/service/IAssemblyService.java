/**
 * 
 */
package com.assembly.management.service;

import com.assembly.management.bean.RequestBean;
import com.assembly.management.bean.ResponseBean;

/**
 * Service class for the assembly Service
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
public interface IAssemblyService {

	/**
	 * This method processes all product Processing
	 * 
	 * @param machines
	 * @param bolts
	 * @param assemblyTime
	 * @return
	 * @throws InterruptedException
	 */
	public ResponseBean processProduct(RequestBean requestBean);
}

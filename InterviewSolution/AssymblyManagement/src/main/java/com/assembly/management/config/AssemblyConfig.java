/**
 * 
 */
package com.assembly.management.config;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.assembly.management.bean.RawMaterialHolder;

/**
 * This class holds configuration of the application
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
@Configuration
public class AssemblyConfig {

	/**
	 * Application level counter
	 */
	private static int nextVal = 0;
	
	private static final String TASK_EXECUTOR_THREAD = "TASK_EXECUTOR_THREAD";

	/**
	 * Application level countdown latch
	 */
	public static CountDownLatch countDownLatch;

	/**
	 * Conveyer Queue
	 * 
	 * @return
	 */
	@Bean
	public Queue<Material> assemblyConveyer() {
		return new ConcurrentLinkedQueue<Material>();
	}

	/**
	 * Task Executor bean
	 * 
	 * @return
	 */
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor rExecutor = new ThreadPoolTaskExecutor();
		rExecutor.setCorePoolSize(3);
		rExecutor.setMaxPoolSize(3);
		rExecutor.setThreadNamePrefix(TASK_EXECUTOR_THREAD);
		rExecutor.initialize();
		return rExecutor;
	}

	/**
	 * Material Enum for product
	 *
	 */
	public enum Material {
		MACHINE, BOLT;
	}

	/**
	 * Product Id to raw material holder map
	 * 
	 * @return
	 */
	@Bean
	public Map<Integer, RawMaterialHolder> materialMapping() {
		return new ConcurrentHashMap<Integer, RawMaterialHolder>();
	}

	/**
	 * counter on application level
	 * 
	 * @return
	 */
	public static int populateNextVal() {
		nextVal++;
		return nextVal;
	}
}

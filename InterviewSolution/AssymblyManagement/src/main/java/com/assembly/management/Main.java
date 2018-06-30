package com.assembly.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Class contains starter Main method for this application
 * 
 * @author Snehal Kute
 * @since 30 June 2018
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Main {

	/**
	 * Starter spring boot method main()
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}
}

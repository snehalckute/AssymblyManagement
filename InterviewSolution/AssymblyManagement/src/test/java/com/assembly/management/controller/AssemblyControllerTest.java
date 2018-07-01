/**
 * 
 */
package com.assembly.management.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.assembly.management.Main;
import com.assembly.management.bean.RequestBean;
import com.assembly.management.bean.ResponseBean;

/**
 * @author Snehooooo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AssemblyControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Autowired
	private AssemblyController controller;

	Logger logger = LoggerFactory.getLogger(AssemblyControllerTest.class);

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/AssemblyManagement" + uri;
	}

	/**
	 * Test method for
	 * {@link com.assembly.management.controller.AssemblyController#home(java.lang.Integer, java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testManageAssembly() {

		RequestBean course = new RequestBean();
		course.setTimeToAssemble(10);
		course.setTotalBolts(6);
		course.setTotalMachines(3);

		HttpEntity<RequestBean> entity = new HttpEntity<RequestBean>(course,
				headers);

		ResponseEntity<ResponseBean> response = restTemplate.exchange(
				createURLWithPort("/assemble"), HttpMethod.POST, entity,
				ResponseBean.class);

		ResponseBean resp = response.getBody();
		Assert.assertEquals("0", resp.getCode());
		Assert.assertEquals("SUCCESS", resp.getMessage());
		Assert.assertEquals(3, resp.getTotalProducts());
		Assert.assertEquals(new Long(10), resp.getTotalTimeTaken());

		logger.info(" Total Products = " + resp.getTotalProducts());
		logger.info(" Total Time Taken = " + resp.getTotalTimeTaken());

	}

	/**
	 * Test method for
	 * {@link com.assembly.management.controller.AssemblyController#home(java.lang.Integer, java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testManageAssemblyNegative() {

		RequestBean course = new RequestBean();
		course.setTimeToAssemble(10);
		course.setTotalBolts(null);
		course.setTotalMachines(3);

		HttpEntity<RequestBean> entity = new HttpEntity<RequestBean>(course,
				headers);

		ResponseEntity<ResponseBean> response = restTemplate.exchange(
				createURLWithPort("/assemble"), HttpMethod.POST, entity,
				ResponseBean.class);

		ResponseBean resp = response.getBody();
		Assert.assertEquals("0", resp.getCode());
		Assert.assertEquals("SUCCESS", resp.getMessage());

	}
}

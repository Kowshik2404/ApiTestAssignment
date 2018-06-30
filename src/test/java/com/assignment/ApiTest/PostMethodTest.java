package com.assignment.ApiTest;

import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.beans.Customer;

/**
 * The Class PostMethodTest.
 */
public class PostMethodTest {

	/**
	 * Adds the space.
	 */
	@BeforeMethod
	public void addSpace() {
		System.out.println();
		System.out.println();
	}

	/**
	 * Success status test.
	 *
	 * @throws Exception the exception
	 */
	@Test(priority = 1)
	public void successStatusTest() throws Exception {

		System.out.println("**************************Test1**************************");
		System.out.println("Perform test to check status '201'");
		ApiExecutor executApi = new ApiExecutor();
		System.out.println("Send request with correct url");
		Customer customer = new Customer();
		customer.setName("name");
		customer.setNationality("Nation");
		CloseableHttpResponse httpResponse = executApi.postMethod("/customer", "application/json; charset=utf8",
				customer, null);
		System.out.println("Verify if status code is '201'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
		System.out.println("**************************Ends**************************");
	}

	/**
	 * Resource not found test.
	 *
	 * @throws Exception the exception
	 */
	@Test(priority = 2)
	public void resourceNotFoundTest() throws Exception {

		System.out.println("**************************Test2**************************");
		System.out.println("Perform test to check status '404'");
		ApiExecutor executApi = new ApiExecutor();
		System.out.println("Send request with incorrect url");
		Customer customer = new Customer();
		customer.setName("name");
		customer.setNationality("Nation");
		CloseableHttpResponse httpResponse = executApi.postMethod("/customers", "application/json; charset=utf8",
				customer, null);
		System.out.println("Verify if status code is '404'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
		System.out.println("**************************Ends**************************");
	}

	/**
	 * Unsupported media test.
	 *
	 * @throws Exception the exception
	 */
	@Test(priority = 3)
	public void unsupportedMediaTest() throws Exception {

		System.out.println("**************************Test2**************************");
		System.out.println("Perform test to check status '415'");
		ApiExecutor executApi = new ApiExecutor();
		System.out.println("Send request with correct url and unsupported media type");
		Customer customer = new Customer();
		customer.setName("name");
		customer.setNationality("Nation");
		CloseableHttpResponse httpResponse = executApi.postMethod("/customer", "application/xml; charset=utf8",
				customer, null);
		System.out.println("Verify if status code is '415'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
		System.out.println("**************************Ends**************************");
	}

	/**
	 * Success with auth test.
	 *
	 * @throws Exception the exception
	 */
	@Test(priority = 4)
	public void successWithAuthTest() throws Exception {

		System.out.println("**************************Test2**************************");
		System.out.println("Perform test to check status '201'");
		ApiExecutor executApi = new ApiExecutor();
		System.out.println("Send request with correct url and authentication");
		Customer customer = new Customer();
		customer.setName("name");
		customer.setNationality("Nation");

		CloseableHttpResponse httpResponse = executApi.postMethod("/customer", "application/json; charset=utf8",
				customer, new UsernamePasswordCredentials("XYZ", "ABC"));
		System.out.println("Verify if status code is '415'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
		System.out.println("**************************Ends**************************");
	}
}

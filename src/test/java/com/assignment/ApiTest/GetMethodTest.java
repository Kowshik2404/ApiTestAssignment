package com.assignment.ApiTest;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.beans.Customer;
import com.google.gson.Gson;

/**
 * The Class GetMethodTest.
 */
public class GetMethodTest {

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
		System.out.println("Perform test to check status '200'");
		ApiExecutor executApi = new ApiExecutor();
		System.out.println("Send request with correct url");
		CloseableHttpResponse httpResponse = executApi.getMethod("/customer/1/");
		String result = EntityUtils.toString(httpResponse.getEntity());
		System.out.println("Verify if status code is '200'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		System.out.println("Response : " + result);
		Gson gson = new Gson();
		Customer cust = gson.fromJson(result, Customer.class);
		System.out.println("Customer name : " + cust.getName());
		System.out.println("Customer nationality : " + cust.getNationality());
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
		CloseableHttpResponse httpResponse = executApi.getMethod("/customers/1/");
		System.out.println("Verify if status code is '404'");
		System.out.println("Api Status code : " + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
		System.out.println("**************************Ends**************************");
	}
}

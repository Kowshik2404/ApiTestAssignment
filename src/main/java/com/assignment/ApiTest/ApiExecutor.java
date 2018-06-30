package com.assignment.ApiTest;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.assignment.beans.Customer;
import com.google.gson.Gson;

/**
 * The Class ApiExecutor.
 */
public class ApiExecutor {

	/** The http client. */
	CloseableHttpClient httpClient = null;

	/**
	 * Gets the method.
	 *
	 * @param url the url
	 * @return the method
	 * @throws Exception the exception
	 */
	public CloseableHttpResponse getMethod(String url) throws Exception {

		System.out.println("Execute GET method");
		String domain = "http://localhost:8080/ApiTest";
		String resourceUrl = domain + url;
		System.out.println("Url : " + resourceUrl);
		httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(resourceUrl);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		return httpResponse;
	}

	/**
	 * Post method.
	 *
	 * @param url the url
	 * @param contentType the content type
	 * @param customer the customer
	 * @param credentials the credentials
	 * @return the closeable http response
	 * @throws Exception the exception
	 */
	public CloseableHttpResponse postMethod(String url, String contentType, Customer customer, Credentials credentials)
			throws Exception {

		try {
			System.out.println("Execute POST method");
			String domain = "http://localhost:8080/ApiTest";
			String resourceUrl = domain + url;
			System.out.println("Url : " + resourceUrl);
			HttpPost httpPost = new HttpPost(resourceUrl);
			httpPost.setHeader("Content-Type", contentType);
			if (credentials != null) {
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(AuthScope.ANY, credentials);
				httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
			} else {
				httpClient = HttpClients.createDefault();
			}
			Gson gson = new Gson();
			String jsonCust = gson.toJson(customer);
			System.out.println(jsonCust);
			StringEntity entity = new StringEntity(jsonCust);
			httpPost.setEntity(entity);
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return httpResponse;
		} finally {
			httpClient.close();
		}
	}
}

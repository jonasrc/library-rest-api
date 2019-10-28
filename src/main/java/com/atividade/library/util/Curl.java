package com.atividade.library.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

public class Curl {
	
	public static String testCurl() throws URISyntaxException, ClientProtocolException, IOException {
		  
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("www.google.com")
				.setPath("")
				.build();

		ResponseHandler<String> handler = new BasicResponseHandler();
		  
		HttpClient client = HttpClientBuilder.create().build();
		  
		HttpGet httpget = new HttpGet(uri);
		  
		HttpResponse response = client.execute(httpget);
		  
		String body = handler.handleResponse(response);
		  
		return body;
	}

	public static String makeRequest(String scheme, String host, String path, HashMap<String, String> parameters) throws URISyntaxException, ClientProtocolException, IOException {
		  
		URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPath(path);
		
		parameters.forEach(
			(key, value) -> uriBuilder.setParameter(key, value)
		);
		
		URI uri = uriBuilder.build();

		ResponseHandler<String> handler = new BasicResponseHandler();
		  
		HttpClient client = HttpClientBuilder.create().build();
		  
		HttpGet httpget = new HttpGet(uri);
		  
		HttpResponse response = client.execute(httpget);
		  
		String body = handler.handleResponse(response).toString();
		  
		return body;
	}
}

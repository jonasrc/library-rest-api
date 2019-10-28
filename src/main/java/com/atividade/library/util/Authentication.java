package com.atividade.library.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.atividade.library.exception.NotAuthorizedException;

public class Authentication {
	
	private static Map<String, String> authApi = new HashMap<String, String>() {{
		put("scheme", "http");
		put("host", "localhost:9000/auth/v1/api");
		put("authenticate", "/authenticate");
	}};
	
	public static void authenticateUser(HttpServletRequest request) throws IOException, URISyntaxException, NotAuthorizedException {
		String authorization = request.getHeader("Authorization");

		if(authorization == null) {
			throw new NotAuthorizedException();
		}
		
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("base64", authorization);
		
		String response = Curl.makeRequest(
				authApi.get("scheme"),
				authApi.get("host"),
				authApi.get("authenticate"),
				parameters);

		if(!response.equals("authorized")) {
			throw new NotAuthorizedException();
		}
	}
}

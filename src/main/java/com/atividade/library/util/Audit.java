package com.atividade.library.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.atividade.library.exception.NotAuthorizedException;

public class Audit {
	
	private static Map<String, String> auditApi = new HashMap<String, String>() {{
		put("scheme", "http");
		put("host", "localhost:9010/auth/v1/api");
		put("authenticate", "/authenticate");
	}};
	
	public static void postLog(HttpServletRequest request) throws IOException, URISyntaxException, NotAuthorizedException {
		String authorization = request.getHeader("Authorization");

		if(authorization == null) {
			throw new NotAuthorizedException();
		}
		
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("base64", authorization);
		
		String response = Curl.makeRequest(
				auditApi.get("scheme"),
				auditApi.get("host"),
				auditApi.get("authenticate"),
				parameters);

		if(!response.equals("authorized")) {
			throw new NotAuthorizedException();
		}
	}
}

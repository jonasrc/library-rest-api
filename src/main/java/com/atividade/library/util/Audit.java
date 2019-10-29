package com.atividade.library.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.atividade.library.exception.NotAuthorizedException;

public class Audit {
	
	private static Map<String, String> auditApi = new HashMap<String, String>() {{
		put("scheme", "http");
		put("host", "localhost:9020/audit/api/v1");
		put("log", "/log");
	}};
	
	public static void postLog(String orderId, String userId, String transactionId, String creationDate) throws IOException, URISyntaxException, NotAuthorizedException {
		HashMap<String, String> parameters = new HashMap<String, String>();

		parameters.put("orderId", orderId);
		parameters.put("userId", userId);
		parameters.put("transactionId", transactionId);
		parameters.put("creationDate", creationDate);

		String response = Curl.makeRequest(auditApi.get("scheme"), auditApi.get("host"), auditApi.get("log"), parameters);

		if(!response.equals("authorized")) {
			throw new NotAuthorizedException();
		}
	}
}

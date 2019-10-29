package com.atividade.library.util;

import com.atividade.library.exception.NotAuthorizedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
	
	private static Map<String, String> transactionApi = new HashMap<String, String>() {{
		put("scheme", "http");
		put("host", "localhost:9010/credit-card/api/v1");
		put("purchase", "/purchase");
	}};
	
	public static String postPurchase(
			String orderId,
			String userId,
			String creditCardHolder,
			String creditCardNumber,
			String creditCardExpirationDate,
			String creditCardCVC) throws IOException, URISyntaxException, NotAuthorizedException {
		HashMap<String, String> parameters = new HashMap<String, String>();

		parameters.put("orderId", orderId);
		parameters.put("userId", userId);
		parameters.put("creditCardHolder", creditCardHolder);
		parameters.put("creditCardNumber", creditCardNumber);
		parameters.put("creditCardExpirationDate", creditCardExpirationDate);
		parameters.put("creditCardCVC", creditCardCVC);

		String response = Curl.makeRequest(transactionApi.get("scheme"), transactionApi.get("host"), transactionApi.get("purchase"), parameters);

		if(!response.equals("authorized")) {
			throw new NotAuthorizedException();
		}

		return response;
	}
}

package com.donkko.crypto.exchange.bithumb.api.example;

import java.util.HashMap;

public class Main {
    public static void main(String args[]) {
		Api_Client api = new Api_Client("",
			"");
	
		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", "ETH");
		rgParams.put("units", "0.01");
	
	
		try {
		    String result = api.callApi("/trade/market_buy", rgParams);
		    System.out.println(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
    }
}


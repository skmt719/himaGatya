package com.example.himaGatya.Controller.API;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromEventon<T> extends FromEventonAndEmoshicom<T> {

	@Override
	String CreateRequestURL() {
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String request = String.format("https://eventon.jp/api/events.json?limit=100&ymd=%s",ymd);
		
		return request;
	}
	

}

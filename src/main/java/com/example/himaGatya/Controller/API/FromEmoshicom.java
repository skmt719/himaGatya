package com.example.himaGatya.Controller.API;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromEmoshicom<T> extends FromEventonAndEmoshicom<T> {

	@Override
	String CreateRequestURL() {
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String request = String.format("https://api.moshicom.com/api/events.json?ymd=%s&limit=30",ymd);
		
		return request;
	}
}

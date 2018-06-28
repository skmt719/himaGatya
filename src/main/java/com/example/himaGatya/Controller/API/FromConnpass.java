package com.example.himaGatya.Controller.API;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromConnpass<T> extends FromATNDAndConnpass<T> {

	@Override
	String CreateRequestURL() {
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String request = String.format("https://connpass.com/api/v1/event/?count=100&ymd=%s",ymd);
		
		return request;
	}
}

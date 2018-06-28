package com.example.himaGatya.Controller.Controller.API;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FromATND<T> extends FromATNDAndConnpass<T> {

	@Override
	String CreateRequestURL() {
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String request = String.format("http://api.atnd.org/events/?count=100&ymd=%s&format=json",ymd);
		
		return request;
	}
}

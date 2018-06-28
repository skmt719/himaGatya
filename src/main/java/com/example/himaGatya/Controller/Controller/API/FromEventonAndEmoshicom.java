package com.example.himaGatya.Controller.Controller.API;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FromEventonAndEmoshicom<T> extends GetEventAPI<T> {
	@Override
	String ShapingString(String data) {
		String[] tmp = data.split("\\{\"event_id\"");
		StringBuilder res = new StringBuilder();
		//必要な部分の切り出し
		
		for(int i = 1;i<tmp.length;i++) {
		if(tmp[i].indexOf("\"opened_at\"")>=0){
			res.append("{\"event_id\"")
				.append(tmp[i].substring(0, tmp[i].indexOf("\"opened_at\"")-1))
				.append(tmp[i].substring(tmp[i].indexOf("\"capacity\"")-1, tmp[i].indexOf("\"payment_types\"")-1))
				.append(tmp[i].substring(tmp[i].indexOf("\"address\"")-1,tmp[i].indexOf("\"lat\"")-1))
				.append("},");
			}
		}

		String result = new String(res.toString());
		return "[" + result.replaceAll(",$", "").replace("\"catch\"","\"catchcopy\"") + "]";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	T[] StrageData(String str) {
		final ObjectMapper jsonMapper = new ObjectMapper();
		EventonAndEmoshicom[] result = null;
		try {
			result = jsonMapper.readValue(str,EventonAndEmoshicom[].class);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		for(EventonAndEmoshicom events : result) {
			events.setTitle(convertUnicode(events.getTitle()));
			events.setSummary(convertUnicode(events.getSummary()));
			events.setContents(convertUnicode(events.getSummary()));
			events.setAddress(convertUnicode(events.getAddress()));
			events.setPlace(convertUnicode(events.getPlace()));
		}
		
		return (T[])result;
	}
}

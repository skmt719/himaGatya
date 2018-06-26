package com.example.himaGatya.Controller.API;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FromATNDAndConnpass<T> extends GetEventAPI<T> {

	@Override
	String ShapingString(String data) {
		String[] tmp = data.split("\\{\"event_id\"");
		StringBuilder res = new StringBuilder();
		for(int i = 1;i<tmp.length;i++) {
			res.append("{\"event_id\"")
				.append(tmp[i].substring(0, tmp[i].indexOf("\"lat\"")-1))
				.append("},")
				.insert(res.lastIndexOf("\"event_id\"") + 11, "\"")
				.insert(res.lastIndexOf("\"title\"") - 1, "\"")
				.insert(res.lastIndexOf("\"limit\"") + 8, "\"")
				.insert(res.lastIndexOf("\"address\"") - 1, "\"");
		}
		
		String result = new String(res.toString());
		return "[" + result.replaceAll(",$", "").replace("\"catch\"","\"catchcopy\"") + "]";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	T[] StrageData(String str) {

		final ObjectMapper jsonMapper = new ObjectMapper();
		ATNDAndConnpass[] result = null;
		try {
			result = jsonMapper.readValue(str,ATNDAndConnpass[].class);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		for(ATNDAndConnpass events : result) {
			events.setTitle(convertUnicode(events.getTitle()));
			events.setCatchcopy(convertUnicode(events.getCatchcopy()));
			events.setDescription(convertUnicode(events.getDescription()));
			events.setAddress(convertUnicode(events.getAddress()));
			events.setPlace(convertUnicode(events.getPlace()));		
		}
		
		return (T[])result;
	}
}

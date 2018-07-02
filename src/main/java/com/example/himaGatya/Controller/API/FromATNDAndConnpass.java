package com.example.himaGatya.Controller.API;

import java.io.IOException;

import com.example.himaGatya.Controller.Event.Events;
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
	
//	@Override
//	void SaveTable(T[] list) {
//		for(T r : list) {    
//			
//			Events event = new Events();
//			
//			event.setName(((EventonAndEmoshicom) r).getTitle());
//			event.setSummary(((EventonAndEmoshicom) r).getSummary());
//			event.setAddress(((EventonAndEmoshicom) r).getAddress());
//			event.setPlace(((EventonAndEmoshicom) r).getPlace());
//			event.setCost(Integer.parseInt(((EventonAndEmoshicom) r).getPrice()));
//			//event.setStart_on(Date.valueOf(r.getStarted_at()));
//			//event.setEnd_on(Date.valueOf(r.getEnded_at()));
//			//event.setStart_at(new Timestamp(Long.parseLong(r.getStarted_at())));
//			event.setStart_on(((EventonAndEmoshicom) r).getStarted_at());
//			event.setEnd_on(((EventonAndEmoshicom) r).getEnded_at());
//			event.setStart_at(((EventonAndEmoshicom) r).getStarted_at());
//			event.setManager_id(-1);
//			event.setEvent_url(((EventonAndEmoshicom) r).getEvent_url());
//			event.setSite_url(((EventonAndEmoshicom) r).getUrl());
//
//			//eventServiceImpl.saveEvents(event);
//		}     
//	}
	
	Events Save(T r) {
		Events e = new Events();                                                
		                                                                        
		e.setName(((ATNDAndConnpass) r).getTitle()); 
//		if(((ATNDAndConnpass) r).getCatchcopy().isEmpty()) {
//			e.setSummary(((ATNDAndConnpass) r).getCatchcopy());
//		}else {
//			e.setSummary(((ATNDAndConnpass) r).getDescription());
//		}
		e.setSummary(((ATNDAndConnpass)r).getCatchcopy());
		                   
		e.setAddress(((ATNDAndConnpass) r).getAddress());                   
        e.setPlace(((ATNDAndConnpass) r).getPlace());                       
        e.setCost(0);      
        //event.setStart_on(Date.valueOf(r.getStarted_at()));                   
        //event.setEnd_on(Date.valueOf(r.getEnded_at()));                       
        //event.setStart_at(new Timestamp(Long.parseLong(r.getStarted_at())));  
        e.setStart_on(((ATNDAndConnpass) r).getStarted_at());               
        e.setEnd_on(((ATNDAndConnpass) r).getEnded_at());                   
        e.setStart_at(((ATNDAndConnpass) r).getStarted_at());               
        e.setManager_id(-1);                                                    
        e.setEvent_url(((ATNDAndConnpass) r).getEvent_url());               
        e.setSite_url(((ATNDAndConnpass) r).getUrl());   
        
        return e;
	}
}

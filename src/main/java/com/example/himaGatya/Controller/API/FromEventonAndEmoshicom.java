package com.example.himaGatya.Controller.API;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.himaGatya.Controller.Event.Events;
import com.example.himaGatya.Controller.Event.EventsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FromEventonAndEmoshicom<T> extends GetEventAPI<T> {

	@Autowired
	EventsServiceImpl eventServiceImpl;
	
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
				.append(tmp[i].substring(tmp[i].lastIndexOf("\"price\"")-1,tmp[i].indexOf("}],\"cancel_policy\"")))
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
	
	@Override
//	void SaveTable(T[] list) {
//		for(T r : list) {    
//			
//			Events e = new Events();
//			
//			e.setName(((EventonAndEmoshicom) r).getTitle());
//			e.setSummary(((EventonAndEmoshicom) r).getSummary());
//			e.setAddress(((EventonAndEmoshicom) r).getAddress());
//			e.setPlace(((EventonAndEmoshicom) r).getPlace());
//			e.setCost(Integer.parseInt(((EventonAndEmoshicom) r).getPrice()));
//			//event.setStart_on(Date.valueOf(r.getStarted_at()));
//			//event.setEnd_on(Date.valueOf(r.getEnded_at()));
//			//event.setStart_at(new Timestamp(Long.parseLong(r.getStarted_at())));
//			e.setStart_on(((EventonAndEmoshicom) r).getStarted_at());
//			e.setEnd_on(((EventonAndEmoshicom) r).getEnded_at());
//			e.setStart_at(((EventonAndEmoshicom) r).getStarted_at());
//			e.setManager_id(-1);
//			e.setEvent_url(((EventonAndEmoshicom) r).getEvent_url());
//			e.setSite_url(((EventonAndEmoshicom) r).getUrl());
//
//			eventServiceImpl.saveEvents(e);
//		}     
//	}
	Events Save(T r) {
		Events e = new Events();                                                
		                                                                        
		e.setName(((EventonAndEmoshicom) r).getTitle());                        
		e.setSummary(((EventonAndEmoshicom) r).getSummary());                   
		e.setAddress(((EventonAndEmoshicom) r).getAddress());                   
        e.setPlace(((EventonAndEmoshicom) r).getPlace());                       
        e.setCost(Integer.parseInt(((EventonAndEmoshicom) r).getPrice()));      
        //event.setStart_on(Date.valueOf(r.getStarted_at()));                   
        //event.setEnd_on(Date.valueOf(r.getEnded_at()));                       
        //event.setStart_at(new Timestamp(Long.parseLong(r.getStarted_at())));  
        e.setStart_on(((EventonAndEmoshicom) r).getStarted_at());               
        e.setEnd_on(((EventonAndEmoshicom) r).getEnded_at());                   
        e.setStart_at(((EventonAndEmoshicom) r).getStarted_at());               
        e.setManager_id(-1);                                                    
        e.setEvent_url(((EventonAndEmoshicom) r).getEvent_url());               
        e.setSite_url(((EventonAndEmoshicom) r).getUrl());   
        
        return e;
	}
}

package com.example.himaGatya.Controller.API;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.himaGatya.Controller.Event.Events;
import com.example.himaGatya.Controller.Event.EventsServiceImpl;



@RestController
public class TestGetEventAPI {

	@Autowired
	EventsServiceImpl eventServiceImpl;

	@RequestMapping("/TestAPI")
	public String FromAPI() {
		GetEventAPI<ATNDAndConnpass> atnd = new FromATND<>() ;
		GetEventAPI<EventonAndEmoshicom> eventon = new FromEventon<>();
		GetEventAPI<ATNDAndConnpass> connpass = new FromConnpass<>() ;
		GetEventAPI<EventonAndEmoshicom> Emoshicom = new FromEmoshicom<>();
		
		ATNDAndConnpass[] ArrayATND = null;
		EventonAndEmoshicom[] ArrayEventon = null;
		ATNDAndConnpass[] ArrayConnpass = null;
		EventonAndEmoshicom[] ArrayEmoshicom = null;
		String data;
		String result = null;
		String query = null;
	
		String filenameATND = "FromAPI\\ATND";
		String filenameEventon = "FromAPI\\Eventon";
		String filenameConnpass = "FromAPI\\Connpass";
		String filenameEmoshicom = "FromAPI\\Emoshicom";
		
		Events event = new Events();
		 

		//ATND接続テスト
		
		query = atnd.CreateRequestURL();
		//atnd.ReadAPI(query, filenameATND);
		data = atnd.WriteAPI(filenameATND);
		result = atnd.ShapingString(data);
		ArrayATND = atnd.StrageData(result);
		
		
		//Eventon接続テスト
		
		query = eventon.CreateRequestURL();
		//eventon.ReadAPI(query, filenameEventon);
		data = eventon.WriteAPI(filenameEventon);
		result = eventon.ShapingString(data);
		ArrayEventon = eventon.StrageData(result);
		
		
		//connpass接続テスト
		
		query = connpass.CreateRequestURL();
		//connpass.ReadAPI(query, filenameConnpass);
		data = connpass.WriteAPI(filenameConnpass);
		result = connpass.ShapingString(data);
		ArrayConnpass = connpass.StrageData(result);
		
		
		//Emoshicom接続テスト
		
		query = Emoshicom.CreateRequestURL();
		//Emoshicom.ReadAPI(query, filenameEmoshicom);
		data = Emoshicom.WriteAPI(filenameEmoshicom);
		result = Emoshicom.ShapingString(data);
		ArrayEmoshicom = Emoshicom.StrageData(result);
		
		
		
		for(ATNDAndConnpass r : ArrayATND) {
			//System.out.println(r.getEvent_id() + ":" +r.getTitle()+"Address:"+r.getAddress()+"Place:"+ r.getPlace());
		}                                                                                
		for(EventonAndEmoshicom r : ArrayEventon) {      
			
			event.setName(r.getTitle());
			event.setSummary(r.getSummary());
			event.setAddress(r.getAddress());
			event.setPlace(r.getPlace());
			//event.setStart_on(Date.valueOf(r.getStarted_at()));
			//event.setEnd_on(Date.valueOf(r.getEnded_at()));
			//event.setStart_at(new Timestamp(Long.parseLong(r.getStarted_at())));
			event.setManager_id(-1);
			event.setEvent_url(r.getEvent_url());
			event.setSite_url(r.getUrl());
			
			eventServiceImpl.saveEvents(event);

			
			//System.out.println(r.getEvent_id() + ":" +r.getTitle()+"Address:"+r.getAddress()+"Place:"+ r.getPlace());
		}                                                                                 
		for(ATNDAndConnpass r : ArrayConnpass) {                                          
			System.out.println(r.getEvent_id() + ":" +r.getTitle()+"Address:"+r.getAddress()+"Place:"+ r.getPlace());
		}                                                                                   
		for(EventonAndEmoshicom r : ArrayEmoshicom) {                                       
			System.out.println(r.getEvent_id() + ":" +r.getTitle()+"Address:"+r.getAddress()+"Place:"+ r.getPlace());
		}
				
		return result;
		
		
	}
	
}

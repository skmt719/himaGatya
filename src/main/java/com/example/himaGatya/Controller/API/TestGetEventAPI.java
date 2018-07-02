package com.example.himaGatya.Controller.API;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.himaGatya.Controller.Event.Events;
import com.example.himaGatya.Controller.Event.EventsServiceImpl;



@RestController
public class TestGetEventAPI {

	@Autowired
	EventsServiceImpl eventServiceImpl;

	@RequestMapping("/admin/TestAPI")
	@Scheduled(cron = "${scheduler.cron}")
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
		
		
		 

		//ATND接続テスト
		
		query = atnd.CreateRequestURL();
		//atnd.ReadAPI(query, filenameATND);
		data = atnd.WriteAPI(filenameATND);
		result = atnd.ShapingString(data);
		ArrayATND = atnd.StrageData(result);
		for(ATNDAndConnpass r : ArrayATND) {
			eventServiceImpl.saveEvents(atnd.Save(r));
		}
		
		
		//Eventon接続テスト
		
		query = eventon.CreateRequestURL();
		//eventon.ReadAPI(query, filenameEventon);
		data = eventon.WriteAPI(filenameEventon);
		result = eventon.ShapingString(data);
		System.out.println(result);
		ArrayEventon = eventon.StrageData(result);
		//eventon.SaveTable(ArrayEventon);
		for(EventonAndEmoshicom r : ArrayEventon) {
			eventServiceImpl.saveEvents(eventon.Save(r));
		}
 
		
		
		
		//connpass接続テスト
		
		query = connpass.CreateRequestURL();
		//connpass.ReadAPI(query, filenameConnpass);
		data = connpass.WriteAPI(filenameConnpass);
		result = connpass.ShapingString(data);
		ArrayConnpass = connpass.StrageData(result);
		for(ATNDAndConnpass r : ArrayConnpass) {
			eventServiceImpl.saveEvents(connpass.Save(r));
		}
		
		
		//Emoshicom接続テスト
		
		query = Emoshicom.CreateRequestURL();
		//Emoshicom.ReadAPI(query, filenameEmoshicom);
		data = Emoshicom.WriteAPI(filenameEmoshicom);
		result = Emoshicom.ShapingString(data);
		ArrayEmoshicom = Emoshicom.StrageData(result);
		for(EventonAndEmoshicom r : ArrayEmoshicom) {
			eventServiceImpl.saveEvents(Emoshicom.Save(r));
		}
		
		return "end";
				
	}
	
}

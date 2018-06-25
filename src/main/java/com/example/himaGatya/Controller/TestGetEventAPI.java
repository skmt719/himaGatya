package com.example.himaGatya.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.himaGatya.Controller.FromATND.ATND;

@RestController
public class TestGetEventAPI {

	@RequestMapping("/TestAPI")
	public String FromAPI() {
		GetEventAPI<ATND> atnd = new FromATND<>() ;
		ATND[] ArrayATND = null;
		String data;
		String result = null;
		String query = null;
	
		ArrayList<String> list = null;
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String filenameATND = String.format("dataATND/%sATND.txt",ymd);

		//ATND接続テスト
		query = atnd.CreateRequestURL();
		atnd.ReadAPI(query, filenameATND);
		data = atnd.WriteAPI(filenameATND);
		result = atnd.ShapingString(data);
		ArrayATND = atnd.StrageData(result);
		
		
		for(ATND r : ArrayATND) {
			System.out.println(r.getEvent_id() + ":" +r.getTitle()+"::::"+r.getEnded_at());
		}
		System.out.println(data);
		return result;
		
	}
	
}

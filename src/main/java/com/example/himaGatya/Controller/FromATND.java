package com.example.himaGatya.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FromATND<T> extends GetEventAPI<T> {

	@Override
	String CreateRequestURL() {
		Date date = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(date);
		String request = String.format("http://api.atnd.org/events/?count=100&ymd=%s&format=json",ymd);
		
		return request;
	}
	
	@Override
	String ShapingString(String data) {
		String[] tmp = data.split("\\{");
		StringBuilder res = new StringBuilder();
		for(int i = 3;i<tmp.length;i+=2) {
			res.append("{")
				.append(tmp[i].substring(0, tmp[i].indexOf("\"lat\"")-1))
				.append("},")
				.insert(res.lastIndexOf("\"event_id\"") + 11, "\"")
				.insert(res.lastIndexOf("\"title\"") - 1, "\"")
				.insert(res.lastIndexOf("\"limit\"") + 8, "\"")
				.insert(res.lastIndexOf("\"address\"") - 1, "\"");
			System.out.println(tmp[i]);
		}
		
		String result = new String(res.toString());
		System.out.println(result);
		return "[" + result.replaceAll(",$", "").replace("\"catch\"","\"catchcopy\"") + "]";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	T[] StrageData(String str) {

		final ObjectMapper jsonMapper = new ObjectMapper();
		ATND[] result = null;
		System.out.println(str);
		try {
			result = jsonMapper.readValue(str,ATND[].class);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}		
		return (T[])result;
	}

	static class ATND {
		private Integer event_id;
		
		private String title;
		private String catchcopy;
		private String description;
		private String event_url;
		private String started_at;
		private String ended_at;
		private String url;
		private String limit;
		private String address;
		private String place;
		
		public Integer getEvent_id() {
			return event_id;
		}
		public void setEvent_id(Integer event_id) {
			this.event_id = event_id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getCatchcopy() {
			return catchcopy;
		}
		public void setCatchcopy(String catchcopy) {
			this.catchcopy = catchcopy;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getEvent_url() {
			return event_url;
		}
		public void setEvent_url(String event_url) {
			this.event_url = event_url;
		}
		public String getStarted_at() {
			return started_at;
		}
		public void setStarted_at(String started_at) {
			this.started_at = started_at;
		}
		public String getEnded_at() {
			return ended_at;
		}
		public void setEnded_at(String ended_at) {
			this.ended_at = ended_at;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getLimit() {
			return limit;
		}
		public void setLimit(String limit) {
			this.limit = limit;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		
	}
}

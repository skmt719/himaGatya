package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Event_create_logsServiceImpl implements Event_create_logsService {

	@Autowired
	Event_create_logsDao event_create_logsDao;


	@Override
	public Event_create_logs getEvent_create_logsById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		return event_create_logsDao.findById(id).orElse(null);
	}

	@Override
	public List<Event_create_logs> getEvent_create_logsList() {
		// TODO 自動生成されたメソッド・スタブ
		return event_create_logsDao.findAll();
	}

	@Override
	public void saveEvent_create_logs(Event_create_logs event_create_logs) {
		// TODO 自動生成されたメソッド・スタブ
		event_create_logsDao.saveAndFlush(event_create_logs);
	}
}

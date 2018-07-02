package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired
	EventsDao EventsDao;



	@Override
	public List<Events> getEventsById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		return EventsDao.findById(id);
	}

	@Override
	public List<Events> getEventsList() {
		// TODO 自動生成されたメソッド・スタブ
		return EventsDao.findAll();
	}

	@Override
	public void saveEvents(Events certifications) {
		// TODO 自動生成されたメソッド・スタブ
		EventsDao.saveAndFlush(certifications);
	}

	@Override
	public void deleteEventById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		EventsDao.deleteById(id);
	}
}

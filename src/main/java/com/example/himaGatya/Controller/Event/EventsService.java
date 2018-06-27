package com.example.entity;

import java.util.List;

public interface EventsService {

	public Events getEventsById(long id);

	public List<Events> getEventsList();

	public void saveEvents(Events events);

	public void deleteEventById(long id);

}

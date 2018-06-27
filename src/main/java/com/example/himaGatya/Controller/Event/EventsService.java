package com.example.himaGatya.Controller.Event;

import java.util.List;

public interface EventsService {

	public Events getEventsById(long id);

	public List<Events> getEventsList();

	public void saveEvents(Events events);

	public void deleteEventById(long id);

}

package com.example.himaGatya.Controller.Event;

import java.util.List;

public interface Event_create_logsService {

	public Event_create_logs getEvent_create_logsById(long id);

	public List<Event_create_logs> getEvent_create_logsList();

	public void saveEvent_create_logs(Event_create_logs event_create_logs);

}

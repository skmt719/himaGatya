package com.example.himaGatya.Controller.Event;

import java.util.List;

public interface GachaRollLogsService {

	public GachaRollLogs getGachaRollLogsById(long id);

	public List<GachaRollLogs> getGachaRollLogsList();

	public void saveGachaRollLogs(GachaRollLogs gachaRollLogs);

	public void deleteGachaRollLogsById(long id);

}

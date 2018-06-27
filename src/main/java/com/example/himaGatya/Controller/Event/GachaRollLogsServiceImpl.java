package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GachaRollLogsServiceImpl implements GachaRollLogsService {

	@Autowired
	GachaRollLogsDao GachaRollLogsDao;

	@Override
	public GachaRollLogs getGachaRollLogsById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		return GachaRollLogsDao.findById(id).orElse(null);
	}

	@Override
	public List<GachaRollLogs> getGachaRollLogsList() {
		// TODO 自動生成されたメソッド・スタブ
		return GachaRollLogsDao.findAll();
	}

	@Override
	public void saveGachaRollLogs(GachaRollLogs gachaRollLogs) {
		// TODO 自動生成されたメソッド・スタブ
		GachaRollLogsDao.saveAndFlush(gachaRollLogs);
	}

	@Override
	public void deleteGachaRollLogsById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		GachaRollLogsDao.deleteById(id);
	}
}

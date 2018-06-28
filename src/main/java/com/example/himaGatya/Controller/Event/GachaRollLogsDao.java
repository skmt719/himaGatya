package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GachaRollLogsDao extends JpaRepository <GachaRollLogs, Long> {

	public List<GachaRollLogs> findAll();

}
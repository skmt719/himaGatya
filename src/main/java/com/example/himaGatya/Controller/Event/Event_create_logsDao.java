package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Event_create_logsDao extends JpaRepository <Event_create_logs, Long> {

	public List<Event_create_logs> findAll();

}



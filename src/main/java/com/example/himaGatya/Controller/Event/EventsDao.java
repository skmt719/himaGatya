package com.example.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsDao extends JpaRepository <Events, Long> {

	public List<Events> findAll();

}
package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository <Users, Long> {

	public List<Users> findAll();
	public Users findByName(String name);
}



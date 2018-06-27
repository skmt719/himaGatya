package com.example.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationsDao extends JpaRepository <Certifications, Long> {

	public List<Certifications> findAll();

}



package com.example.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificationsServiceImpl implements CertificationsService {

	@Autowired
	CertificationsDao certificationsDao;




	@Override
	public Certifications getCertificationsById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		return certificationsDao.findById(id).orElse(null);
	}

	@Override
	public List<Certifications> getCertificationsList() {
		// TODO 自動生成されたメソッド・スタブ
		return certificationsDao.findAll();
	}

	@Override
	public void saveCertifications(Certifications certifications) {
		// TODO 自動生成されたメソッド・スタブ
		certificationsDao.saveAndFlush(certifications);
	}
}

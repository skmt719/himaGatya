package com.example.himaGatya.Controller.Event;

import java.util.List;

public interface UsersService {

	public Certifications getCertificationsById(long id);

	public List<Certifications> getCertificationsList();

	public void saveCertifications(Certifications certifications);

}

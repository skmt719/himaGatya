package com.example.himaGatya.Controller.Event;

import java.util.List;

public interface CertificationsService {

	public Certifications getCertificationsById(long id);

	public List<Certifications> getCertificationsList();

	public void saveCertifications(Certifications certifications);

}

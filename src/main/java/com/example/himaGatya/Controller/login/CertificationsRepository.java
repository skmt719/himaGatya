package com.example.himaGatya.Controller.login;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CertificationsRepository extends CrudRepository<Certifications, Long> {

    public Certifications findByUsername(String username);

    public Certifications findByMailAddress(String mailAddress);

}
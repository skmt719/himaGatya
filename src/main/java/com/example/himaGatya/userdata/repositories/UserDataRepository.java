package com.example.himaGatya.userdata.repositories;

import com.example.himaGatya.Controller.Auth.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

}

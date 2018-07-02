package com.example.himaGatya.Controller.Event;

import java.util.List;

import com.example.himaGatya.Controller.login.Certifications;

public interface UsersService {

	public Users getUsersById(long id);

	public List<Users> getUsersList();

	public void saveUsers(Users certifications);

}

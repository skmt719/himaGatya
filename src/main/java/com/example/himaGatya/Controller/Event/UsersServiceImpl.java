package com.example.himaGatya.Controller.Event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDao usersDao;




	@Override
	public Users getUsersById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		return usersDao.findById(id).orElse(null);
	}

	@Override
	public List<Users> getUsersList() {
		// TODO 自動生成されたメソッド・スタブ
		return usersDao.findAll();
	}

	@Override
	public void saveUsers(Users users) {
		// TODO 自動生成されたメソッド・スタブ
		usersDao.saveAndFlush(users);
	}
}

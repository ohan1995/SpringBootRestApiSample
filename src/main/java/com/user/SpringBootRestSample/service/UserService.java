package com.user.SpringBootRestSample.service;

import java.util.List;

import com.user.SpringBootRestSample.exception.RecordNotFoundException;
import com.user.SpringBootRestSample.model.User;

public interface UserService {
	
	public List<User> getAllUser();
	
	public User getUserById(int id) throws RecordNotFoundException;
	
	public User createUser(User user);
	
	public User updateUser(int id, User user) throws RecordNotFoundException;
	
	public void deleteUserById(int id) throws RecordNotFoundException;
}

package com.user.SpringBootRestSample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.SpringBootRestSample.exception.RecordNotFoundException;
import com.user.SpringBootRestSample.model.User;
import com.user.SpringBootRestSample.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public List<User> getAllUser() {
		return userDAO.findAll();
	}

	@Override
	public User getUserById(int id) throws RecordNotFoundException {
		
		System.out.println("userById");
		User user = userDAO.findById(id)
				.orElseThrow(() -> 
					new RecordNotFoundException("No Record Found for "+id +" id"));

		return user;
	}

	@Override
	public User createUser(User user) {
		return userDAO.save(user);
	}

	@Override
	public User updateUser(int id, User user) throws RecordNotFoundException {
		
		User updated = userDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Unable to update for " + id + " id"));
		
		User newUser = updated;
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		
		User updatedUser = userDAO.save(newUser);
		return updatedUser;
	}

	@Override
	public void deleteUserById(int id) throws RecordNotFoundException{
		
		@SuppressWarnings("unused")
		User user = userDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Unable to delete for " + id + " id no record found"));
		
		userDAO.deleteById(id);
		
	}

}

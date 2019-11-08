package com.user.SpringBootRestSample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.user.SpringBootRestSample.exception.RecordNotFoundException;
import com.user.SpringBootRestSample.model.User;
import com.user.SpringBootRestSample.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("index")
	   public String index() {
	      return "index";
	   }
	
	@GetMapping
	public List<User> getAllUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws RecordNotFoundException {
		User user = service.getUserById(id);
		
		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,@RequestBody User userDetails) throws RecordNotFoundException{
		
			User updatedUser = service.updateUser(id, userDetails);
			return new ResponseEntity<User>(updatedUser, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteUser(@PathVariable("id") int id) throws RecordNotFoundException {
		
		service.deleteUserById(id);
		return HttpStatus.FORBIDDEN;
	}
}

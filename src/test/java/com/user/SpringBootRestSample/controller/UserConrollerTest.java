package com.user.SpringBootRestSample.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.user.SpringBootRestSample.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserConrollerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	void testGetAllUser() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/user",
												HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetUserById() {
		int id = 1;
		User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		System.out.println(user.getFirstName());
		
		assertNotNull(user);
	}
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setFirstName("umesh");
		user.setLastName("singh");
		user.setEmail("umesh@a.com");
		
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/user/", user, User.class);
		
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateUser() {
		int id = 7;
		User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		user.setFirstName("Ajay");
		user.setLastName("Sharma");
		
		restTemplate.put(getRootUrl() + "/user" + id, user);
		
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		assertNotNull(updatedUser);
	}
	
	@Test
	public void testDeleteUser() {
		int id = 7;
		User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		assertNotNull(user);
		
		restTemplate.delete(getRootUrl() + "/user/" + id);
		
		try {
			user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		}catch(HttpClientErrorException ex) {
			assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}

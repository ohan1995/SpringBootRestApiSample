package com.user.SpringBootRestSample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.SpringBootRestSample.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{

}

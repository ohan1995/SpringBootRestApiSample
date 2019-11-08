package com.user.SpringBootRestSample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootRestSampleApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestSampleApplication.class, args);
	}

}

package com.bmi.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpartaBmiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpartaBmiUsersApplication.class, args);
	}
	
	@GetMapping("/")
	public String test() {
		return "sparta bmi users is up and running";
	}

}

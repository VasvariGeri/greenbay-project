package com.gfa.homework;

import com.gfa.homework.models.User;
import com.gfa.homework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication implements CommandLineRunner {

	private UserService userService;

	@Autowired
	public HomeworkApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("foo");
		user.setPassword("foo");
		user.setActive(true);
		user.setRoles("User");
		userService.saveUser(user);
	}
}

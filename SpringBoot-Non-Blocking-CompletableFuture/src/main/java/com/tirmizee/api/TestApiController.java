package com.tirmizee.api;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.entity.User;
import com.tirmizee.repository.UserRepository;
import com.tirmizee.service.UserService;

@RestController
public class TestApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/blocking/user/{firstName}")
	public User getUser(@PathVariable String firstName) {
		return userRepository.getByFirstname(firstName);
	}
	
	@GetMapping("/blockingdelay/user/{firstName}")
	public User getUserDelay(@PathVariable String firstName) throws InterruptedException {
		Thread.sleep(1000L);
		return userRepository.getByFirstname(firstName);
	}
	
	@GetMapping("/nonblocking/user/{firstName}")
	public CompletableFuture<User> asyncGetUser(@PathVariable String firstName) {
		return userService.asyncGetUser(firstName);
	}
	
//	@GetMapping("/nonblockingdelay/user/{firstName}")
//	public CompletableFuture<User> asyncGetUserDelay(@PathVariable String firstName) {
//		return userService.asyncGetUserDelay(firstName);
//	}
	
}

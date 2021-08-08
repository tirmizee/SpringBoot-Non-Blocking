package com.tirmizee.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tirmizee.entity.User;
import com.tirmizee.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * same as below
	 * CompletableFuture<User> future = CompletableFuture.runAsync(() -> asyncGetUser(), TASK_EXECUTOR_SERVICE);
	 */
	@Async(value = "TASK_EXECUTOR_SERVICE")
	public CompletableFuture<User> asyncGetUser(String firstName) {
		System.out.println(Thread.currentThread().getName());
		return CompletableFuture.completedFuture(userRepository.getByFirstname(firstName));
	}
	

}

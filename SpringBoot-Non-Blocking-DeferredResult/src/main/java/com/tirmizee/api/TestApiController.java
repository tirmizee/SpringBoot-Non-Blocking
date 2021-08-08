package com.tirmizee.api;

import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.entity.User;
import com.tirmizee.repository.UserRepository;

@RestController
public class TestApiController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/blocking/user/{firstName}")
	public User getUser(@PathVariable String firstName) {
		return userRepository.getByFirstname(firstName);
	}
	
	@GetMapping("/blockingdelay/user/{firstName}")
	public User getUserDelay(@PathVariable String firstName) throws InterruptedException {
		Thread.sleep(3000L);
		return userRepository.getByFirstname(firstName);
	}
	
	@GetMapping("/nonblocking/user/{firstName}")
	public DeferredResult<User> asyncGetUser(@PathVariable String firstName) {
		DeferredResult<User> deferredResult = new DeferredResult<User>();
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		commonPool.submit(() -> deferredResult.setResult(userRepository.getByFirstname(firstName)));
		return deferredResult;
	}
	
	@GetMapping("/nonblockingdelay/user/{firstName}")
	public DeferredResult<User> asyncGetUserDelay(@PathVariable String firstName) {
		DeferredResult<User> deferredResult = new DeferredResult<User>();
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		commonPool.submit(() -> {
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			deferredResult.setResult(userRepository.getByFirstname(firstName));
		});
		return deferredResult;
	}
	
}

package com.tirmizee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tirmizee.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User getByFirstname(String firstname);
	
}

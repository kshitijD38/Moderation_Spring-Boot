package com.moderation.moderation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moderation.moderation.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	@Query(value = "select * from user where user_name = ?1", nativeQuery = true)
	public User findUserByUserName(String userName);
	
}

package com.moderation.moderation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moderation.moderation.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

	@Query(value = "select * from post where user_id = ?1 and flag = ?2", nativeQuery = true)
	public List<Post> findPostByUserId(int userId, boolean flag);
	
	@Query(value = "select * from post where flag = ?1", nativeQuery = true)
	public List<Post> findPostsByAllUser(boolean flag);
	
	@Query(value = "select * from post where status = ?1", nativeQuery = true)
	public List<Post> findPostByStatus(String status);

}

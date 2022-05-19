package com.moderation.moderation.entity.response;

import java.util.List;

import com.moderation.moderation.entity.Post;

import lombok.Data;

@Data
public class UserResponseList {

	private int user_id;
	private String user_name;
	private List<Post> flagged;
	private List<Post> approved;
	private List<Post> rejected;
	private String error;
	
}

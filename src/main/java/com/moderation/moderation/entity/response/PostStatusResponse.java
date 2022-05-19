package com.moderation.moderation.entity.response;

import lombok.Data;

@Data
public class PostStatusResponse {

	private int post_id;
	private String post;
	private String status;
	private String error;
	
}

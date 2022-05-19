package com.moderation.moderation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moderation.moderation.entity.request.PostReqData;
import com.moderation.moderation.entity.request.PostStatusReqData;
import com.moderation.moderation.entity.request.UserReqData;
import com.moderation.moderation.entity.response.PostResponse;
import com.moderation.moderation.entity.response.PostStatusResponse;
import com.moderation.moderation.entity.response.UserResponseList;
import com.moderation.moderation.service.PostService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

	@Autowired
	PostService postService;
	
	@PostMapping("/post")
	public PostResponse sendPost(@RequestBody PostReqData req) {
		return postService.sendPost(req);
	}
	
	@PostMapping("/user-login")
	public UserResponseList userLogin(@RequestBody UserReqData req) {
		return postService.userLogin(req);
	}
	
	@PostMapping("/approve-or-reject")
	public PostStatusResponse approveReject(@RequestBody PostStatusReqData req) {
		return postService.approveReject(req);
	}
	
}

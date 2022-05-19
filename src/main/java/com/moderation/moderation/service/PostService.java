package com.moderation.moderation.service;

import com.moderation.moderation.entity.request.PostReqData;
import com.moderation.moderation.entity.request.PostStatusReqData;
import com.moderation.moderation.entity.request.UserReqData;
import com.moderation.moderation.entity.response.PostResponse;
import com.moderation.moderation.entity.response.PostStatusResponse;
import com.moderation.moderation.entity.response.UserResponseList;

public interface PostService {

	public PostResponse sendPost(PostReqData req);
	public UserResponseList userLogin(UserReqData req);
	public PostStatusResponse approveReject(PostStatusReqData req);
}

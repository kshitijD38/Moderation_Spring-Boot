package com.moderation.moderation.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moderation.moderation.dao.PostDao;
import com.moderation.moderation.dao.UserDao;
import com.moderation.moderation.entity.Post;
import com.moderation.moderation.entity.User;
import com.moderation.moderation.entity.request.PostReqData;
import com.moderation.moderation.entity.request.PostStatusReqData;
import com.moderation.moderation.entity.request.UserReqData;
import com.moderation.moderation.entity.response.PostResponse;
import com.moderation.moderation.entity.response.PostStatusResponse;
import com.moderation.moderation.entity.response.UserResponseList;
import com.moderation.moderation.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	public ArrayList<String> badWords = new ArrayList<>(Arrays.asList("badword", "bad", "no", "anotherbadword",
			"anotherbad", "anotherno", "badword2", "bad2", "no2", "oh"));

	@Autowired
	PostDao postDao;

	@Autowired
	UserDao userDao;

	@Override
	public PostResponse sendPost(PostReqData req) {
		try {
			User user = userDao.findUserByUserName(req.getUser_name());
			req.setUser_id(user.getUserId());
			String[] words = req.getPost().split(" ");
			ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(words));
			for (Iterator<String> iterator = stringArrayList.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.print(string + " ");
			}
			System.out.println();
			for (String temp : stringArrayList) {
				temp = temp.toLowerCase();
				if (badWords.contains(temp)) {
					System.err.println("not approps");
					Post tempPost = new Post();
					tempPost.setFlag(true);
					tempPost.setPost(req.getPost());
					tempPost.setStatus("Pending");
					tempPost.setUserID(req.getUser_id());
					postDao.save(tempPost);

					PostResponse response = new PostResponse();
					response.setPostStatus("PENDING");
					response.setStatus("OK");
					return response;
				}
			}
			System.err.println("approps");
			Post tempPost = new Post();
			tempPost.setFlag(false);
			tempPost.setPost(req.getPost());
			tempPost.setStatus("Approved");
			tempPost.setUserID(req.getUser_id());
			postDao.save(tempPost);
//		for(String temp : stringArrayList) {
//			System.out.print(temp+" ");
//		}
			PostResponse response = new PostResponse();
			response.setPostStatus("APPROVED_BY_DEFAULT");
			response.setStatus("OK");
			return response;
		} catch (Exception e) {
			PostResponse response = new PostResponse();
			response.setStatus("ERROR");
			response.setError(e.getLocalizedMessage() + " " + e);
			return response;
		}
	}

	@Override
	public UserResponseList userLogin(UserReqData req) {
		UserResponseList response = new UserResponseList();
		List<Post> flaggedPosts = new ArrayList<Post>();
		List<Post> approvedPosts = new ArrayList<Post>();
		List<Post> rejectedPosts = new ArrayList<Post>();
		try {
			User user = userDao.findUserByUserName(req.getUser_name());
			System.out.println(user);
			if (user != null) {
				if (user.getPassword().equals(req.getPassword())) {
					response.setUser_id(user.getUserId());
					response.setUser_name(user.getUserName());
					if (!req.getUser_name().equals("moderator")) {
						flaggedPosts = postDao.findPostByUserId(user.getUserId(), true);
						approvedPosts = postDao.findPostByUserId(user.getUserId(), false);
						rejectedPosts = postDao.findPostByStatus("Rejected");
					} else {
						flaggedPosts = postDao.findPostsByAllUser(true);
					}
				} else {
					response.setError("Check Password");
				}
			} else {
				response.setError("Check User-Name and/or Password");
			}
		} catch (Exception e) {
			response.setError(e.getLocalizedMessage() + " " + e);
		}
		response.setFlagged(flaggedPosts);
		response.setApproved(approvedPosts);
		response.setRejected(rejectedPosts);
		return response;
	}

	@Override
	public PostStatusResponse approveReject(PostStatusReqData req) {
		Post post;
		PostStatusResponse response = new PostStatusResponse();
		try {
			post = postDao.getById(req.getPost_id());
			post.setFlag(false);
			post.setStatus(req.getStatus());
			postDao.save(post);
			response.setPost(post.getPost());
			response.setPost_id(req.getPost_id());
			response.setStatus(req.getStatus());
		} catch (Exception e) {
			response.setError(e.getLocalizedMessage() + " " + e);
		}
		return response;
	}

}

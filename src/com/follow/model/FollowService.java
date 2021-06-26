package com.follow.model;

import java.util.List;

public class FollowService {
	
	private FollowDAO_interface dao;
	
	public FollowService() {
		dao = new FollowDAO();
	}
	
	public void addFollow(Integer follower,Integer followed) {
		dao.insert(follower,followed);
	}
	public void deleteFollow(Integer follower,Integer followed) {
		dao.delete(follower, followed);
	}
	public List<Integer> findFollower(Integer follower){
		return dao.findFollower(follower);
	}
	public List<Integer> findFollowed(Integer followed){
		return dao.findFollower(followed);
	}
}

package com.follow.model;

import java.util.List;

public class FollowService {
	
	private FollowDAO_interface dao;
	
	public FollowService() {
		dao = new FollowDAO();
	}
	
	public FollowVO addFollow(Integer followed,Integer follower) {
		FollowVO followvo = new FollowVO();
		
		followvo.setFollowed(followed);
		followvo.setFollower(follower);
		dao.insert(followvo);
		
		return followvo;
	}
	public void deleteFollow(Integer follower,Integer followed) {
		dao.delete(follower, followed);
	}
	public List<FollowVO> findFollower(Integer follower){
		return dao.findFollower(follower);
	}
	public List<FollowVO> findFollowed(Integer followed){
		return dao.findFollower(followed);
	}
}

package com.follow.model;

import java.util.List;


public interface FollowDAO_interface {
	public void insert(FollowVO FollowVO);
    public void delete(FollowVO FollowVO);
    public List<FollowVO> findFollower(Integer follower);//我追蹤誰
    public List<FollowVO> findFollowed(Integer followed);//誰追蹤我

}

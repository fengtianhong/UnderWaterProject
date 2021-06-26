package com.follow.model;

import java.util.List;


public interface FollowDAO_interface {
	public void insert(Integer follower,Integer followed);
    public void delete(Integer follower,Integer followed);
    public List<Integer> findFollower(Integer follower);//我追蹤誰
    public List<Integer> findFollowed(Integer followed);//誰追蹤我

}

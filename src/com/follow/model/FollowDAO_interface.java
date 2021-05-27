package com.follow.model;

import java.util.List;


public interface FollowDAO_interface {
	public void insert(FollowVO FollowVO);
    public void update(FollowVO FollowVO);
    public FollowVO findByPrimaryKey(Integer follower,Integer followed);
    public List<FollowVO> getAll();
}

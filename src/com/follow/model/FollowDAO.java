package com.follow.model;

import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class FollowDAO implements FollowDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(FollowVO FollowVO) {

	}

	@Override
	public void delete(FollowVO FollowVO) {

	}

	@Override
	public List<FollowVO> findFollower(Integer follower) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FollowVO> findFollowed(Integer followed) {
		// TODO Auto-generated method stub
		return null;
	}

}

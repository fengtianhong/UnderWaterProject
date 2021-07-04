package com.grouptour.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface GroupTourDAO_interface {
	public void insert(GroupTourVO groupTourVO);
	public void update(GroupTourVO groupTourVO);
	public GroupTourVO findByPrimaryKey(Integer groupTourSN);
	public List<GroupTourVO> getAll();
	public List<GroupTourVO> getFrontendAll();
	public void attendGroup(Integer groupTourSN, Connection con);
	public void updateStatus(Integer groupTourSN, String status);
	// 篩選用
	public List<Integer> keywordFilter(String keyword);
	public List<Integer> locationFilter(String location);
	public List<Integer> allForFilter();
	
}

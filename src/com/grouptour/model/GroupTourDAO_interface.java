package com.grouptour.model;

import java.util.List;

public interface GroupTourDAO_interface {
	public void insert(GroupTourVO groupTourVO);
	public void update(GroupTourVO groupTourVO);
	public GroupTourVO findByPrimaryKey(Integer groupTourSN);
	public List<GroupTourVO> getAll();
}

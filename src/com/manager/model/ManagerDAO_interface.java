package com.manager.model;

public interface ManagerDAO_interface {
	
	public void insert(ManagerVO ManagerVO);
	public int update(String account,Integer status);
	public Boolean login(ManagerVO ManagerVO);
}

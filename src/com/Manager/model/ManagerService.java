package com.Manager.model;

public class ManagerService {
	
	private ManagerDAO_interface dao;
	
	ManagerVO vo = new ManagerVO();
	
	public ManagerService(){
		dao = new ManagerDAO();
	}
	
	public ManagerVO insertManagerVO(String account, String pwd, Integer status) {
		ManagerVO vo = new ManagerVO();
		vo.setAccount(account);
		vo.setStatus(status);
		return vo;
	}
	
	public ManagerVO updateManagerVO(String account,Integer status) {
		ManagerVO vo = new ManagerVO();
		vo.setAccount(account);
		vo.setStatus(status);
		return vo;
	}
}

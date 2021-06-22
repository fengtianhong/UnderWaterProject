package com.manager.model;

public class ManagerService {
	
	private ManagerDAO_interface dao;
	
	ManagerVO vo = new ManagerVO();
	
	public ManagerService(){
		dao = new ManagerDAO();
	}
	
	public ManagerVO insertManager(String account, String pwd, Integer status) {
		ManagerVO vo = new ManagerVO();
		vo.setAccount(account);
		vo.setStatus(status);
		return vo;
	}
	
	public ManagerVO updateManager(String account,Integer status) {
		ManagerVO vo = new ManagerVO();
		vo.setAccount(account);
		vo.setStatus(status);
		return vo;
	}
	
	public Boolean loginManager(String account,String pwd) {
		ManagerVO vo = new ManagerVO();
		vo.setAccount(account);
		vo.setPwd(pwd);
		
		return dao.login(vo);
	}
}

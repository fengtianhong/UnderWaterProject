package com.adorder.model;

import java.util.List;


public interface AdOrderDAO_interface {
	
	public void insert(AdOrderVO AdOrderVO);//新增
	public void update(AdOrderVO AdOrderVO);//更新
	public List<AdOrderVO> findByadUserID(Integer adUserID);//以帳號查詢
	public List<AdOrderVO> getAll();//後台查詢全部
	
}

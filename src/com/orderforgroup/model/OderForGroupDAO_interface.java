package com.orderforgroup.model;

import java.util.List;

public interface OderForGroupDAO_interface {
	public void insert(OderForGroupVO oderForGroupVO);
	public void update(OderForGroupVO oderForGroupVO);
	public OderForGroupVO findByPrimaryKey(Integer orderSN);
	public List<OderForGroupVO> getOrderByUserID(Integer userID);
	public List<OderForGroupVO> getAll();
	public List<Integer> checkRepeatOrder(Integer userID);	// 確認是否已報名用

}

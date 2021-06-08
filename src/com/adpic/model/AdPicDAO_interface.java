package com.adpic.model;

import java.util.List;

public interface AdPicDAO_interface {
	public int insert(AdPicVO AdPicVO);//新增
	public void update(AdPicVO AdPicVO);//以orderSN更新
	public List<AdPicVO> findByorderSN(Integer orderSN);//以訂單orderSN找圖片
	public List<AdPicVO> getAll();//找全部
}

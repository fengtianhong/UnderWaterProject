package com.collections.model;

import java.util.List;

public interface CollectionsDAO_interface {
	public void insert(CollectionsVO collectionsVO);
	public void update();	// no need
	public void delete(CollectionsVO collectionsVO);
	public List<Integer> findByUserID(Integer userID);	// // FK need imports 顯示會員收藏的套裝行程
	
}

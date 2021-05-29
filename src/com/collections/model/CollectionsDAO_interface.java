package com.collections.model;

import java.util.List;

public interface CollectionsDAO_interface {
	public void insert(CollectionsVO collectionsVO);
	public void delete(CollectionsVO collectionsVO);
	public List<Integer> findByUserID(Integer userID);	// // FK need imports ��ܷ|�����ê��M�˦�{
	
}

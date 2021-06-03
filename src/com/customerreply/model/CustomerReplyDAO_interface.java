package com.customerreply.model;

import java.util.List;
import java.util.Set;

public interface CustomerReplyDAO_interface {
	
	public List<String> getHistoryMsg(Integer userID);
	public void insertMsg(Integer userID, String Msg);
}

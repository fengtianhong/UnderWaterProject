package com.chat.model;

import java.sql.Timestamp;
import java.util.List;

public class ChatService {
	
	private ChatDAO_interface dao;
	
	public ChatService() {
		dao = new ChatDAO();
	}
	
	public ChatVO addChat(Integer fromAccount,Integer toAccount ,String content,Timestamp dateTime) {
	ChatVO chatvo = new ChatVO();
	chatvo.setFromAccount(fromAccount);
	chatvo.setToAccount(toAccount);
	chatvo.setContent(content);
	chatvo.setDateTime(dateTime);
	dao.insert(chatvo);
	return chatvo;
	}
	
	public List<ChatVO> getAll(Integer UserID){
		return dao.findByAccount(UserID);
	}
	
	public List<ChatVO> getAtoB(Integer UserA,Integer UserB){
		return dao.findByAtoB(UserA, UserB);
	}
}

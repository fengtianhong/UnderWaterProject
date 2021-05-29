package com.chat.model;

import java.util.List;

public interface ChatDAO_interface {
	public void insert(ChatVO ChatVO);
    public List<ChatVO> findByAccount(Integer UserID);//查詢帳號聊天紀錄
    public List<ChatVO> findByAtoB(Integer UserA,Integer UserB);//查詢帳號對帳號的聊天紀錄
//    public List<ChatVO> getAll(); 感覺不用getAll
}

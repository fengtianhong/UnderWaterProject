package com.chat.model;

import java.util.List;

public interface ChatDAO_interface {
	public void insert(ChatVO ChatVO);
    public void update(ChatVO ChatVO);
    public ChatVO findByPrimaryKey(Integer chatSN);
    public List<ChatVO> getAll();
}

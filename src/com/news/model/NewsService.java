package com.news.model;

import java.sql.Date;
import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();
	}
	
	public NewsVO addNews(String title,String content,byte[] image,Date newsDate,String newsFrom,String newsType) {
		NewsVO newsvo = new NewsVO();
		newsvo.setTitle(title);
		newsvo.setContent(content);
		newsvo.setImage(image);
		newsvo.setNewsDate(newsDate);
		newsvo.setNewsFrom(newsFrom);
		newsvo.setNewsType(newsType);
		dao.insert(newsvo);
			
		return newsvo;
	}
	
	public NewsVO updateNews(Integer newsSN,String title,String content,byte[] image,Date newsDate,String newsFrom,String newsType) {
		NewsVO newsvo = new NewsVO();
		newsvo.setNewsSN(newsSN);
		newsvo.setTitle(title);
		newsvo.setContent(content);
		newsvo.setImage(image);
		newsvo.setNewsDate(newsDate);
		newsvo.setNewsFrom(newsFrom);
		newsvo.setNewsType(newsType);
		dao.update(newsvo);
			
		return newsvo;
	}
	
	public void deleteNews(Integer newsSN) {
		dao.delete(newsSN);
	}

	
	public NewsVO getOneNewsVO(Integer newsSN) {
		return dao.findByPrimaryKey(newsSN);
	}
	
	public List<NewsVO> getAll(){
		return dao.getAll();
	}
	public List<NewsVO> getType(Integer newsType){
		return dao.getType(newsType);
	}
}

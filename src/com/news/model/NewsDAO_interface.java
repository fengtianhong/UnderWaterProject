package com.news.model;

import java.util.List;

public interface NewsDAO_interface {
    public void insert(NewsVO newsVO);
    public void update(NewsVO newsVO);
    public void delete(Integer newsSN);
    public NewsVO findByPrimaryKey(Integer newsSN);
    public List<NewsVO> getAll();
    public List<NewsVO> getType(Integer newsType);
}

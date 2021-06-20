package com.qa.model;

import java.util.List;

public interface QaDAO_interface {
	public void insert(QaVO qaVO);
	public void update(QaVO qaVO);
	public void delete(Integer questionSN);
	public QaVO findByPrimaryKey(Integer questionSN);
	public List<QaVO> getByMenu(String menu, String submenu);	// use menu and submenu to search
	public List<QaVO> getBySystem(String system);
	public List<QaVO> getPopularQuestion();
	public List<QaVO> getAll();

}

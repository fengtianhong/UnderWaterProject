package com.qa.model;

import java.util.List;

public class QaService {
	private QaDAO_interface dao;
	
	public QaService() {
		dao = new QaDAO();
	}
	public QaVO addQa(String menu, String submenu, String system, String question, String answer, Boolean popularQuestion) {
		QaVO vo = new QaVO();
		vo.setMenu(menu);
		vo.setSubmenu(submenu);
		vo.setSystem(system);
		vo.setQuestion(question);
		vo.setAnswer(answer);
		vo.setPopularQuestion(popularQuestion);
		dao.insert(vo);
		return vo;
	}
	public QaVO updateQa(Integer questionSN, String menu, String submenu, String system, String question, String answer, Integer clicks, Boolean popularQuestion, Integer popularQuestionSort) {
		QaVO vo = new QaVO();
		vo.setQuestionSN(questionSN);
		vo.setMenu(menu);
		vo.setSubmenu(submenu);
		vo.setSystem(system);
		vo.setQuestion(question);
		vo.setAnswer(answer);
		vo.setClicks(clicks);
		vo.setPopularQuestion(popularQuestion);
		vo.setPopularQuestionSort(popularQuestionSort);
		dao.update(vo);
		return vo;
		
	}
	public void deleteQa(Integer questionSN) {
		dao.delete(questionSN);
	}
	public QaVO getOne(Integer questionSN) {
		return dao.findByPrimaryKey(questionSN);
	}
	public List<QaVO> getByMenu(String menu, String submenu) {	// abandon
		return dao.getByMenu(menu, submenu);
	}
	public List<QaVO> getBySystem(String system) {		
		return dao.getBySystem(system);
	}
	public List<QaVO> getPopularQuestion() {
		return dao.getPopularQuestion();
	}
	public List<QaVO> getAll() {
		return dao.getAll();
	}
	

}

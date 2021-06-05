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
	public QaVO updateQa(Integer questionSN, String menu, String submenu, String system, String question, String answer, Boolean popularQuestion, Integer popularQuestionSort) {
		QaVO vo = new QaVO();
		vo.setQuestionSN(questionSN);
		vo.setMenu(menu);
		vo.setSubmenu(submenu);
		vo.setSystem(system);
		vo.setQuestion(question);
		vo.setAnswer(answer);
		vo.setPopularQuestion(popularQuestion);
		vo.setPopularQuestionSort(popularQuestionSort);
		dao.update(vo);
		return vo;
		
	}
	public void deleteQa(Integer questionSN) {
		dao.delete(questionSN);
	}
	public List<QaVO> getByMenu(String menu, String submenu) {
		return dao.getByMenu(menu, submenu);
	}
	public List<QaVO> getBySystem(String system) {
		return dao.getBySystem(system);
	}
	public List<QaVO> getPopularQuestion() {
		return dao.getPopularQuestion();
	}
	

}

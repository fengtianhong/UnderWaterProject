package com.qa.model;

public class QaVO implements java.io.Serializable{
	
	private Integer questionSN;
	private String menu;
	private String submenu;
	private String system;
	private String question;
	private String answer;
	private Integer clicks;
	private Boolean popularQuestion;
	private Integer popularQuestionSort;
	
	public Integer getQuestionSN() {
		return questionSN;
	}
	public void setQuestionSN(Integer questionSN) {
		this.questionSN = questionSN;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getSubmenu() {
		return submenu;
	}
	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public boolean isPopularQuestion() {
		return popularQuestion;
	}
	public void setPopularQuestion(Boolean popularQuestion) {
		this.popularQuestion = popularQuestion;
	}
	public Integer getPopularQuestionSort() {
		return popularQuestionSort;
	}
	public void setPopularQuestionSort(Integer popularQuestionSort) {
		this.popularQuestionSort = popularQuestionSort;
	}



}

package com.qa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QaService;
import com.qa.model.QaVO;

@WebServlet("/qa/qaSelect.do")
public class QaSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		QaService qaSvc = new QaService(); 
		String type;
		
		if("getPopular".equals(action)) {	
			try {
				List<QaVO> listNew = qaSvc.getPopularQuestion();
				type = "熱門問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);
				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
			}
		}
		if("getPoint".equals(action)) {	
			try {
				List<QaVO> listNew = qaSvc.getBySystem("1");
				type = "潛點地圖問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
			}
		}
		if("getGroup".equals(action)) {
			try {
				List<QaVO> listNew = qaSvc.getBySystem("2");
				type = "揪團及套裝行程問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);
				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
			}
		}
		if("getMall".equals(action)) {
			try {
				List<QaVO> listNew = qaSvc.getBySystem("3");
				type = "商城問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);
				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
		}
		}
		if("getForum".equals(action)) {
			try {
				List<QaVO> listNew = qaSvc.getBySystem("4");
				type = "論壇問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);
				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOthers".equals(action)) {
			try {
				List<QaVO> listNew = qaSvc.getBySystem("0");
				type = "其他問題";
				req.setAttribute("type", type);
				req.setAttribute("listNew", listNew);
				req.getRequestDispatcher("/qa/QA.jsp").forward(req, res);
				
			}catch(Exception e) {
				System.out.println("QA getPopular failure");
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/QA.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}

package com.news.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.news.model.NewsService;
import com.news.model.NewsVO;

@MultipartConfig
public class NewsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			// 檢查錯誤
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String title = req.getParameter("title");
				if (title == null || (title.trim().length() == 0)) {
					title = "";
					errorMsgs.add("請輸入新聞標題");
				}
				String content = req.getParameter("content");
				if (content == null || (content.trim().length() == 0)) {
					title = "";
					errorMsgs.add("請輸入新聞內容");
				}
				byte[] image = null;
				Part FileToPic = req.getPart("image");
				if (FileToPic.getSize() != 0) {
					InputStream in = FileToPic.getInputStream();
					image = new byte[in.available()];
					in.read(image);
					in.close();
				}
				java.sql.Date newsDate = null;
				try {
					newsDate = java.sql.Date.valueOf(req.getParameter("newsDate").trim());
				} catch (IllegalArgumentException e) {
					newsDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String newsFrom = req.getParameter("newsFrom");
				if (newsFrom == null || (newsFrom.trim().length() == 0)) {
					errorMsgs.add("請輸入新聞來源");

				}
				String newsType = req.getParameter("newsType");
				if (newsType == null || (newsType.trim().length() == 0)) {
					errorMsgs.add("請輸入新聞類型");

				}

				NewsVO newsVO = new NewsVO();
				newsVO.setTitle(title);
				newsVO.setContent(content);
				newsVO.setImage(image);
				newsVO.setNewsDate(newsDate);
				newsVO.setNewsFrom(newsFrom);
				newsVO.setNewsType(newsType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢查完畢
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(title, content, image, newsDate, newsFrom, newsType);
				RequestDispatcher failureView = req.getRequestDispatcher("/news/newslist.jsp");
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/news/newslist.jsp");
				failureView.forward(req, res);
			}

		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer newsSN = new Integer(req.getParameter("newsSN"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNewsVO(newsSN);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
				System.out.println(newsVO.getTitle());
				String url = "/news/updateNews.jsp";
				System.out.println("2");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				System.out.println("3");
				successView.forward(req, res);
				System.out.println("4");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/news/newslist.jsp");
				failureView.forward(req, res);
			}
		}

	}

}

package com.orderforgroup.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grouptour.model.GroupTourService;
import com.grouptour.model.GroupTourVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.orderforgroup.model.OderForGroupService;
import com.orderforgroup.model.OderForGroupVO;

@WebServlet("/orderforgroup/orderforgroup.do")
public class OrderForGroupServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {	// 訂單成立
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				Integer userID = new Integer(req.getParameter("userID").trim());		// FK有需要先驗證是否存在?
				Integer groupTourSN = new Integer(req.getParameter("groupTourSN").trim());	// FK有需要先驗證是否存在?
				
				
				Integer totalPrice = null;
				try {
					totalPrice = new Integer(req.getParameter("totalPrice"));
					if(totalPrice < 0 || totalPrice == null) {
						errMsg.add("請確認價格");
					}
				}catch(Exception e) {
					totalPrice = 0;		// 這個要???
					errMsg.add("請確認價格");
				}
				
				
				Date purchaseDate = null;
				try {
					LocalDate d = LocalDate.now(); // 取得今日
					purchaseDate = Date.valueOf(d);
				}catch(IllegalArgumentException e) {
					errMsg.add("購買日期錯誤");		// 這個要???
				}
				
				//insert failureA database error occured. Data truncation: Data too long for column 'phone' at row 1
				String phone = req.getParameter("phone").trim();
				String phoneReg = "^09[0-9]{8}$";
				if(phone == null || phone.length() == 0) {
					errMsg.add("請確認手機號碼");
				}else if(!phone.matches(phoneReg)) {
					errMsg.add("請確認手機號碼是否正確");
				}
				
				
				String personID = req.getParameter("personID").trim();
				String personIDReg = "^[A-Z][0-9]{9}$";
				if(personID == null || personID.length() == 0) {
					errMsg.add("請確認身分證字號");
				}else if(personID.matches(personIDReg)) {
					errMsg.add("請確認身分證字號是否正確，開頭需大寫");
				}
				
				
				Date birthdate = null;
				try {
					birthdate = Date.valueOf(req.getParameter("birthdate"));
				}catch(IllegalArgumentException e) {
					birthdate = new Date(System.currentTimeMillis());
					errMsg.add("請確認您的生日");
				}
				
				
				OderForGroupVO oderForGroupVO = new OderForGroupVO();
				oderForGroupVO = new OderForGroupVO();
				oderForGroupVO.setUserID(userID);
				oderForGroupVO.setGroupTourSN(groupTourSN);
				oderForGroupVO.setTotalPrice(totalPrice);
				oderForGroupVO.setPurchaseDate(purchaseDate);
				oderForGroupVO.setPhone(phone);
				oderForGroupVO.setPersonID(personID);
				oderForGroupVO.setBirthdate(birthdate);
				
				if(!errMsg.isEmpty()) {
					req.setAttribute("oderForGroupVO", oderForGroupVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/orderforgroup/addOrderForGroup.jsp");	// 重新insert 待確認
					failureView.forward(req, res);
					return;
				}
				
				OderForGroupService oderForGroupSvc = new OderForGroupService();
				oderForGroupSvc.insertOderForGroup(userID, groupTourSN, totalPrice, purchaseDate, phone, personID, birthdate);
// 這邊要讓套裝行程的報名人數加一
				
				req.setAttribute("Msg", "新增成功");
				RequestDispatcher successView = req.getRequestDispatcher("/collections/test_collections.jsp");	// 訂單成功頁面 待確認
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("insert failure"+ e.getMessage());
				errMsg.add("Exception occured"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/oderforgroup/addOrderForGroup.jsp");	// 重新insert 待確認
				failureView.forward(req, res);
			}
		}
		
		
		if("update".equals(action)) {			// 訂單修改
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				Integer userID = new Integer(req.getParameter("userID").trim());		// FK有需要先驗證是否存在?
				Integer groupTourSN = new Integer(req.getParameter("groupTourSN").trim());	// FK有需要先驗證是否存在?
				
				
				Integer totalPrice = null;
				try {
					totalPrice = new Integer(req.getParameter("totalPrice"));
					if(totalPrice < 0 || totalPrice == null) {
						errMsg.add("請確認價格");
					}
				}catch(Exception e) {
					totalPrice = 0;		// 這個要???
					errMsg.add("請確認價格");
				}
				
				
				Date purchaseDate = null;
				try {
					LocalDate d = LocalDate.now(); // 取得今日
					purchaseDate = Date.valueOf(d);
				}catch(IllegalArgumentException e) {
					errMsg.add("購買日期錯誤");		// 這個要???
				}
				
				
				String phone = req.getParameter("phone").trim();
				String phoneReg = "^09[0-9]{8}$";
				if(phone == null || phone.length() == 0) {
					errMsg.add("請確認手機號碼");
				}else if(!phone.matches(phoneReg)) {
					errMsg.add("請確認手機號碼是否正確");
				}
				
				
				String personID = req.getParameter("personID").trim();
				String personIDReg = "^[A-Z][0-9]{9}$";
				if(personID == null || personID.length() == 0) {
					errMsg.add("請確認身分證字號");
				}else if(personID.matches(personIDReg)) {
					errMsg.add("請確認身分證字號是否正確，開頭需大寫");
				}
				
				
				Date birthdate = null;
				try {
					birthdate = Date.valueOf(req.getParameter("birthdate"));
				}catch(IllegalArgumentException e) {
					birthdate = new Date(System.currentTimeMillis());
					errMsg.add("請確認您的生日");
				}
				
				
				OderForGroupVO oderForGroupVO = new OderForGroupVO();
				oderForGroupVO = new OderForGroupVO();
				oderForGroupVO.setUserID(userID);
				oderForGroupVO.setGroupTourSN(groupTourSN);
				oderForGroupVO.setTotalPrice(totalPrice);
				oderForGroupVO.setPurchaseDate(purchaseDate);
				oderForGroupVO.setPhone(phone);
				oderForGroupVO.setPersonID(personID);
				oderForGroupVO.setBirthdate(birthdate);
				
				if(!errMsg.isEmpty()) {
					req.setAttribute("oderForGroupVO", oderForGroupVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/orderforgroup");	// 重新insert 待確認
					failureView.forward(req, res);
					return;
				}
				
				OderForGroupService oderForGroupSvc = new OderForGroupService();
				oderForGroupSvc.insertOderForGroup(userID, groupTourSN, totalPrice, purchaseDate, phoneReg, personIDReg, birthdate);
				RequestDispatcher successView = req.getRequestDispatcher("/orderforgroup/test_collections.jsp");	// 訂單成功頁面 待確認(先回listOne)
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				System.out.println("insert failure"+ e.getMessage());
				errMsg.add("Exception occured"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/orderforgroup");	// 重新insert 待確認
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_ForUpdate".equals(action)) {		// 訂單修改 for後台還是前台? 多一個頁面參數?
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			try {
				Integer orderSN = new Integer(req.getParameter("orderSN"));

				OderForGroupService oderForGroupSvc = new OderForGroupService();
				OderForGroupVO oderForGroupVO = oderForGroupSvc.findByPrimaryKey(orderSN);
				
				req.setAttribute("oderForGroupVO", oderForGroupVO);
				RequestDispatcher successView = req.getRequestDispatcher("/orderforgroup/updateOrderForGroup.jsp"); // 更改訂單頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/orderforgroup/backendListAll");	// 回到選擇訂單頁面
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_ForOrder".equals(action)) {		// 報名
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			try {
				Integer userID = new Integer(req.getParameter("userID"));
				Integer groupTourSN = new Integer(req.getParameter("groupTourSN"));
				
				GroupTourService groupTourSvc = new GroupTourService();
				GroupTourVO groupTourVO = groupTourSvc.getOne(groupTourSN);
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getone(userID);
				HttpSession session = req.getSession();
				
// NOT YET 要加資格判斷
// 以及判斷是否有報名過(這個要在套裝行程顯示頁面?)			
				String certification = memberVO.getCertification();
				String certificationLimit = groupTourVO.getCertificationLimit();
				
				
				session.setAttribute("memberVO", memberVO);
				req.setAttribute("groupTourVO", groupTourVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/orderforgroup/addOrderForGroup.jsp"); // 下單頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/collections/test_collections.jsp");	// 回到套裝行程頁面
				failureView.forward(req, res);
			}
		}
		
		
		if("getAll_ForDisplay".equals(action)) {	// 會員訂單查詢 for後台還是前台? 多一個頁面參數?
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			try {
				Integer userID = new Integer(req.getParameter("userID"));
				OderForGroupService oderForGroupSvc = new OderForGroupService();
				List<OderForGroupVO> list = oderForGroupSvc.getOrderByUserID(userID);
				
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/orderforgroup/"); // 更改訂單頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/orderforgroup/");	// 回到選擇訂單頁面
				failureView.forward(req, res);
			}
		}
		
	}

}

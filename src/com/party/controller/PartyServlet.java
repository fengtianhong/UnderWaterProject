package com.party.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.party.model.PartyService;
import com.party.model.PartyVO;
import com.partymember.model.PartyMemberService;
import com.partymember.model.PartyMemberVO;

@MultipartConfig(fileSizeThreshold=2*1024*1024, maxFileSize=2*1024*1024, maxRequestSize=5*1024*1024)

public class PartyServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		PartyService partySvc = new PartyService();
		PartyMemberService partyMemberSvc = new PartyMemberService();
		PrintWriter out = res.getWriter();
		
// ========================= 會員使用畫面  =====================================
		
		//會員 從navbar 連到揪團總表
		if ("party".equals(action)) {
			HttpSession session = req.getSession();
			session.setAttribute("listBySearch", null);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
		}
		
		//會員 查詢 揪團總表(有下條件)
		if ("getAllBy".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("test");
			String keyword = req.getParameter("keyword");
			Integer pointSN = Integer.parseInt(req.getParameter("pointSN"));
			Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize"));
			List<PartyVO> listBySearch = partySvc.findBySearch(keyword, pointSN, partyMinSize);

			if (listBySearch.size() == 0) {
				errorMsgs.add("查無此條件資料，請重新查詢！");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
				successView.forward(req, res);				
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("listBySearch", listBySearch);
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
				successView.forward(req, res);
			}
		}
		
		//會員 查詢 揪團細節
		if ("partyDetail".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			
			List<PartyMemberVO> partyMemberList = partyMemberSvc.findByPartySN(partyVO.getPartySN());
			req.setAttribute("partyVO", partyVO);
			req.setAttribute("partyMemberList", partyMemberList);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
			successView.forward(req, res);
		}
		
		//會員 從揪團細節 回總表
		if ("goBackToList".equals(action)) {
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
		}
		
		//會員 從揪團細節 去報名
		if ("goRegister".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			HttpSession session = req.getSession();
			Integer partyMember = (Integer) session.getAttribute("userID");
			
			if (partyMember != null) { //若已登入過
				// 判斷是否已報名過
				List<PartyMemberVO> listAll = partyMemberSvc.findByPartyMember(partyMember);
				for (PartyMemberVO vo : listAll) {
					if (vo.getPartySN().equals(partySN)) {
						errorMsgs.add("您已報名過囉");	//已報名過
						RequestDispatcher faildView = req.getRequestDispatcher("/party/partyDetail.jsp");
						faildView.forward(req, res);
						return;
					}
				}
				// 沒有報名過
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyRegister.jsp");
				successView.forward(req, res);
			} else {
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyRegister.jsp");
				successView.forward(req, res);
			}
		}
		
//未完成	//會員 從報名 回上頁揪團細節
		if ("goBackToDetail".equals(action)) {
//			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
//			PartyVO partyVO = partySvc.findByPartySN(partySN);
			String partySN = req.getParameter("partySN");
			req.setAttribute("partySN", partySN);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
			successView.forward(req, res);
		}
		
		
//未完成	//會員 從報名頁面 提交報名表
		if ("submitRegistration".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			Integer partyMember = Integer.parseInt(req.getParameter("partyMember"));
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			Date birthDate = Date.valueOf(req.getParameter("birthDate"));
			String personID = req.getParameter("personID");
			String certification = req.getParameter("certification");
			//證照照片
			Part part = req.getPart("certificationPic");
			InputStream in = part.getInputStream();
			byte[] certificationPic = new byte[in.available()];
			in.read(certificationPic);
			in.close();
			String comment = req.getParameter("comment");
			
			PartyMemberVO pm1 = new PartyMemberVO();
			pm1.setPartySN(partySN);
			pm1.setPartyMember(partyMember);
			pm1.setGender(gender);
			pm1.setEmail(email);
			pm1.setPhone(phone);
			pm1.setBirthDate(birthDate);
			pm1.setPersonID(personID);
			pm1.setCertification(certification);
			pm1.setCertificationPic(certificationPic);
			pm1.setComment(comment);
			
//			try {
				partyMemberSvc.insert(pm1);
				System.out.println("新增成功");
//待確認持續登入狀態	
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyIJoin.jsp");
				successView.forward(req, res);	
//			} catch (SQLException e) {
//*****待確認怎麼抓這個例外
//				java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '400001-1' for key 'partymember.UK_PartyMember_partySN_partyMember'
//				System.out.println("重複報名");
//			out.println("<h2>***待做*** 將跳轉至會員後台頁面(查詢已報名)</h2>");
//out.println("<h2>***待做*** 重複報名抓不住例外</h2>");
//			}
			
			
		}
		
		if ("readyToHost".equals(action)) {
			try {
			Integer partyHost = Integer.parseInt(req.getParameter("partyHost"));
			String partyTitle = req.getParameter("partyTitle");
			Date regDate = Date.valueOf(req.getParameter("regDate"));
			Date closeDate = Date.valueOf(req.getParameter("closeDate"));
			Date startDate = Date.valueOf(req.getParameter("startDate"));
			Date endDate = Date.valueOf(req.getParameter("endDate"));
			Integer partyLocation = Integer.parseInt(req.getParameter("partyLocation"));
			Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize"));
			String partyDetail = req.getParameter("partyDetail");
			String status = "0";
			
			PartyVO pv1 = new PartyVO();
			pv1.setPartyHost(partyHost);
			pv1.setPartyTitle(partyTitle);
			pv1.setRegDate(regDate);
			pv1.setCloseDate(closeDate);
			pv1.setStartDate(startDate);
			pv1.setEndDate(endDate);
			pv1.setPartyLocation(partyLocation);
			pv1.setPartyMinSize(partyMinSize);
			pv1.setPartyDetail(partyDetail);
			pv1.setStatus(status);
			
			partySvc.insert(pv1);
			
			// 跳轉到無條件的總列表(新增的才會在最上面)
			List<PartyVO> listBySearch = null;
			req.setAttribute("listBySearch", listBySearch);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
// 跳出Party相關記得把session.invalidate();
		
		
// ================================= 管理者後台 ==================================
		
		//管理者 查詢揪團代碼
		if ("findByPartySN".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			
			if ("".equals(req.getParameter("partySN").trim())) {
				session.removeAttribute("findByPartySNLike");
				errorMsgs.add("請輸入欲查詢之揪團編號!");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
				successView.forward(req, res);
			} 
			
			Integer partySN = Integer.parseInt(req.getParameter("partySN").trim());
			List<PartyVO> findByPartySNLike = partySvc.findByPartySNLike(partySN);
			
			if (findByPartySNLike.size() == 0 ) {
				errorMsgs.add("查無此揪團編號，請重新查詢!");
				session.removeAttribute("findByPartySNLike");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
				successView.forward(req, res);
			} else {		
				session.setAttribute("findByPartySNLike", findByPartySNLike);
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
				successView.forward(req, res);
			}
			
			
		}
		
		//管理者 查看內容準備修改
		if ("updateParty".equals(action)) {
			
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyManageDetail.jsp");
			successView.forward(req, res);
			
		}
		
//未完成     //管理者 確認修改
		if ("submitUpdate".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN").trim());
			Integer partyHost = Integer.parseInt(req.getParameter("partyHost").trim());
			String partyTitle = req.getParameter("partyTitle");
			Date startDate = Date.valueOf(req.getParameter("startDate"));
			Date endDate = Date.valueOf(req.getParameter("endDate"));
			Date regDate = Date.valueOf(req.getParameter("regDate"));
			Date closeDate = Date.valueOf(req.getParameter("closeDate"));
			Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize").trim());
			Integer partyLocation = Integer.parseInt(req.getParameter("partyLocation").trim());
			String partyDetail = req.getParameter("partyDetail");
			String status = req.getParameter("status");
			
			PartyVO pv1 = new PartyVO();
			pv1.setPartySN(partySN);
			pv1.setPartyHost(partyHost);
			pv1.setPartyTitle(partyTitle);
			pv1.setStartDate(startDate);
			pv1.setEndDate(endDate);
			pv1.setRegDate(regDate);
			pv1.setCloseDate(closeDate);
			pv1.setPartyMinSize(partyMinSize);
			pv1.setPartyLocation(partyLocation);
			pv1.setPartyDetail(partyDetail);
			pv1.setStatus(status);
			
			partySvc.update(pv1);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHost.jsp");
			successView.forward(req, res);
		}
		
		//管理者 放棄修改 返回前頁
		if ("goBackToManage".equals(action)) {
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
			successView.forward(req, res);
		}
		
		
// ================================= 使用者(會員)後台 ==================================
		
		//使用者 查詢自己主揪的團 細節/更改/審核參加資格
		if ("partyIHostDetail".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			List<PartyMemberVO> partyMembers = partyMemberSvc.findByPartySN(partySN);
			req.setAttribute("partyMembers", partyMembers);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHostDetail.jsp");
			successView.forward(req, res);
		}
		
		//使用者 放棄修改 自己主揪的團 => 用前端處理 history.back()
//		if ("goBackToPartyIHost".equals(action)) {
//			RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHost.jsp");
//			successView.forward(req, res);
//		}
		
		//使用者 審核自己主揪的團
		if ("updatePartyMemberStatus".equals(action)) {
			Integer partyMemberSN = Integer.parseInt(req.getParameter("partyMemberSN"));
			String status = req.getParameter("status");
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));			
			//接受或拒絕 更新狀態
			partyMemberSvc.updateStatus(partyMemberSN, status);

			System.out.println("核可人數= " + partyMemberSvc.findByPartySNAndStatus(partySN, "1").size());
			System.out.println("最低人數= " + partySvc.findByPartySN(partySN).getPartyMinSize());
			
			PartyVO partyVO = partySvc.findByPartySN(partySN);
//待補	判斷人數足夠變更狀態!
			if (partyMemberSvc.findByPartySNAndStatus(partySN, "1").size() == partyVO.getPartyMinSize()) {
				partyVO.setStatus("1");
				partySvc.update(partyVO);
			}
			
			//以下改用AJAX
//			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
//			PartyVO partyVO = partySvc.findByPartySN(partySN);
//			req.setAttribute("partyVO", partyVO);
			
//			List<PartyMemberVO> partyMembers = partyMemberSvc.findByPartySN(partySN);
//			req.setAttribute("partyMembers", partyMembers);
			
//			RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHostDetail.jsp");
//			successView.forward(req, res);

//如果需要錯誤判斷  out.print("qq"); 
			
		}
		//使用者 查詢自己參加的揪團細節 
		if ("partyIJoinDetail".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
			successView.forward(req, res);
		}

	}
}

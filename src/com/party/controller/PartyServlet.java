package com.party.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
		HttpSession session = req.getSession();
		
// ========================= 會員使用畫面  =====================================
		
		// 從navbar 連到揪團總表(會看到全部)
		if ("party".equals(action)) {
			session.setAttribute("listBySearch", null);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
		}
		
		// 下條件查詢 揪團總表
		if ("getAllBy".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<PartyVO> listBySearch = null;

			try {
				//空白或預設就是搜尋全部
				String keyword = req.getParameter("keyword").trim();  // 搜尋標題or揪團編號
				Integer pointSN = Integer.parseInt(req.getParameter("pointSN"));  // 用選的
				Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize"));  // 用選的
				listBySearch = partySvc.findBySearch(keyword, pointSN, partyMinSize);
				
				if (listBySearch.size() == 0) {   // 無資料
					errorMsgs.add("查無此條件資料，請重新查詢！");
					session.removeAttribute("listBySearch");
				} else {
					session.setAttribute("listBySearch", listBySearch);
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
				successView.forward(req, res);	
				
			} catch(Exception e) {
				errorMsgs.add("按條件搜尋失敗: " + e.getMessage());
				session.removeAttribute("listBySearch");
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyList.jsp");
				failureView.forward(req, res);	
			}
		}
		
		// 查詢 揪團細節
		if ("partyDetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN"));  // hidden input
				PartyVO partyVO = partySvc.findByPartySN(partySN);
				req.setAttribute("partyVO", partyVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("查詢揪團詳細資料失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyList.jsp");
				failureView.forward(req, res);	
			}
		}
		
		// 從查詢揪團細節 回總表 => 改用js
//		if ("goBackToList".equals(action)) {
//			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
//			successView.forward(req, res);
//		}
		
		// 報名去 (前往報名)
		if ("goRegister".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN"));  // hidden input
				PartyVO partyVO = partySvc.findByPartySN(partySN);
				session.setAttribute("partyVO", partyVO);	// 有filter

				Integer partyMember = (Integer) session.getAttribute("userID");
				if (partyMember != null) { //若已登入過
					// 判斷是否已報名過
					List<PartyMemberVO> listAll = partyMemberSvc.findByPartyMember(partyMember);
					for (PartyMemberVO vo : listAll) {
						if (vo.getPartySN().equals(partySN)) {
							//已報名過
							errorMsgs.add("您已報名過囉");	
							RequestDispatcher failureView = req.getRequestDispatcher("/party/partyDetail.jsp");
							failureView.forward(req, res);
							return;
						}
					}
					
					//判斷是否為主揪
					Integer partyHost = partySvc.findByPartySN(partySN).getPartyHost();
					if (partyHost.equals(partyMember)) {
						errorMsgs.add("您是主揪，不需要報名喔!");	
						RequestDispatcher failureView = req.getRequestDispatcher("/party/partyDetail.jsp");
						failureView.forward(req, res);
						return;
					}
					
					// 沒有報名過
					RequestDispatcher successView = req.getRequestDispatcher("/party/partyRegister.jsp");
					successView.forward(req, res);
				} else {
					// 尚未登入
					RequestDispatcher successView = req.getRequestDispatcher("/party/partyRegister.jsp");
					successView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add("前往報名頁面失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyList.jsp");
				failureView.forward(req, res);	
			}
		}
		
		// 從填寫報名資訊 回上頁  揪團細節 => 改用js
//		if ("goBackToDetail".equals(action)) {
//		}
		
		
//未完成	//會員 從報名頁面 提交報名表
		if ("submitRegistration".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN").trim());  // hidden input
				Integer partyMember = Integer.parseInt(req.getParameter("partyMember"));  // hidden input
				String gender = req.getParameter("gender");  // hidden input
				
				String email = req.getParameter("email");
				if (email == null || email.trim().length() == 0 || !email.contains("@") || !email.contains(".")) {
					errorMsgs.add("輸入email格式不正確");
				}
				
				String phone = req.getParameter("phone").trim();  // HTML input驗證 (reg)
				if (phone == null || phone.length() == 0) {
					errorMsgs.add("輸入手機號碼格式不正確");
				}

				Date birthDate = null;
				try {
					birthDate = Date.valueOf(req.getParameter("birthDate"));
				} catch (IllegalArgumentException ie) {
					birthDate = null;	// 允許不輸入
				}
				
				String personID = req.getParameter("personID").trim();  // 可以不輸入
				String personIDReg = "^[A-Z]{1}[12]{1}[0-9]{8}$";
				if (personID.length() != 0) { // 如果填寫了 就要符合格式
					if (!personID.matches(personIDReg)) {
						errorMsgs.add("輸入身份證字號格式不正確");
					}
				}
				
				String certification = req.getParameter("certification");
				
				//證照照片
				Part part = req.getPart("certificationPic");
				String fileType = part.getContentType();
				InputStream in = null;
				byte[] certificationPic = null;
				
				if (fileType.equals("application/octet-stream")) {
					//沒上傳
					if (session.getAttribute("certificationPic") != null) {  // 卻有之前資料
						certificationPic = (byte[]) session.getAttribute("certificationPic");
					}
				} else if (fileType.startsWith("image")) {
					in = part.getInputStream();
					certificationPic = new byte[in.available()];
					in.read(certificationPic);
					in.close();
					session.setAttribute("certificationPic", certificationPic);
				} else {  
					// 上傳非圖檔
					errorMsgs.add("上傳證照圖片檔案格式有誤" + fileType);
					}
						
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
				
				req.setAttribute("partyMemberVO", pm1);
				
				if (!errorMsgs.isEmpty()) {
					// 如果報名有輸入錯誤, 須回傳原本partyVO
					PartyVO partyVO = partySvc.findByPartySN(partySN);
					req.setAttribute("partyVO", partyVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/party/partyRegister.jsp");
					failureView.forward(req, res);
					return;
				}

				partyMemberSvc.insert(pm1);
				
				session.removeAttribute("certificationPic");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyIJoin.jsp");
				successView.forward(req, res);	
				
			} catch (Exception e) {
				errorMsgs.add("報名失敗: " + e.getMessage());
				System.out.println("partyservlet #submitRegistration = " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		// 發起揪團
		if ("readyToHost".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partyHost = Integer.parseInt(req.getParameter("partyHost"));  // hidden input
				String partyTitle = req.getParameter("partyTitle");
				String partyTitleReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9,!@#$%^&*()_+=)\\/?<>;:\'\"|. ˋˊ~]{2,}$";
				
				if (partyTitle == null || partyTitle.trim().length() == 0) {
					errorMsgs.add("標題不可空白!");
				} else if (!partyTitle.matches(partyTitleReg)) {
					errorMsgs.add("標題至少要有兩個中/英文字, 不可以注音文或包含 [] {}");
				}
				
				Date startDate = null;
				try {
					startDate = Date.valueOf(req.getParameter("startDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始日期");
				}
				
				Date endDate = null;
				try {
					endDate = Date.valueOf(req.getParameter("endDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束日期");
				}
				
				Date regDate = null;
				try {
					regDate = Date.valueOf(req.getParameter("regDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入開放報名日期");
				}
				
				Date closeDate = null;
				try {
					closeDate = Date.valueOf(req.getParameter("closeDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入截止報名日期");
				}
				
				if (regDate.after(closeDate)) {
					errorMsgs.add("開始報名日期不可晚於截止報名日期");
				}
				
				if (closeDate.after(startDate)) {
					errorMsgs.add("截止報名日期不可晚於揪團開始日期");
				}
				
				if (startDate.after(endDate)) {
					errorMsgs.add("揪團開始日期不可晚於揪團結束日期");
				}
				
				
				Integer partyLocation = Integer.parseInt(req.getParameter("partyLocation"));  // hidden input
				Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize"));  // hidden input
				String partyDetail = req.getParameter("partyDetail");  // 可填可不填
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
				
				if (!errorMsgs.isEmpty()) {
					// 如果發起內容有輸入錯誤, 須回傳當下已填寫的partyVO
					req.setAttribute("partyVO", pv1); 
					RequestDispatcher failureView = req.getRequestDispatcher("/party/HostParty.jsp");
					failureView.forward(req, res);
					return;
				}
				
				partySvc.insert(pv1);
				
				// 跳轉到無條件的總列表(新增的才會在最上面)
				session.setAttribute("listBySearch", null);
				req.removeAttribute("partyVO");
				
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
				successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add("發起揪團失敗: " + e.getMessage());
				System.out.println("partyservlet #readyToHost = " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/HostParty.jsp");
				failureView.forward(req, res);
			}
		}
		
// ================================= 使用者(會員)後台 ==================================

		//使用者 查詢自己主揪的團 細節/更改/審核參加資格
		if ("partyIHostDetail".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));  // hidden input
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			List<PartyMemberVO> partyMembers = partyMemberSvc.findByPartySN(partySN);
			req.setAttribute("partyMembers", partyMembers);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHostDetail.jsp");
			successView.forward(req, res);
		}
		
		//使用者 放棄修改 自己主揪的團 => 用前端處理 history.back()
//				if ("goBackToPartyIHost".equals(action)) {
//					RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHost.jsp");
//					successView.forward(req, res);
//				}
		
//未完成日期部分
		//會員後台 確認修改
		if ("submitUpdateByMember".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN").trim());  // hidden input
				Integer partyHost = Integer.parseInt(req.getParameter("partyHost").trim());  // hidden input
				
				String partyTitle = req.getParameter("partyTitle");
				String partyTitleReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9,!@#$%^&*()_+=)\\/?<>;:\'\"|. ˋˊ~]{2,}$";
				if (partyTitle == null || partyTitle.trim().length() == 0) {
					errorMsgs.add("標題不可空白!");
				} else if (!partyTitle.matches(partyTitleReg)) {
					errorMsgs.add("標題至少要有兩個中/英文字, 不可以注音文或包含 [] {}");
				}
				
				Date startDate = null;
				try {
					startDate = Date.valueOf(req.getParameter("startDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始日期");
				}
				
				Date endDate = null;
				try {
					endDate = Date.valueOf(req.getParameter("endDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束日期");
				}
				
				Date regDate = null;
				try {
					regDate = Date.valueOf(req.getParameter("regDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入開放報名日期");
				}
				
				Date closeDate = null;
				try {
					closeDate = Date.valueOf(req.getParameter("closeDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入截止報名日期");
				}
				
				if (regDate.after(closeDate)) {
					errorMsgs.add("開始報名日期不可晚於截止報名日期");
				}
				
				if (closeDate.after(startDate)) {
					errorMsgs.add("截止報名日期不可晚於揪團開始日期");
				}
				
				if (startDate.after(endDate)) {
					errorMsgs.add("揪團開始日期不可晚於揪團結束日期");
				}
				
				Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize").trim());  // 下拉式選單
				Integer partyLocation = Integer.parseInt(req.getParameter("partyLocation").trim());  // 下拉式選單
				String partyDetail = req.getParameter("partyDetail");  // 可填可不填
				String status = req.getParameter("status");  // 下拉式選單
				
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
				
				if (!errorMsgs.isEmpty()) {
					// 如果修改內容有輸入錯誤, 須回傳當下已填寫的partyVO
					req.setAttribute("partyVO", pv1); 
					RequestDispatcher failureView = req.getRequestDispatcher("/party/partyIHostDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				partySvc.update(pv1);
				
				// 跳轉回總列表
				errorMsgs.add("修改成功!");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHost.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("管理者後台修改揪團失敗: " + e.getMessage());
				System.out.println("partyservlet #submitUpdate = " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyManage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		//使用者 審核團員名單 => AJAX呼叫controller
		if ("updatePartyMemberStatus".equals(action)) {
			List<String> errorMsgs2 = new LinkedList<String>();
			req.setAttribute("errorMsgs2", errorMsgs2);
			
			try {
				Integer partyMemberSN = Integer.parseInt(req.getParameter("partyMemberSN"));
				String status = req.getParameter("status");
				Integer partySN = Integer.parseInt(req.getParameter("partySN"));			
				//接受或拒絕 更新狀態
				partyMemberSvc.updateStatus(partyMemberSN, status);
	
				int nowSize = partyMemberSvc.findByPartySNAndStatus(partySN, "1").size();
				int partyMinSize = partySvc.findByPartySN(partySN).getPartyMinSize();
	//			System.out.println("核可人數= " + nowSize);
	//			System.out.println("最低人數= " + partyMinSize);
				
				PartyVO partyVO = partySvc.findByPartySN(partySN);
	//待補	判斷人數足夠變更狀態!
				if (nowSize == partyMinSize) {
					partyVO.setStatus("1");
					partySvc.update(partyVO);
					out.print("full");
				} else {
					out.print("continue");
				}
				// 前端顯示會員已被接受/拒絕 => 改用AJAX
	//					Integer partySN = Integer.parseInt(req.getParameter("partySN"));
	//					PartyVO partyVO = partySvc.findByPartySN(partySN);
	//					req.setAttribute("partyVO", partyVO);
				
	//					List<PartyMemberVO> partyMembers = partyMemberSvc.findByPartySN(partySN);
	//					req.setAttribute("partyMembers", partyMembers);
				
	//					RequestDispatcher successView = req.getRequestDispatcher("/party/partyIHostDetail.jsp");
	//					successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs2.add("審核報名會員是否通過/拒絕 失敗: " + e.getMessage());
				System.out.println("partyservlet #updatePartyMemberStatus= " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyIHost.jsp");
				failureView.forward(req, res);
			}
				
		}
		
		//使用者 查詢自己參加的揪團細節 
		if ("partyIJoinDetail".equals(action)) {
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN"));  // hidden input
				PartyVO partyVO = partySvc.findByPartySN(partySN);
				req.setAttribute("partyVO", partyVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("partyservlet #partyIJoinDetail = " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyIJoin.jsp");
				failureView.forward(req, res);
			}
		}

		
// 跳出Party相關記得把session.invalidate();
		
		
// ================================= 管理者後台 ==================================
		
		//管理者 查詢揪團代碼
		if ("findByPartySN".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			if (req.getParameter("partySN") == null || (req.getParameter("partySN").trim()).length() == 0 ) {
				session.removeAttribute("findByPartySNLike");
				errorMsgs.add("請輸入欲查詢之揪團編號!");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
				successView.forward(req, res);
				return;
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
		
		//管理者 查看內容 前往修改
		if ("updateParty".equals(action)) {
			
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));  //hidden input
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyManageDetail.jsp");
			successView.forward(req, res);
			
		}
		
//未完成日期部分
		//管理者 確認修改
		if ("submitUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer partySN = Integer.parseInt(req.getParameter("partySN").trim());  // hidden input
				Integer partyHost = Integer.parseInt(req.getParameter("partyHost").trim());  // hidden input
				
				String partyTitle = req.getParameter("partyTitle");
				String partyTitleReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9,!@#$%^&*()_+=)\\/?<>;:\'\"|. ˋˊ~]{2,}$";
				if (partyTitle == null || partyTitle.trim().length() == 0) {
					errorMsgs.add("標題不可空白!");
				} else if (!partyTitle.matches(partyTitleReg)) {
					errorMsgs.add("標題至少要有兩個中/英文字, 不可以注音文或包含 [] {}");
				}
				
				Date startDate = null;
				try {
					startDate = Date.valueOf(req.getParameter("startDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動開始日期");
				}
				
				Date endDate = null;
				try {
					endDate = Date.valueOf(req.getParameter("endDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入活動結束日期");
				}
				
				Date regDate = null;
				try {
					regDate = Date.valueOf(req.getParameter("regDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入開放報名日期");
				}
				
				Date closeDate = null;
				try {
					closeDate = Date.valueOf(req.getParameter("closeDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入截止報名日期");
				}
				
				if (regDate.after(closeDate)) {
					errorMsgs.add("開始報名日期不可晚於截止報名日期");
				}
				
				if (closeDate.after(startDate)) {
					errorMsgs.add("截止報名日期不可晚於揪團開始日期");
				}
				
				if (startDate.after(endDate)) {
					errorMsgs.add("揪團開始日期不可晚於揪團結束日期");
				}
				
				Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize").trim());  // 下拉式選單
				Integer partyLocation = Integer.parseInt(req.getParameter("partyLocation").trim());  // 下拉式選單
				String partyDetail = req.getParameter("partyDetail");  // 可填可不填
				String status = req.getParameter("status");  // 下拉式選單
				
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
				
				if (!errorMsgs.isEmpty()) {
					// 如果修改內容有輸入錯誤, 須回傳當下已填寫的partyVO
					req.setAttribute("partyVO", pv1); 
					RequestDispatcher failureView = req.getRequestDispatcher("/party/partyManageDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				partySvc.update(pv1);
				
				// 跳轉回總列表
				errorMsgs.add("修改成功!");
				session.removeAttribute("findByPartySNLike");
				RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("管理者後台修改揪團失敗: " + e.getMessage());
				System.out.println("partyservlet #submitUpdate = " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyManage.jsp");
				failureView.forward(req, res);
			}
		}
		
		//管理者 放棄修改 返回前頁 => 改用前端直接導向
//		if ("goBackToManage".equals(action)) {
//			RequestDispatcher successView = req.getRequestDispatcher("/party/partyManage.jsp");
//			successView.forward(req, res);
//		}
		

	}
}

package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("有跑到1");
		
		
		if("getOne_For_Update".equals(action)) {
//			System.out.println("有跑到2");
			try {
				Integer userID = new Integer (req.getParameter("userid"));
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getone(userID);
				
				req.setAttribute("memberVO", memberVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/member/webManagerSystemChange.jsp");
				successView.forward(req, res);
//				System.out.println("有跑到change");
				return;
			}catch(Exception e) {
				e.printStackTrace();
				//錯誤導到指定的JSP
				RequestDispatcher failureView = req.getRequestDispatcher("/member/webManagerSystem.jsp");
				failureView.forward(req, res);
//				System.out.println("跑到壞掉");
				return;
			}
		}
			
			
			
			
			
			//update
			if("update".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					Integer userId =  new Integer(req.getParameter("userid").trim());					
					String account = req.getParameter("account").trim();
					
					String nickName = null;
					try {
						nickName = req.getParameter("nickname").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認暱稱");
					}
					
					String gender = null;
					try {
						gender = req.getParameter("gender").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認性別");
					}
					
					String userName = null;
					try {
						userName = req.getParameter("username").trim();
					}catch(Exception e) {
						errorMsgs.add("");
					}
					
					Date birthDate = null;
					try {
						birthDate =  Date.valueOf(req.getParameter("birthdate").trim());
					}catch(IllegalArgumentException e) {
						birthDate = Date.valueOf("1970-01-01");
					}
					
					String phone= null;
					try {
						phone = req.getParameter("phone").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認電話");
					}
					
					String certification = null;
					try {
						certification = req.getParameter("certification").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認證照");
					}
					
					
					//證照圖片
					byte[] certificationPic = null;
					InputStream in = null;
					
					try {
						Part part = req.getPart("certificationPic");
						in = part.getInputStream();
						certificationPic = new byte[in.available()];
						in.read(certificationPic);
						
						if(certificationPic.length == 0) {//未修正則存取原圖
							MemberService membersvc = new MemberService();
							MemberVO originalVO = membersvc.getone(userId);
							certificationPic = originalVO.getCertificationPic();
						}
					}catch(Exception e) {
						e.printStackTrace();
						errorMsgs.add("證照讀取錯誤" + e.getMessage());
					}finally{
						in.close();
					}
					
					//個人照片
					byte[] personPhoto = null;
//					File noimage = new File("/member/images/noimage.PNG");
					
					try {
						Part part = req.getPart("personPhoto");
						in = part.getInputStream();
						personPhoto = new byte[in.available()];
						in.read(personPhoto);
						
						if(personPhoto.length == 0) {//未修正則存取原圖
							MemberService membersvc = new MemberService();
							MemberVO originalVO = membersvc.getone(userId);
							personPhoto = originalVO.getPersonPhoto();
						}
					}catch(Exception e) {
						e.printStackTrace();
						errorMsgs.add("個人照片讀取錯誤" + e.getMessage());
					}finally{
						in.close();
					}
					
					String personId = null;
					try {
						personId = req.getParameter("personid").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認身份證字號");
					}
					
					String address = null;
					try {
						address =  req.getParameter("address").trim();
					}catch(Exception e) {
						errorMsgs.add("請確認地址");
					}
								
					Timestamp createTime =  Timestamp.valueOf(req.getParameter("createtime"));
					
					Integer status = null;
					try {
						status = new Integer(req.getParameter("status").trim());
					}catch(Exception e) {
						errorMsgs.add("請確認帳號狀態");
					}
					
					
					
					Integer ratepeople = null;
					try {
						ratepeople = new Integer(req.getParameter("ratepeople").trim());
					}catch(Exception e) {
						errorMsgs.add("請確認評價人數");
					}
					
					Integer ratepoint = null;
					try {
						ratepoint = new Integer(req.getParameter("ratepoint").trim());
					}catch(Exception e) {
						errorMsgs.add("請確認評價分數");
					}
					
					
					
					Timestamp updateTime = null;
					if(errorMsgs.isEmpty()) {
						updateTime = new Timestamp(System.currentTimeMillis());
					}else {
						updateTime = Timestamp.valueOf(req.getParameter("updatetime"));
					}
					
					MemberVO memberVO = new MemberVO();
					memberVO.setUserID(userId);
					memberVO.setAccount(account);
					memberVO.setNickName(nickName);
					memberVO.setUserName(userName);
					memberVO.setBirthDate(birthDate);
					memberVO.setPhone(phone);
					memberVO.setCertification(certification);
					memberVO.setCertificationPic(certificationPic);
					memberVO.setPersonID(personId);
					memberVO.setAddress(address);
					memberVO.setCreateTime(createTime);
					memberVO.setStatus(status);
					memberVO.setUpDateTime(updateTime);
					memberVO.setRatePeople(ratepeople);
					memberVO.setRatePeople(ratepoint);
					memberVO.setPersonPhoto(personPhoto);
					
					if(!errorMsgs.isEmpty()) {
//						System.out.println("我有跑到isEmpty");
						req.setAttribute("membervo", memberVO);
						String url = "/member/webManagerSystemChange.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						
						return;
					}
					
					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.updateMember(userId, account, nickName, userName, gender,
							birthDate, phone, certification, certificationPic, personId, address,
							createTime, status, updateTime, ratepeople, ratepoint, personPhoto);
					req.setAttribute("membervo", memberVO);
//					System.out.println("我有跑到membersvc");
					String url = "/member/webManagerSystem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
		if("search".equals(action)) {
//			System.out.println("search有跑到");
			List<String> errorMsgs = new LinkedList<String>();
			
			try {
				req.setAttribute("errorMsgs", errorMsgs);
				MemberService memberSvc = new MemberService();
				String keyword = req.getParameter("keyword");
				String url = "/member/webManagerSystem.jsp";
				String account = req.getParameter("keyword");
				String nickName = req.getParameter("keyword");
				String userName = req.getParameter("keyword");
				String address = req.getParameter("keyword");
				System.out.println(keyword);
				List<MemberVO> list = memberSvc.managerFindBySearch(account, nickName, userName, address);
				System.out.println(list);
				if(list.size() == 0) {
					errorMsgs.add("查無資料");
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}else {
					req.setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}
			}catch(Exception e){
				
			}
			
		}
		
	}

}

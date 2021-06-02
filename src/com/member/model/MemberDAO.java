package com.member.model;

import java.sql.*;
import java.util.List;

import util.Util;

public class MemberDAO implements MemberDAO_interface{
	
//	private static final String INSERT_STMT = "INSERT INTO Member (userID, account, pwd,nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address, createTime, status, upDateTime, ratePeople, ratePoint) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
//	private static final String UPDATE_STMT = "UPDATE Member set account=?, pwd=?, NickName=?, userName=?, gender=?, birthDate=?, phone=?, Certification=?, CertificationPic=?, personID=?, address=?, createTime=?,  status=?, updateTime=?, ratePeople=?, ratePoint=?, where userID = ?";
//	private static final String findBYPrimaryKey = "SELECT * FROM Member where userid=?";
//	private static final String GET_ALL_STMT = "SELECT * FROM Member ";
	
//	public static void main(String[] args) {
		//測試insert
//		MemberVO vo = new MemberVO();
//		vo.setUserID(00000004);
//		vo.setAccount("tibame");
//		vo.setPwd("12345678");
//		vo.setNickName("阿宏");
//		vo.setUserName("馮天宏4帥哥");
//		vo.setGender("男");
//		vo.setBirthDate(Date.valueOf("2010-10-10"));
//		vo.setPhone("0912345678");
//		vo.setCertification("A1");
//		vo.setCertificationPic(null);
//		vo.setPersonID("F123456789");
//		vo.setAddress("花蓮縣壽豐鄉中山路6X號");
//		vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		vo.setStatus("0");
//		vo.setUpDateTime(new Timestamp(System.currentTimeMillis()));
//		vo.setRatePeople(0);
//		vo.setRatePoint(0);
//		
//		MemberDAO dao = new MemberDAO();
//		dao.insert(vo);
		//測試insert
//		System.out.println("已加入成功");
//	}	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動成功");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

@Override
public void insert(MemberVO MemberVO) {
	// TODO Auto-generated method stub
	
}

@Override
public void update(MemberVO MemberVO) {
	// TODO Auto-generated method stub
	
}

@Override
public MemberVO findBYPrimaryKey(Integer userID) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<MemberVO> getAll() {
	// TODO Auto-generated method stub
	return null;
}
	
//	@Override
//	public void insert(MemberVO MemberVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			System.out.println("連線成功");
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setInt(1, MemberVO.getUserID());
//			pstmt.setString(2, MemberVO.getAccount());
//			pstmt.setString(3, MemberVO.getPwd());
//			pstmt.setString(4, MemberVO.getNickName());
//			pstmt.setString(5, MemberVO.getUserName());
//			pstmt.setString(6, MemberVO.getGender());
//			pstmt.setDate(7, MemberVO.getBirthDate());
//			pstmt.setString(8, MemberVO.getPhone());
//			pstmt.setString(9, MemberVO.getCertification());
//			pstmt.setBytes(10, MemberVO.getCertificationPic());
//			pstmt.setString(11, MemberVO.getPersonID());
//			pstmt.setString(12, MemberVO.getAddress());
//			pstmt.setTimestamp(13, MemberVO.getCreateTime());
//			pstmt.setString(14, MemberVO.getStatus());
//			pstmt.setTimestamp(15, MemberVO.getUpDateTime());
//			pstmt.setInt(16, MemberVO.getRatePeople());
//			pstmt.setInt(17, MemberVO.getRatePoint());
//			
//			pstmt.executeUpdate();
//			
//		}catch(SQLException se) {
//			se.printStackTrace();
//		}finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			
//			if(con != null) {
//				try {
//					con.close();
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}
//	@Override
//	public void update(MemberVO MemberVO) {//未做完
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE_STMT);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	@Override
//	public MemberVO findBYPrimaryKey(Integer userID) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE_STMT);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
//	@Override
//	public List<MemberVO> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	

	
	
}

package com.admember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import util.Util;

public class adMemberDAO implements adMemberDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO adMember (AdUserID, Account, Pwd, CreateTime, Status) VALUES (?, ?, ?, ?, ? )";
	private static final String UPDATE_STMT = "UPDATE adMember set Pwd=?, status=? where account = ?";
	private static final String FINDBYACCOUNT_STMT = "select * from adMember where account = ?";
	
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動成功");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {

//		//insert 測試OK
//		adMemberVO vo = new adMemberVO();
//		vo.setAdUserID(00000002);
//		vo.setAccount("4125252");
//		vo.setPwd("123456");
//		vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		vo.setStatus(0);
//		
//		adMemberDAO dao = new adMemberDAO();
//		dao.insert(vo);
//		System.out.println("已加入成功");
//		//insert 測試OK
		
		//update 測試OK
//		adMemberDAO dao = new adMemberDAO();
//		dao.update("4125252", "55123", 1);
//		System.out.println("更新成功");
		//update 測試OK
		
		//find account 怎麼佐證有查到?
		adMemberDAO dao = new adMemberDAO();
		adMemberVO vo = dao.findBYaccount(4125252);
		//find account
	}

	@Override
	public void insert(adMemberVO adMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, adMemberVO.getAdUserID());
			pstmt.setString(2, adMemberVO.getAccount());
			pstmt.setString(3, adMemberVO.getPwd());
			pstmt.setTimestamp(4, adMemberVO.getCreateTime());
			pstmt.setInt(5, adMemberVO.getStatus());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int update(String account, String pwd, Integer status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, pwd);
			pstmt.setInt(2, status);
			pstmt.setString(3, account);

			i = pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	

	@Override
	public adMemberVO findBYaccount(Integer account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		adMemberVO vo = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYACCOUNT_STMT);
			System.out.println("連線成功");
			
			pstmt.setInt(1, account);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new adMemberVO();
				vo.setAccount(rs.getString("account"));
				vo.setCreateTime(rs.getTimestamp("createTime"));
				vo.setStatus(rs.getInt("status"));
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}

}

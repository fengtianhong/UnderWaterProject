package com.admember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import util.Util;

public class AdMemberDAO implements AdMemberDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO adMember (Account, Pwd,Status) VALUES (?, ?, ? )";
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
		AdMemberVO vo = new AdMemberVO();
		vo.setAccount("4125252");
		vo.setPwd("123456");
		vo.setStatus(0);
//		
		AdMemberDAO dao = new AdMemberDAO();
		dao.insert(vo);
		System.out.println("已加入成功");
//		//insert 測試OK
		
		//update 測試OK
//		adMemberDAO dao = new adMemberDAO();
//		dao.update("4125252", "55123", 1);
//		System.out.println("更新成功");
		//update 測試OK
		
		//find account 怎麼佐證有查到?
//		AdMemberDAO dao = new AdMemberDAO();
//		AdMemberVO vo = dao.findBYaccount(4125252);
		//find account
	}

	@Override
	public void insert(AdMemberVO AdMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, AdMemberVO.getAccount());
			pstmt.setString(2, AdMemberVO.getPwd());
			pstmt.setInt(3, AdMemberVO.getStatus());

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
	public AdMemberVO findBYaccount(Integer account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdMemberVO vo = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYACCOUNT_STMT);
			System.out.println("連線成功");
			
			pstmt.setInt(1, account);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new AdMemberVO();
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

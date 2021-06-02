package com.Manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Util;

public class ManagerDAO implements ManagerDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO Manager (account, pwd, status) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Manager set status=? where account = ?";
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動成功");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//測試insert ok
//		ManagerVO vo = new ManagerVO();
//		vo.setAccount("00000004");
//		vo.setPwd("123456");
//		vo.setStatus(0);
//		
//		ManagerDAO dao = new ManagerDAO();
//		dao.insert(vo);
//		System.out.println("加入成功");
		//測試insert ok
		
		//測試updata
		ManagerDAO dao = new ManagerDAO();
		dao.update("00000004", 0 );
		
		System.out.println("更新成功");
		//測試updata

	}

	@Override
	public int update(String account, Integer status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, status);
			pstmt.setString(2, account);
			
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

	public void insert(ManagerVO ManagerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ManagerVO.getAccount());
			pstmt.setString(2, ManagerVO.getPwd());
			pstmt.setInt(3, ManagerVO.getStatus());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

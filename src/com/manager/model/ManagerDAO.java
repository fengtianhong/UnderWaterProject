package com.manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Util;

public class ManagerDAO implements ManagerDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO Manager (account, pwd, status) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Manager set status=? where account = ?";
	private static final String LOGIN_STMT = "SELECT * FROM MANAGER where account=? and pwd=?";
	
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
		ManagerDAO dao = new ManagerDAO();
		ManagerVO vo = new ManagerVO();
//		vo.setAccount("97119");
//		vo.setPwd("123456");
//		vo.setStatus(0);
//		dao.insert(vo);
//		System.out.println("加入成功");
		//測試insert ok
		
		//測試updata
		
//		dao.update("00000004", 0 );
		
//		System.out.println("更新成功");
		//測試updata
		
		//測試login
		vo.setAccount("97119");
		vo.setPwd("5");
		dao.login(vo);
		//測試login
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

	@Override
	public Boolean login(ManagerVO ManagerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagerVO vo = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(LOGIN_STMT);
			pstmt.setString(1, ManagerVO.getAccount());
			pstmt.setString(2, ManagerVO.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new ManagerVO();
				vo.setAccount(rs.getString("account"));
				vo.setPwd(rs.getString("pwd"));
				System.out.println("登入成功");
				return true;
			}else {
				System.out.println("帳號密碼錯誤");
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
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
		return false;
	}

}

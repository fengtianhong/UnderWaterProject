package com.collections.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.Util;



public class CollectionsDAO implements CollectionsDAO_interface {
	
//	insert, delete, findByUserID 已確認可以在 DB 執行 (會卡FK)	
	// 兩個問題 	1. 兩欄位是否綁複合 UK  2. 由 controller 判斷要 insert 還是 delete ?
	
//	public static void main(String[] args) {		// FOR TEST
//		CollectionsDAO dao = new CollectionsDAO();
//		CollectionsVO collectionsVO = new CollectionsVO();
//		collectionsVO.setGroupTourSN(1); 	// 1
//		collectionsVO.setUserID(1);			// 1 2 3
//		dao.insert(collectionsVO);			// OK
//		dao.delete(collectionsVO); 			// OK
//		System.out.println(dao.findByUserID(2));  // OK
//		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/UNDERWATER?serverTimezone=Asia/Taipei"
//				, "root"
//				, "User!23$");
//		ps = con.prepareStatement(INSERT_STMT);
//	}
	
	private static final String INSERT_STMT = "INSERT INTO Collections (userID, groupTourSN) VALUES (?, ?)";
	private static final String DELETE_STMT = "DELETE FROM Collections where userID = ? and groupTourSN = ?";
	private static final String GET_ALLGP_STMT = "SELECT userID, groupTourSN FROM Collections WHERE userID = ?";
//	private static final String GET_ONE_STMT = "SELECT userID FROM Collections WHERE groupTourSN = ?";	// 不確定需不需要


	@Override
	public void insert(CollectionsVO collectionsVO) {	
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, collectionsVO.getUserID());
			ps.setInt(2, collectionsVO.getGroupTourSN());			
			ps.executeUpdate();

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	@Override
	public void update() { // no need	
	}

	@Override
	public void delete(CollectionsVO collectionsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);
			
			ps = con.prepareStatement(DELETE_STMT);
			ps.setInt(1, collectionsVO.getUserID());
			ps.setInt(2, collectionsVO.getGroupTourSN());
			ps.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<Integer> findByUserID(Integer userID) {
		List<Integer> groupTourSNList = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);

			ps = con.prepareStatement(GET_ALLGP_STMT);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				groupTourSNList.add(rs.getInt("groupTourSN"));
			}
			
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return groupTourSNList;
	}

}

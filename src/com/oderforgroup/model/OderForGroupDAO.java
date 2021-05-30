package com.oderforgroup.model;

import java.sql.*;
import java.util.*;

import util.Util;

public class OderForGroupDAO implements OderForGroupDAO_interface{
//	public static void main(String[] args) {		// FOR TEST
//		OderForGroupDAO dao = new OderForGroupDAO();
//		OderForGroupVO vo = new OderForGroupVO();
//		vo.setOrderSN(6005); // use default
//		vo.setUserID(2);	
//		vo.setGroupTourSN(6001);
//		vo.setTotalPrice(10000);
//		vo.setPurchaseDate(java.sql.Date.valueOf("2021-04-28"));
//		vo.setPhone("0918210612");
//		vo.setPersonID("A9999999");
//		vo.setBirthdate(java.sql.Date.valueOf("1996-07-19"));
//		System.out.println(vo.toString());
////		dao.insert(vo);			// OK
////		dao.update(vo);			// OK
//		System.out.println(dao.findByPrimaryKey(6003));  	// OK
//		System.out.println(dao.getOrderByUserID(2)); 		// OK
//}
	
	// 訂單結束時間的話吃 GroupTour 的行程結束時間 > 但要寫在DAO?
	private static final String INSERT_STMT = "INSERT INTO OderForGroup (userID, groupTourSN, totalPrice, purchaseDate, phone, personID, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE OderForGroup SET userID=?, groupTourSN=?, totalPrice=?, purchaseDate=?, phone=?, personID=?, birthdate=? WHERE orderSN = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM OderForGroup WHERE orderSN = ?";
	private static final String GET_ByUSERID_STMT = "SELECT * FROM OderForGroup WHERE userID = ? ORDER BY purchaseDate";

	@Override
	public void insert(OderForGroupVO oderForGroupVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, oderForGroupVO.getUserID());			
			ps.setInt(2, oderForGroupVO.getGroupTourSN());			
			ps.setInt(3, oderForGroupVO.getTotalPrice());			
			ps.setDate(4, oderForGroupVO.getPurchaseDate());			
			ps.setString(5, oderForGroupVO.getPhone());						
			ps.setString(6, oderForGroupVO.getPersonID());						
			ps.setDate(7, oderForGroupVO.getBirthdate());											
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
	public void update(OderForGroupVO oderForGroupVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setInt(1, oderForGroupVO.getUserID());			
			ps.setInt(2, oderForGroupVO.getGroupTourSN());		
			ps.setInt(3, oderForGroupVO.getTotalPrice());			
			ps.setDate(4, oderForGroupVO.getPurchaseDate());			
			ps.setString(5, oderForGroupVO.getPhone());						
			ps.setString(6, oderForGroupVO.getPersonID());						
			ps.setDate(7, oderForGroupVO.getBirthdate());						
			ps.setInt(8, oderForGroupVO.getOrderSN());						
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
	public OderForGroupVO findByPrimaryKey(Integer orderSN) {
		OderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(GET_ONE_STMT);
			
			ps.setInt(1, orderSN);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				oderForGroupVO = new OderForGroupVO();
				oderForGroupVO.setOrderSN(rs.getInt("orderSN"));
				oderForGroupVO.setUserID(rs.getInt("userID"));
				oderForGroupVO.setGroupTourSN(rs.getInt("groupTourSN"));
				oderForGroupVO.setTotalPrice(rs.getInt("totalPrice"));
				oderForGroupVO.setPurchaseDate(rs.getDate("purchaseDate"));
				oderForGroupVO.setPhone(rs.getString("phone"));
				oderForGroupVO.setPersonID(rs.getString("personID"));
				oderForGroupVO.setBirthdate(rs.getDate("birthdate"));
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
		return oderForGroupVO;
	}

	@Override
	public List<OderForGroupVO> getOrderByUserID(Integer userID) {
		List<OderForGroupVO> list = new ArrayList<OderForGroupVO>();
		OderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				ps = con.prepareStatement(GET_ByUSERID_STMT);
				ps.setInt(1, userID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					oderForGroupVO = new OderForGroupVO();
					oderForGroupVO.setOrderSN(rs.getInt("orderSN"));
					oderForGroupVO.setUserID(rs.getInt("userID"));
					oderForGroupVO.setGroupTourSN(rs.getInt("groupTourSN"));
					oderForGroupVO.setTotalPrice(rs.getInt("totalPrice"));
					oderForGroupVO.setPurchaseDate(rs.getDate("purchaseDate"));
					oderForGroupVO.setPhone(rs.getString("phone"));
					oderForGroupVO.setPersonID(rs.getString("personID"));
					oderForGroupVO.setBirthdate(rs.getDate("birthdate"));
					list.add(oderForGroupVO);
				}
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
		return list;
	}

}

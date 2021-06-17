package com.orderforgroup.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class OderForGroupDAO implements OderForGroupDAO_interface{
	
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO OderForGroup (userID, groupTourSN, totalPrice, purchaseDate, phone, personID, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE OderForGroup SET userID=?, groupTourSN=?, totalPrice=?, purchaseDate=?, phone=?, personID=?, birthdate=? WHERE orderSN = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM OderForGroup WHERE orderSN = ?";
	private static final String GET_ByUSERID_STMT = "SELECT * FROM OderForGroup WHERE userID = ? ORDER BY purchaseDate";
	private static final String GET_ALL_STMT = "SELECT * FROM OderForGroup ORDER BY purchaseDate";

	@Override
	public void insert(OderForGroupVO oderForGroupVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, oderForGroupVO.getUserID());			
			ps.setInt(2, oderForGroupVO.getGroupTourSN());			
			ps.setInt(3, oderForGroupVO.getTotalPrice());			
			ps.setDate(4, oderForGroupVO.getPurchaseDate());			
			ps.setString(5, oderForGroupVO.getPhone());						
			ps.setString(6, oderForGroupVO.getPersonID());						
			ps.setDate(7, oderForGroupVO.getBirthdate());											
			ps.executeUpdate();
			
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
				con = ds.getConnection();
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

	@Override
	public List<OderForGroupVO> getAll() {
		List<OderForGroupVO> list = new ArrayList<OderForGroupVO>();
		OderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_ALL_STMT);
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

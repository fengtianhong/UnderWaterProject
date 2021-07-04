package com.orderforgroup.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.grouptour.model.GroupTourDAO;

import util.Util;

public class OrderForGroupDAO implements OrderForGroupDAO_interface{
	
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
	private static final String GET_ByUSERID_STMT = "SELECT * FROM OderForGroup WHERE userID = ? ORDER BY purchaseDate DESC";
	private static final String GET_ALL_STMT = "SELECT * FROM OderForGroup ORDER BY purchaseDate DESC";
	private static final String GET_MEMBER_STMT = "SELECT * FROM OderForGroup WHERE groupTourSN = ? ORDER BY purchaseDate DESC";

	
	
	@Override
	public void insert(OrderForGroupVO oderForGroupVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			// 新增訂單
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, oderForGroupVO.getUserID());			
			ps.setInt(2, oderForGroupVO.getGroupTourSN());			
			ps.setInt(3, oderForGroupVO.getTotalPrice());			
			ps.setDate(4, oderForGroupVO.getPurchaseDate());			
			ps.setString(5, oderForGroupVO.getPhone());						
			ps.setString(6, oderForGroupVO.getPersonID());						
			ps.setDate(7, oderForGroupVO.getBirthdate());											
			ps.executeUpdate();
			//ps.close();//
			
			// 更新報名人數
			GroupTourDAO dao = new GroupTourDAO();
			dao.attendGroup(oderForGroupVO.getGroupTourSN(), con);	// 裡面有錯也要rollback
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if(con != null) {
				try {
					System.out.println("insert rolled back");
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("rollback error occured. " + e.getMessage());
				}
			}
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
	public void update(OrderForGroupVO oderForGroupVO) {
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
	public OrderForGroupVO findByPrimaryKey(Integer orderSN) {
		OrderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_STMT);
			
			ps.setInt(1, orderSN);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				oderForGroupVO = new OrderForGroupVO();
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
	public List<OrderForGroupVO> getOrderByUserID(Integer userID) {
		List<OrderForGroupVO> list = new ArrayList<OrderForGroupVO>();
		OrderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_ByUSERID_STMT);
				ps.setInt(1, userID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					oderForGroupVO = new OrderForGroupVO();
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
	public List<OrderForGroupVO> getAll() {
		List<OrderForGroupVO> list = new ArrayList<OrderForGroupVO>();
		OrderForGroupVO oderForGroupVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_ALL_STMT);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					oderForGroupVO = new OrderForGroupVO();
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
	public List<Integer> checkRepeatOrder(Integer userID) {
		List<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_ByUSERID_STMT);
				ps.setInt(1, userID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					list.add(rs.getInt("groupTourSN"));
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
	public List<Integer> getMember(Integer groupTourSN) {
		List<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_MEMBER_STMT);
				ps.setInt(1, groupTourSN);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					list.add(rs.getInt("userID"));
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

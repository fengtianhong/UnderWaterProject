package com.collections.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class CollectionsDAO implements CollectionsDAO_interface {
	
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
	
	private static final String INSERT_STMT = "INSERT INTO Collections (userID, groupTourSN) VALUES (?, ?)";
	private static final String DELETE_STMT = "DELETE FROM Collections where userID = ? and groupTourSN = ?";
	private static final String GET_ALLGP_STMT = "SELECT userID, groupTourSN FROM Collections WHERE userID = ?";
//	private static final String GET_ONE_STMT = "SELECT * FROM Collections WHERE groupTourSN = ?";	// 不確定需不需要

	@Override
	public void insert(CollectionsVO collectionsVO) {	
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, collectionsVO.getUserID());
			ps.setInt(2, collectionsVO.getGroupTourSN());			
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
	public void delete(CollectionsVO collectionsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			
			ps = con.prepareStatement(DELETE_STMT);
			ps.setInt(1, collectionsVO.getUserID());
			ps.setInt(2, collectionsVO.getGroupTourSN());
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
	public List<Integer> findByUserID(Integer userID) {
		List<Integer> groupTourSNList = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps = con.prepareStatement(GET_ALLGP_STMT);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				groupTourSNList.add(rs.getInt("groupTourSN"));
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
		return groupTourSNList;
	}

}

package com.locationrate.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class LocationRateDAO implements LocationRateDAO_interface{
	
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
	private static final String INSERT_STMT = "INSERT INTO LocationRate (pointSN, userID, rate, rateDetail) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LocationRate SET rate=?, rateDetail=? WHERE SN=?";
	private static final String DELETE_STMT = "DELETE FROM LocationRate WHERE SN = ?";
	private static final String GET_DIVE_POINT_LIST_STMT = "SELECT * FROM LocationRate WHERE pointSN = ?";
	private static final String GET_ByUSER_STMT = "SELECT * FROM LocationRate WHERE userID = ?";
	
	@Override
	public void insert(LocationRateVO locationRateVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, locationRateVO.getPointSN());			
			ps.setInt(2, locationRateVO.getUserID());			
			ps.setInt(3, locationRateVO.getRate());			
			ps.setString(4, locationRateVO.getRateDetail());					
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
	public void update(LocationRateVO locationRateVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setInt(1, locationRateVO.getRate());			
			ps.setString(2, locationRateVO.getRateDetail());		
			ps.setInt(3, locationRateVO.getSN());			
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
	public void delete(Integer SN) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE_STMT);

			ps.setInt(1, SN);
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
	public List<LocationRateVO> getByPointSN(Integer pointSN) {
		List<LocationRateVO> list = new ArrayList<LocationRateVO>();
		LocationRateVO locationRateVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_DIVE_POINT_LIST_STMT);
				ps.setInt(1, pointSN);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					locationRateVO = new LocationRateVO();
					locationRateVO.setSN(rs.getInt("SN"));
					locationRateVO.setPointSN(rs.getInt("pointSN"));
					locationRateVO.setUserID(rs.getInt("userID"));
					locationRateVO.setRate(rs.getInt("rate"));
					locationRateVO.setRateDetail(rs.getString("rateDetail"));
					locationRateVO.setCreateTime(rs.getTimestamp("createTime"));
					list.add(locationRateVO);
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
	public List<LocationRateVO> getByUser(Integer userID) {
		List<LocationRateVO> list = new ArrayList<LocationRateVO>();
		LocationRateVO locationRateVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GET_ByUSER_STMT);
				ps.setInt(1, userID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					locationRateVO = new LocationRateVO();
					locationRateVO.setSN(rs.getInt("SN"));
					locationRateVO.setPointSN(rs.getInt("pointSN"));
					locationRateVO.setUserID(rs.getInt("userID"));
					locationRateVO.setRate(rs.getInt("rate"));
					locationRateVO.setRateDetail(rs.getString("rateDetail"));
					locationRateVO.setCreateTime(rs.getTimestamp("createTime"));
					list.add(locationRateVO);
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

package com.grouptour.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class GroupTourDAO implements GroupTourDAO_interface{
	
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
	private static final String INSERT_STMT = "INSERT INTO GroupTour (tourName, startTime, endTime, regTime, closeTime, pointSN, price, attendNumber, limitNumder, certificationLimit, status, content) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE GroupTour SET tourName=?, startTime=?, endTime=?, regTime=?, closeTime=?, pointSN=?, price=?, attendNumber=?, limitNumder=?, certificationLimit=?, status=?, content=? WHERE groupTourSN=?";
	private static final String GET_ONE_STMT = "SELECT * FROM GroupTour WHERE groupTourSN=?";
	private static final String GET_All_LIST_STMT = "SELECT * FROM GroupTour ORDER BY status, startTime";

	@Override
	public void insert(GroupTourVO groupTourVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, groupTourVO.getTourName());			
			ps.setDate(2, groupTourVO.getStartTime());			
			ps.setDate(3, groupTourVO.getEndTime());			
			ps.setDate(4, groupTourVO.getRegTime());			
			ps.setDate(5, groupTourVO.getCloseTime());						
			ps.setInt(6, groupTourVO.getPointSN());						
			ps.setInt(7, groupTourVO.getPrice());						
			ps.setInt(8, groupTourVO.getAttendNumber());						
			ps.setInt(9, groupTourVO.getLimitNumder());						
			ps.setString(10, groupTourVO.getCertificationLimit());						
			ps.setString(11, groupTourVO.getStatus());						
			ps.setString(12, groupTourVO.getContent());						
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
	public void update(GroupTourVO groupTourVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setString(1, groupTourVO.getTourName());			
			ps.setDate(2, groupTourVO.getStartTime());		
			ps.setDate(3, groupTourVO.getEndTime());			
			ps.setDate(4, groupTourVO.getRegTime());			
			ps.setDate(5, groupTourVO.getCloseTime());						
			ps.setInt(6, groupTourVO.getPointSN());						
			ps.setInt(7, groupTourVO.getPrice());						
			ps.setInt(8, groupTourVO.getAttendNumber());						
			ps.setInt(9, groupTourVO.getLimitNumder());						
			ps.setString(10, groupTourVO.getCertificationLimit());						
			ps.setString(11, groupTourVO.getStatus());						
			ps.setString(12, groupTourVO.getContent());						
			ps.setInt(13, groupTourVO.getGroupTourSN());						
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
	public GroupTourVO findByPrimaryKey(Integer groupTourSN) {
		GroupTourVO groupTourVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_STMT);
			
			ps.setInt(1, groupTourSN);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				groupTourVO = new GroupTourVO();
				groupTourVO.setGroupTourSN(rs.getInt("groupTourSN"));
				groupTourVO.setTourName(rs.getString("tourName"));
				groupTourVO.setStartTime(rs.getDate("startTime"));
				groupTourVO.setEndTime(rs.getDate("endTime"));
				groupTourVO.setRegTime(rs.getDate("regTime"));
				groupTourVO.setCloseTime(rs.getDate("closeTime"));
				groupTourVO.setCreateTime(rs.getTimestamp("createTime"));
				groupTourVO.setPointSN(rs.getInt("pointSN"));
				groupTourVO.setPrice(rs.getInt("price"));
				groupTourVO.setAttendNumber(rs.getInt("attendNumber"));
				groupTourVO.setLimitNumder(rs.getInt("limitNumder"));
				groupTourVO.setCertificationLimit(rs.getString("certificationLimit"));
				groupTourVO.setStatus(rs.getString("status"));
				groupTourVO.setContent(rs.getString("content"));
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
		return groupTourVO;
	}

	@Override
	public List<GroupTourVO> getAll() {
		
		List<GroupTourVO> list = new ArrayList<GroupTourVO>();
		GroupTourVO groupTourVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_All_LIST_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				groupTourVO = new GroupTourVO();
				groupTourVO.setGroupTourSN(rs.getInt("groupTourSN"));
				groupTourVO.setTourName(rs.getString("tourName"));
				groupTourVO.setStartTime(rs.getDate("startTime"));
				groupTourVO.setEndTime(rs.getDate("endTime"));
				groupTourVO.setRegTime(rs.getDate("regTime"));
				groupTourVO.setCloseTime(rs.getDate("closeTime"));
				groupTourVO.setCreateTime(rs.getTimestamp("createTime"));
				groupTourVO.setPointSN(rs.getInt("pointSN"));
				groupTourVO.setPrice(rs.getInt("price"));
				groupTourVO.setAttendNumber(rs.getInt("attendNumber"));
				groupTourVO.setLimitNumder(rs.getInt("limitNumder"));
				groupTourVO.setCertificationLimit(rs.getString("certificationLimit"));
				groupTourVO.setStatus(rs.getString("status"));
//				groupTourVO.setContent(rs.getString("content"));	// 此方法僅為套裝行程商品列表用，因此不取內文部分
				list.add(groupTourVO);
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
		return list;
	}
}

	
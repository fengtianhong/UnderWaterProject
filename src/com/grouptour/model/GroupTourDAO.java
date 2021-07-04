package com.grouptour.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orderforgroup.model.OrderForGroupVO;

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
	private static final String INSERT_STMT = "INSERT INTO GroupTour (tourName, tourPic, startTime, endTime, regTime, closeTime, pointSN, price, limitNumder, certificationLimit, status, content) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE GroupTour SET tourName=?, tourPic=?, startTime=?, endTime=?, regTime=?, closeTime=?, pointSN=?, price=?, attendNumber=?, limitNumder=?, certificationLimit=?, status=?, content=? WHERE groupTourSN=?";
	private static final String GET_ONE_STMT = "SELECT * FROM GroupTour WHERE groupTourSN=?";
	private static final String GET_All_LIST_STMT = "SELECT * FROM GroupTour ORDER BY status, startTime";
	private static final String GET_FRONTEND_All_LIST_STMT = "SELECT * FROM GroupTour WHERE status=0 ORDER BY startTime";
	private static final String UPDATE_ATTEND_NUMBER_STMT = "UPDATE GroupTour SET attendNumber=attendNumber+1 WHERE groupTourSN=?";
	private static final String UPDATE_STATUS_STMT = "UPDATE GroupTour SET status=? WHERE groupTourSN=?";
	// 篩選用
	private static final String KEYWORD_STMT = "SELECT * FROM GroupTour where tourName like ? or content like ? WHERE status=0 ORDER BY startTime";
	private static final String LOCATION_STMT = "select groupTourSN from GroupTour left join Diveinfo on GroupTour.pointSN = Diveinfo.pointSN where local = ? AND GroupTour.status=0 ORDER BY startTime";
	private static final String GET_FILTER_All_STMT = "SELECT groupTourSN FROM GroupTour WHERE status=0 ORDER BY startTime";

	
	@Override
	public void insert(GroupTourVO groupTourVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, groupTourVO.getTourName());			
			ps.setBytes(2, groupTourVO.getTourPic());			
			ps.setDate(3, groupTourVO.getStartTime());			
			ps.setDate(4, groupTourVO.getEndTime());			
			ps.setDate(5, groupTourVO.getRegTime());			
			ps.setDate(6, groupTourVO.getCloseTime());						
			ps.setInt(7, groupTourVO.getPointSN());						
			ps.setInt(8, groupTourVO.getPrice());											
			ps.setInt(9, groupTourVO.getLimitNumder());						
			ps.setString(10, groupTourVO.getCertificationLimit());						
			ps.setString(11, groupTourVO.getStatus());						
			ps.setString(12, groupTourVO.getContent());						
			ps.executeUpdate();
			
		} catch (SQLException se) {
			// runtime exception 可以穿越各層一直到view，顯示出錯誤訊息
			// 且有效的錯誤訊息可以知道是程式的哪部分出錯
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
			ps.setBytes(2, groupTourVO.getTourPic());			
			ps.setDate(3, groupTourVO.getStartTime());		
			ps.setDate(4, groupTourVO.getEndTime());			
			ps.setDate(5, groupTourVO.getRegTime());			
			ps.setDate(6, groupTourVO.getCloseTime());						
			ps.setInt(7, groupTourVO.getPointSN());						
			ps.setInt(8, groupTourVO.getPrice());						
			ps.setInt(9, groupTourVO.getAttendNumber());						
			ps.setInt(10, groupTourVO.getLimitNumder());						
			ps.setString(11, groupTourVO.getCertificationLimit());						
			ps.setString(12, groupTourVO.getStatus());						
			ps.setString(13, groupTourVO.getContent());						
			ps.setInt(14, groupTourVO.getGroupTourSN());						
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
				groupTourVO.setTourPic(rs.getBytes("tourPic"));
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
			// runtime exception 可以穿越各層一直到view，顯示出錯誤訊息
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
				groupTourVO.setTourPic(rs.getBytes("tourPic"));
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

	// for OderForGroup insert transaction
	@Override
	public void attendGroup(Integer groupTourSN, Connection con) {
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(UPDATE_ATTEND_NUMBER_STMT);
			ps.setInt(1, groupTourSN);			
			ps.executeUpdate();
			
		} catch (SQLException se) {			
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("attendGroup rolled back");
					// Exception 發生時必須 rollback
					con.rollback();
				} catch (SQLException sqle) {
					throw new RuntimeException("rollback error occured. " + sqle.getMessage());
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
			}	// 連線不用關，呼叫的DAO或繼續做事
		}	
		
	}

	@Override
	public List<GroupTourVO> getFrontendAll() {
		
		List<GroupTourVO> list = new ArrayList<GroupTourVO>();
		GroupTourVO groupTourVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_FRONTEND_All_LIST_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				groupTourVO = new GroupTourVO();
				groupTourVO.setGroupTourSN(rs.getInt("groupTourSN"));
				groupTourVO.setTourName(rs.getString("tourName"));
				groupTourVO.setTourPic(rs.getBytes("tourPic"));
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

	@Override
	public void updateStatus(Integer groupTourSN, String status) {
		// UPDATE_STATUS_STMT = "UPDATE GroupTour SET status=? WHERE groupTourSN=?";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STATUS_STMT);
			ps.setString(1, status);						
			ps.setInt(2, groupTourSN);			
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
	public List<Integer> keywordFilter(String keyword) {
		List<Integer> list = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(KEYWORD_STMT);
			ps.setString(1, "%"+ keyword + "%");
			ps.setString(2, "%"+ keyword + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("groupTourSN"));
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

	@Override
	public List<Integer> locationFilter(String location) {
		List<Integer> list = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(LOCATION_STMT);
			ps.setString(1, location);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("groupTourSN"));
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

	@Override
	public List<Integer> allForFilter() {
		List<Integer> list = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_FILTER_All_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("groupTourSN"));
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

	

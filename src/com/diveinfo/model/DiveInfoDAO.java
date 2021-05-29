package com.diveinfo.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class DiveInfoDAO implements DiveInfoDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	private static final String INSERT_STMT = "INSERT INTO DiveInfo (pointName, latitude, longitude, view, introduction, season, local, pic, ratePoint, ratePeople, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String UPDATE_STMT = "UPDATE DiveInfo set pointName=?, latitude=?,longitude=?, view=?, introduction=?, season=?, local=?,  pic=?, ratePoint=?, ratePeople=?, status=? where pointSN = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM DiveInfo WHERE pointSN = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM DiveInfo";

	@Override
	public void insert(DiveInfoVO diveInfoVO) {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, diveInfoVO.getPointName());
			ps.setDouble(2, diveInfoVO.getLatitude());
			ps.setDouble(3, diveInfoVO.getLongitude());
			ps.setString(4, diveInfoVO.getView());
			ps.setString(5, diveInfoVO.getIntroduction());
			ps.setString(6, diveInfoVO.getSeason());
			ps.setString(7, diveInfoVO.getLocal());
			ps.setBytes(8, diveInfoVO.getPic());
			ps.setInt(9, diveInfoVO.getRatePoint());
			ps.setInt(10, diveInfoVO.getRatePeople());
			ps.setString(11, diveInfoVO.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(DiveInfoVO diveInfoVO) {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setString(1, diveInfoVO.getPointName());
			ps.setDouble(2, diveInfoVO.getLatitude());
			ps.setDouble(3, diveInfoVO.getLongitude());
			ps.setString(4, diveInfoVO.getView());
			ps.setString(5, diveInfoVO.getIntroduction());
			ps.setString(6, diveInfoVO.getSeason());
			ps.setString(7, diveInfoVO.getLocal());
			ps.setBytes(8, diveInfoVO.getPic());
			ps.setInt(9, diveInfoVO.getRatePoint());
			ps.setInt(10, diveInfoVO.getRatePeople());
			ps.setString(11, diveInfoVO.getStatus());
			ps.setInt(12, diveInfoVO.getPointSN());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public DiveInfoVO findByPrimaryKey(Integer pointSN) {
		
		DiveInfoVO diveinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, pointSN);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				diveinfoVO = new DiveInfoVO();
				diveinfoVO.setPointSN(rs.getInt("pointSN"));
				diveinfoVO.setPointName(rs.getString("pointName"));
				diveinfoVO.setLatitude(rs.getDouble("latitude"));  
				diveinfoVO.setLongitude(rs.getDouble("longitude"));
				diveinfoVO.setView(rs.getString("view"));
				diveinfoVO.setIntroduction(rs.getString("introduction"));
				diveinfoVO.setSeason(rs.getString("season"));
				diveinfoVO.setLocal(rs.getString("local"));
				diveinfoVO.setPic(rs.getBytes("pic"));
				diveinfoVO.setRatePoint(rs.getInt("ratePoint"));
				diveinfoVO.setRatePeople(rs.getInt("ratePeople"));
				diveinfoVO.setStatus(rs.getString("status"));		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return diveinfoVO;
	}

	@Override
	public List<DiveInfoVO> getAll() {
		List<DiveInfoVO> list = new ArrayList<DiveInfoVO>();
		DiveInfoVO diveinfoVO = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();

			while(rs.next()) {
				diveinfoVO = new DiveInfoVO();
				diveinfoVO.setPointSN(rs.getInt("pointSN"));
				diveinfoVO.setPointName(rs.getString("pointName"));
				diveinfoVO.setLatitude(rs.getDouble("latitude"));  
				diveinfoVO.setLongitude(rs.getDouble("longitude"));
				diveinfoVO.setView(rs.getString("view"));
				diveinfoVO.setIntroduction(rs.getString("introduction"));
				diveinfoVO.setSeason(rs.getString("season"));
				diveinfoVO.setLocal(rs.getString("local"));
				diveinfoVO.setPic(rs.getBytes("pic"));
				diveinfoVO.setRatePoint(rs.getInt("ratePoint"));
				diveinfoVO.setRatePeople(rs.getInt("ratePeople"));
				diveinfoVO.setStatus(rs.getString("status"));
				list.add(diveinfoVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}

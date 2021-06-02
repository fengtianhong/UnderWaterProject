package com.locationrate.model;

import java.sql.*;
import java.util.*;
import util.Util;

public class LocationRateDAO implements LocationRateDAO_interface{
	
	private static final String INSERT_STMT = "INSERT INTO LocationRate (pointSN, userID, rate, rateDetail) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LocationRate SET rate=?, rateDetail=? WHERE SN=?";
	private static final String DELETE_STMT = "DELETE FROM LocationRate WHERE SN = ?";
	private static final String GET_DIVE_POINT_LIST_STMT = "SELECT * FROM LocationRate WHERE pointSN = ?";
	private static final String GET_ByUSER_STMT = "SELECT * FROM LocationRate WHERE userID = ?";
	
//	public static void main(String[] args) {		// FOR TEST
//		LocationRateDAO dao = new LocationRateDAO();
//		LocationRateVO vo = new LocationRateVO();
//		vo = new LocationRateVO();
//		vo.setSN(6002);
//		vo.setPointSN(200002);
//		vo.setUserID(3);
//		vo.setRate(1);
//		vo.setRateDetail("鬼島校正回歸中，勿前往");
////		vo.setCreateTime();
//		System.out.println(vo.toString());
//		dao.insert(vo);			// OK
////		dao.update(vo);			// OK
////		dao.delete(6003); 				// OK
//		System.out.println(dao.getByPointSN(200002));  	// OK
//		System.out.println(dao.getByUser(1)); 		// OK
//}
	
	@Override
	public void insert(LocationRateVO locationRateVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, locationRateVO.getPointSN());			
			ps.setInt(2, locationRateVO.getUserID());			
			ps.setInt(3, locationRateVO.getRate());			
			ps.setString(4, locationRateVO.getRateDetail());			
					
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
	public void update(LocationRateVO locationRateVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setInt(1, locationRateVO.getRate());			
			ps.setString(2, locationRateVO.getRateDetail());		
			ps.setInt(3, locationRateVO.getSN());			
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
	public void delete(Integer SN) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(DELETE_STMT);

			ps.setInt(1, SN);
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
	public List<LocationRateVO> getByPointSN(Integer pointSN) {
		List<LocationRateVO> list = new ArrayList<LocationRateVO>();
		LocationRateVO locationRateVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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

	
	@Override
	public LocationRateVO getByUser(Integer userID) {
		LocationRateVO locationRateVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
		return locationRateVO;
	}

}

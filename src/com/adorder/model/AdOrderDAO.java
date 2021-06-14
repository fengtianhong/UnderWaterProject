package com.adorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;

import util.Util;

public class AdOrderDAO implements AdOrderDAO_interface{
	
	private static final String INSERT_STMT = "INSERT INTO adOrder (adUserID, block, showTime, expiredTime, status) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Member SET block=?, showTime=?, expiredTime=?, status=? WHERE adOrderID = ?";
	private static final String FINDBYACCOUNT_STMT = "SELECT * FROM adOrder where  adUserID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AdOrder ORDER BY orderSN";
	
	public static void main(String[] args) {
		
		AdOrderVO vo = new AdOrderVO();
		AdOrderDAO dao = new AdOrderDAO();
		
		//insert測試ok
		vo.setAdUserID(1);
		vo.setBlock(2);
		vo.setShowTime(Timestamp.valueOf("2021-06-10 11:04:45"));
		vo.setExpiredTime(Timestamp.valueOf("2021-06-13 11:04:45"));
		vo.setStatus(1);
		dao.insert(vo);
		System.out.println("已新增一筆");
		//insert測試ok
		
		//update測試
//		vo.setBlock(2);
//		vo.setShowTime(Timestamp.valueOf("2021-06-12 11:04:45"));
//		vo.setExpiredTime(Timestamp.valueOf("2021-06-18 11:04:45"));
//		vo.setStatus(1);
//		vo.setAdUserID(adUserID);
		//update測試

		//findByadUserID測試
//		List<AdOrderVO> L1 = dao.findByadUserID(1);
//		for(AdOrderVO xx:L1) {
//			System.out.println(xx.getShowTime());
//		}
		//findByadUserID測試
		
		//getAll測試
//		List<AdOrderVO> L1 = dao.getAll();
//		for(AdOrderVO xx:L1) {
//			System.out.println(xx.getAdUserID());
//		}
		//getAll測試
	}
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("驅動成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(AdOrderVO AdOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, AdOrderVO.getAdUserID());
			pstmt.setInt(2, AdOrderVO.getBlock());
			pstmt.setTimestamp(3, AdOrderVO.getShowTime());
			pstmt.setTimestamp(4, AdOrderVO.getExpiredTime());
			pstmt.setInt(5, AdOrderVO.getStatus());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(AdOrderVO AdOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, AdOrderVO.getBlock());
			pstmt.setTimestamp(2, AdOrderVO.getShowTime());
			pstmt.setTimestamp(3, AdOrderVO.getExpiredTime());
			pstmt.setInt(4, AdOrderVO.getStatus());
			pstmt.setInt(5, AdOrderVO.getOrderSN());
			
			pstmt.executeUpdate();
			
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
	}

	@Override
	public List<AdOrderVO> findByadUserID(Integer adUserID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdOrderVO vo = null;
		List<AdOrderVO> list = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYACCOUNT_STMT);
			
			pstmt.setInt(1, adUserID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AdOrderVO();
				vo.setOrderSN(rs.getInt("orderSN"));
				vo.setBlock(rs.getInt("block"));
				vo.setTime(rs.getTimestamp("time"));
				vo.setShowTime(rs.getTimestamp("showTime"));
				vo.setExpiredTime(rs.getTimestamp("expiredTime"));
				vo.setStatus(rs.getInt("status"));
				list.add(vo);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<AdOrderVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AdOrderVO> list = new ArrayList<>();
		ResultSet rs = null;
		AdOrderVO vo = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new AdOrderVO();
				vo.setOrderSN(rs.getInt("orderSN"));
				vo.setAdUserID(rs.getInt("adUserID"));
				vo.setBlock(rs.getInt("block"));
				vo.setTime(rs.getTimestamp("time"));
				vo.setShowTime(rs.getTimestamp("showTime"));
				vo.setExpiredTime(rs.getTimestamp("expiredTime"));
				vo.setStatus(rs.getInt("status"));
				list.add(vo);
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
			if(pstmt != null) {
				try {
					pstmt.close();
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

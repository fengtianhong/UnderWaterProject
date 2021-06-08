package com.adpic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;

import util.Util;

public class AdPicDAO implements AdPicDAO_interface{
	
	private static final String INSERT_STMT = "INSERT INTO AdPic (adPicSN, orderSN, pic) VALUES(?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE AdPic SET  pic=? WHERE AdpicSN=? ";
	private static final String GET_ONE_STMT = "SELECT * FROM Adpic WHERE adPicSN=?";
	private static final String GET_ALL_STMT = "SELECT * FROM AdPic ORDER BY adPicSN";
	
	public static void main(String[] args) {
		//insert 測試ok
//		FileInputStream fis = null;
//		byte[] b = null;
//		
//		try {
//			fis = new FileInputStream("C:\\TFA101_WebApp\\eclipse_TFA101_workspace\\UnderWaterProject\\src\\com\\adpic\\model\\01.jpg");
//			b = new  byte[fis.available()];
//			fis.read(b);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(fis != null) {
//				try {
//					fis.close();
//				}catch(IOException ie){
//					ie.printStackTrace();
//				}
//			}
//		}
//		
//		
//		AdPicVO vo = new AdPicVO();
//		vo.setAdPicSN(1);
//		vo.setOrderSN(1);
//		vo.setPic(b);
//		
//		AdPicDAO dao = new AdPicDAO();
//		dao.insert(vo);
//		System.out.println("加入成功");
		//insert 測試ok
		
		//測試findByorderSN OK
//		AdPicDAO dao = new AdPicDAO();
//		List<AdPicVO> L1 = dao.findByorderSN(1);
//		for(AdPicVO xx:L1) {
//			System.out.println(xx.getAdPicSN());
//		}
		//測試findByorderSN OK
		
		//測試getAll OK
		AdPicDAO dao = new AdPicDAO();
		List<AdPicVO> L1 = dao.getAll();
		for(AdPicVO xx:L1) {
			System.out.println(xx.getAdPicSN());
		}
		//測試getAll OK
		
	}
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動成功");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public int insert(AdPicVO AdPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try{
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, AdPicVO.getAdPicSN());
			pstmt.setInt(2, AdPicVO.getOrderSN());
			pstmt.setBytes(3, AdPicVO.getPic());
			
			i = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
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
		return i;
	}

	@Override
	public void update(AdPicVO AdPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setBytes(1, AdPicVO.getPic());
			
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
	public List<AdPicVO> findByorderSN(Integer orderSN) {
		AdPicVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdPicVO> list1 = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, orderSN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new AdPicVO();
				vo.setAdPicSN(rs.getInt("adPicSN"));
				vo.setPic(rs.getBytes("Pic"));
				list1.add(vo);
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
		return list1;
	}

	@Override
	public List<AdPicVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AdPicVO> list = new ArrayList<>();
		AdPicVO vo = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new AdPicVO();
				vo.setAdPicSN(rs.getInt("adPicSN"));
				vo.setOrderSN(rs.getInt("orderSN"));
				vo.setPic(rs.getBytes("pic"));
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

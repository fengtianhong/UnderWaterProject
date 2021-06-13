package com.articleReport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleReportDAO implements ArticleReportDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO ArticleReport (userID, articleSN, rptReason) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT rptSN, userID, articleSN, rptReason, rptResult,reRptResult FROM ArticleReport order by rptSN";
	private static final String GET_ONE_STMT = 
			"SELECT rptSN, userID, articleSN, rptReason, rptResult,reRptResult FROM ArticleReport where rptSN = ?";
	private static final String UPDATE = 
			"UPDATE ArticleReport set rptSN = ?, rptReason = ?, userID = ?, articleSN = ?, rptResult = ?, reRptResult = ? where rptSN = ?";
	
	

//	新增檢舉文章
	@Override
	public void insert(ArticleReportVO articleReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, articleReportVO.getUserID());
			pstmt.setInt(2, articleReportVO.getArticleSN());
			pstmt.setString(3, articleReportVO.getRptReason());
//			pstmt.setString(4, articleReportVO.getRptResult());
//			pstmt.setString(5, articleReportVO.getReRptResult());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public void update(ArticleReportVO articleReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, articleReportVO.getRptReason());
			pstmt.setInt(2, articleReportVO.getUserID());
			pstmt.setInt(3, articleReportVO.getArticleSN());
			pstmt.setString(4, articleReportVO.getRptResult());
			pstmt.setString(5, articleReportVO.getReRptResult());
			pstmt.setInt(6, articleReportVO.getRptSN());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public ArticleReportVO findByPrimaryKey(Integer rptSN) {
		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rptSN);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleReportVO = new ArticleReportVO();
				articleReportVO.setRptSN(rs.getInt("rptSN"));
				articleReportVO.setUserID(rs.getInt("userID"));
				articleReportVO.setArticleSN(rs.getInt("articleSN"));
				articleReportVO.setRptReason(rs.getString("rptReason"));
				articleReportVO.setRptResult(rs.getString("rptResult"));
				articleReportVO.setReRptResult(rs.getString("reRptResult"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return articleReportVO;
	}
		
	
	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO articleReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleReportVO = new ArticleReportVO();
				articleReportVO.setRptSN(rs.getInt("rptSN"));
				articleReportVO.setUserID(rs.getInt("userID"));
				articleReportVO.setArticleSN(rs.getInt("articleSN"));
				articleReportVO.setRptReason(rs.getString("rptReason"));
				articleReportVO.setRptResult(rs.getString("rptResult"));
				articleReportVO.setReRptResult(rs.getString("reRptResult"));
				list.add(articleReportVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
		return list;
	}
}

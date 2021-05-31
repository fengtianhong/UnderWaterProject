package com.articleReport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO ArticleReport (reportReason, userID, articleSN) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT reportSN, reportReason, userID, articleSN FROM ArticleReport order by reportSN";
	private static final String GET_ONE_STMT = 
			"SELECT reportSN, reportReason, userID, articleSN FROM ArticleReport where reportSN = ?";
	private static final String UPDATE = 
			"UPDATE ArticleReport set reportReason=?, userID=?, articleSN=? where reportSN = ?";
	
	

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
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
		
	}
	@Override
	public ArticleReportVO findByPrimaryKey(Integer reportSN) {
		return null;
	}
	@Override
	public List<ArticleReportVO> getAll() {
		return null;
	}

	

}

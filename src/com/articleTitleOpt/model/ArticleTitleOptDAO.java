package com.articleTitleOpt.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class ArticleTitleOptDAO implements ArticleTitleOptDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO ArticleTitleOpt(articleTitleOptText) VALUES (?)";
	private static final String UPDATE = "UPDATE ArticleTitleOpt set articleTitleOptText=? where articleTitleOptSN = ?";
	private static final String GET_ALL_STMT = "SELECT articleTitleOptSN, articleTitleOptText FROM ArticleTitleOpt order by articleTitleOptSN";
	private static final String GET_ONE_STMT = "SELECT articleTitleOptSN, articleTitleOptText FROM ArticleTitleOpt where articleTitleOptSN = ?";

//	新增文章標題欄位
	@Override
	public void insert(ArticleTitleOptVO articleTitleOptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleTitleOptVO.getArticleTitleOptText());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//	更改標題欄位
	@Override
	public void update(ArticleTitleOptVO articleTitleOptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, articleTitleOptVO.getArticleTitleOptText());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
	@Override
	public ArticleTitleOptVO findByPrimaryKey(Integer articleTitleOptSN) {
		ArticleTitleOptVO articleTitleOptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, articleTitleOptSN);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleTitleOptVO = new ArticleTitleOptVO();
				articleTitleOptVO.setArticleTitleOptSN(rs.getInt("ArticleTitleOptSN"));
				articleTitleOptVO.setArticleTitleOptText(rs.getString("ArticleTitleOptText"));
			}
		}catch(SQLException se) {
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
		return articleTitleOptVO;
	}

//	查全部
	@Override
	public List<ArticleTitleOptVO> getAll() {
		List<ArticleTitleOptVO> list = new ArrayList<ArticleTitleOptVO>();
		ArticleTitleOptVO articleTitleOptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				articleTitleOptVO = new ArticleTitleOptVO();
				articleTitleOptVO.setArticleTitleOptSN(rs.getInt("ArticleTitleOptSN"));
				articleTitleOptVO.setArticleTitleOptText(rs.getString("ArticleTitleOptText"));
				list.add(articleTitleOptVO);
			}
		}catch (SQLException se) {
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
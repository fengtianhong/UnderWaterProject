package com.forumArticle.model;

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



public class ForumArticleDAO implements ForumArticleDAO_interface{
	
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
			"INSERT INTO ForumArticle (articleTitle, articleText, userID, articleTitleOptSN) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM ForumArticle where articleStatus = 1 order by articleSN desc ";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM ForumArticle where articleSN = ?";
		private static final String hiddenAtricle = 
			"UPDATE ForumArticle set articleStatus = 0 where articleSN = ?";
		private static final String userUPDATE = 
			"UPDATE ForumArticle set articleTitle = ?, articleText = ? where articleSN = ?";
		
//	新增文章
	@Override
	public void insert(ForumArticleVO forumArticleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, forumArticleVO.getArticleTitle());

			pstmt.setString(2, forumArticleVO.getArticleText());

			pstmt.setInt(3, forumArticleVO.getUserID());

			pstmt.setInt(4, forumArticleVO.getArticleTitleOptSN());

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

//	
	@Override
	public void hiddenAtricle(Integer articleSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(hiddenAtricle);
			
			pstmt.setInt(1, articleSN);
			
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
//	修改文章(使用者)	
	@Override
	public void userUpdate(ForumArticleVO forumArticleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(userUPDATE);
			
			pstmt.setString(1, forumArticleVO.getArticleTitle());
			pstmt.setString(2, forumArticleVO.getArticleText());
			pstmt.setInt(3, forumArticleVO.getArticleSN());
			
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
	public ForumArticleVO findByPrimaryKey(Integer articleSN) {
		ForumArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, articleSN);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleSN(rs.getInt("articleSN"));
				forumArticleVO.setArticleTitle(rs.getString("articleTitle"));
				forumArticleVO.setPublishedDate(rs.getTimestamp("publishedDate"));
				forumArticleVO.setArticleText(rs.getString("articleText"));
				forumArticleVO.setArticleStatus(rs.getBoolean("articleStatus"));
				forumArticleVO.setUserID(rs.getInt("userID"));
				forumArticleVO.setArticleTitleOptSN(rs.getInt("articleTitleOptSN"));
				forumArticleVO.setRateGCount(rs.getInt("rateGCount"));
				forumArticleVO.setRateNGCount(rs.getInt("rateNGCount"));	
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
		return forumArticleVO;
	}

	@Override
	public List<ForumArticleVO> getAll() {
		List<ForumArticleVO> list = new ArrayList<ForumArticleVO>();
		ForumArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleSN(rs.getInt("articleSN"));
				forumArticleVO.setArticleTitle(rs.getString("articleTitle"));
				forumArticleVO.setPublishedDate(rs.getTimestamp("publishedDate"));
				forumArticleVO.setArticleText(rs.getString("articleText"));
				forumArticleVO.setArticleStatus(rs.getBoolean("articleStatus"));
				forumArticleVO.setUserID(rs.getInt("userID"));
				forumArticleVO.setArticleTitleOptSN(rs.getInt("articleTitleOptSN"));
				forumArticleVO.setRateGCount(rs.getInt("rateGCount"));
				forumArticleVO.setRateNGCount(rs.getInt("rateNGCount"));
				list.add(forumArticleVO);
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
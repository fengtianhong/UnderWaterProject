package com.forumRate.model;

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

public class ForumRateDAO implements ForumRateDAO_interface{
	
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
			"INSERT INTO ForumRate (userID, articleSN, articleRate) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT articleRateSN, userID, articleSN, articleRate FROM ForumRate order by articleRateSN";
		private static final String GET_ONE_STMT = 
			"SELECT articleRateSN, userID, articleSN, articleRate FROM ForumRate FROM ForumRate where articleRateSN = ?";
		private static final String DELETE = 
			"DELETE FROM ForumRate where articleRateSN = ?";
		private static final String UPDATE = 
			"UPDATE ForumRate set userID = ?, articleSN = ?, articleRate = ? where articleRateSN = ?";
//			更新應該是對評價更新，不會動到其他資料？
		
//	public static void main(String[] args) {
//		ForumRateDAO frdao = new ForumRateDAO();
//		ForumRateVO frvo = new ForumRateVO();
//		frvo = new ForumRateVO();
//		frvo.setUserID(3);
//		frvo.setArticleSN(30001);
//		frvo.setArticleRate(true);
//	}
		
	@Override
	public void insert(ForumRateVO forumRateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, forumRateVO.getUserID());
			pstmt.setInt(2, forumRateVO.getArticleSN());
			pstmt.setBoolean(3, forumRateVO.getArticleRate());
			
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
	public void update(ForumRateVO forumRateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, forumRateVO.getUserID());
			pstmt.setInt(2, forumRateVO.getArticleSN());
			pstmt.setBoolean(3, forumRateVO.getArticleRate());
			pstmt.setInt(4, forumRateVO.getArticleRateSN());
			
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
	
//	評價收回
	@Override
	public void delete(Integer articleRateSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleRateSN);

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
	public ForumRateVO findByPrimaryKey(Integer articleRateSN) {
		ForumRateVO forumRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, articleRateSN);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumRateVO = new ForumRateVO();
				forumRateVO.setArticleRateSN(rs.getInt("articleRateSN"));
				forumRateVO.setUserID(rs.getInt("userID"));
				forumRateVO.setArticleSN(rs.getInt("articleSN"));
				forumRateVO.setArticleRate(rs.getBoolean("articleRate"));				
			}
		}  catch (SQLException se) {
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
		return forumRateVO;
	}


	@Override
	public List<ForumRateVO> getAll() {
		
		List<ForumRateVO> list = new ArrayList<ForumRateVO>();
		ForumRateVO forumRateVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumRateVO = new ForumRateVO();
				forumRateVO.setArticleRateSN(rs.getInt("articleRateSN"));
				forumRateVO.setUserID(rs.getInt("userID"));
				forumRateVO.setArticleSN(rs.getInt("articleSN"));
				forumRateVO.setArticleRate(rs.getBoolean("articleRate"));
				list.add(forumRateVO);
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

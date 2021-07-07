package com.forumComment.model;

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

public class ForumCommentDAO implements ForumCommentDAO_interface{
	
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
			"INSERT INTO ForumComment (cmtText, userID, articleSN) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT cmtSN, cmtDate, cmtText, userID, articleSN FROM ForumComment order by cmtSN";
		private static final String GET_ONE_STMT = 
			"SELECT  * FROM ForumComment where articleSN = ?";
		
//		private static final String GET_ONE_STMT = 
//				"SELECT  * FROM ForumComment where cmtSN = ?";
		
		
		private static final String DELETE = 
			"DELETE FROM ForumComment where cmtSN = ?";
		private static final String UPDATE = 
			"UPDATE ForumComment set cmtText = ? , userID = ?, articleSN = ?  where cmtSN = ?";

		
	@Override
	public void insert(ForumCommentVO forumCommentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, forumCommentVO.getCmtText());
			pstmt.setInt(2, forumCommentVO.getUserID());
			pstmt.setInt(3, forumCommentVO.getArticleSN());
			
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
	public void update(ForumCommentVO forumCommentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setString(1, forumCommentVO.getCmtText());
			pstmt.setInt(2, forumCommentVO.getUserID());
			pstmt.setDouble(3, forumCommentVO.getArticleSN());
			pstmt.setInt(4, forumCommentVO.getCmtSN());
			
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
	public void delete(Integer cmtSN) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cmtSN);

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
//	public List<ForumCommentVO> findByPrimaryKey(Integer articleSN) {
	public List<ForumCommentVO> findByPrimaryKey(Integer cmtSN) {
		
		List<ForumCommentVO> list = new ArrayList<ForumCommentVO>();
		ForumCommentVO forumCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

//			pstmt.setInt(1, articleSN);
			pstmt.setInt(1, cmtSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtSN(rs.getInt("cmtSN"));
				forumCommentVO.setCmtDate(rs.getTimestamp("cmtDate"));
				forumCommentVO.setCmtText(rs.getString("cmtText"));
				forumCommentVO.setUserID(rs.getInt("userID"));
				forumCommentVO.setArticleSN(rs.getInt("articleSN"));
				
				list.add(forumCommentVO);
				
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
		return list;
	}

	
	@Override
	public List<ForumCommentVO> getAll() {
		
		List<ForumCommentVO> list = new ArrayList<ForumCommentVO>();
		ForumCommentVO forumCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtSN(rs.getInt("cmtSN"));
				forumCommentVO.setCmtDate(rs.getTimestamp("cmtDate"));
				forumCommentVO.setCmtText(rs.getString("cmtText"));
				forumCommentVO.setUserID(rs.getInt("userID"));
				forumCommentVO.setArticleSN(rs.getInt("articleSN"));
				list.add(forumCommentVO);
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
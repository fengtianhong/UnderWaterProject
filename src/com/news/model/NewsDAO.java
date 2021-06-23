package com.news.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class NewsDAO implements NewsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO News (title, content, image,newsDate, newsFrom, newsType) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE News set title=?, content=?, image=?, newsDate=?, newsFrom=?, newsType=? where newsSN = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM News WHERE newsSN = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM News order by newsDate desc";
	private static final String DELETE_STMT = "DELETE FROM News WHERE newsSN = ?";

	@Override
	public void delete(Integer newsSN) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, newsSN);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, newsVO.getTitle());
			ps.setString(2, newsVO.getContent());
			ps.setBytes(3, newsVO.getImage());
			ps.setDate(4, newsVO.getNewsDate());
			ps.setString(5, newsVO.getNewsFrom());
			ps.setString(6, newsVO.getNewsType());
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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setString(1, newsVO.getTitle());
			ps.setString(2, newsVO.getContent());
			ps.setBytes(3, newsVO.getImage());
			ps.setDate(4, newsVO.getNewsDate());
			ps.setString(5, newsVO.getNewsFrom());
			ps.setString(6, newsVO.getNewsType());
			ps.setInt(7, newsVO.getNewsSN());
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
	public NewsVO findByPrimaryKey(Integer newsSN) {

		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_STMT);

			ps.setInt(1, newsSN);

			rs = ps.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsSN(rs.getInt("newsSN"));
				newsVO.setTitle(rs.getString("title"));
				newsVO.setImage(rs.getBytes("image"));
				newsVO.setContent(rs.getString("content"));
				newsVO.setNewsDate(rs.getDate("newsDate"));
				newsVO.setNewsFrom(rs.getString("newsFrom"));
				newsVO.setNewsType(rs.getString("newsType"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsSN(rs.getInt("newsSN"));
				newsVO.setTitle(rs.getString("title"));
				newsVO.setImage(rs.getBytes("image"));
				newsVO.setContent(rs.getString("content"));
				newsVO.setNewsDate(rs.getDate("newsDate"));
				newsVO.setNewsFrom(rs.getString("newsFrom"));
				newsVO.setNewsType(rs.getString("newsType"));
				list.add(newsVO);
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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

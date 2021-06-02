package com.chat.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class ChatDAO implements ChatDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Chat(fromAccount, toAccount, content, dateTime) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Chat WHERE fromAccount = ? or toAccount = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Chat WHERE (fromAccount = ? and toAccount = ?) or (fromAccount = ? and toAccount = ?)";

	@Override
	public void insert(ChatVO ChatVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, ChatVO.getFromAccount());
			ps.setInt(2, ChatVO.getToAccount());
			ps.setString(3, ChatVO.getContent());
			ps.setTimestamp(4, ChatVO.getDateTime());
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
	public List<ChatVO> findByAccount(Integer UserID) {
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO= null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_STMT);
			ps.setInt(1, UserID);
			ps.setInt(2, UserID);
			rs= ps.executeQuery();
			while(rs.next()) {
				chatVO= new ChatVO();
				chatVO.setChatSN(rs.getInt("chatSN"));
				chatVO.setFromAccount(rs.getInt("fromAccount"));
				chatVO.setToAccount(rs.getInt("toAccount"));
				chatVO.setContent(rs.getString("content"));
				chatVO.setDateTime(rs.getTimestamp("dateTime"));
				list.add(chatVO);
			}
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
		return list;
	}

	@Override
	public List<ChatVO> findByAtoB(Integer UserA, Integer UserB) {
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO= null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, UserA);
			ps.setInt(2, UserB);
			ps.setInt(3, UserB);
			ps.setInt(4, UserA);
			rs= ps.executeQuery();
			while(rs.next()) {
				chatVO= new ChatVO();
				chatVO.setChatSN(rs.getInt("chatSN"));
				chatVO.setFromAccount(rs.getInt("fromAccount"));
				chatVO.setToAccount(rs.getInt("toAccount"));
				chatVO.setContent(rs.getString("content"));
				chatVO.setDateTime(rs.getTimestamp("dateTime"));
				list.add(chatVO);
			}
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
		return list;
	}

}

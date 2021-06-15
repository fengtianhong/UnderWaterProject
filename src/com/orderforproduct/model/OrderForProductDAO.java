package com.orderforproduct.model;

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

public class OrderForProductDAO implements OrderForProductDAO_interface {

	private static final String INSERT_STMT = "INSER INTO OrderForProduct (userID, totalPrice, orderStatus) VALUES (?, ?, ?)";
	private static final String CHANGESTATUS_STMT = "UPDATE OrderForProduct SET productStatus = ? WHERE orderSN = ?";
	private static final String UPDATE_STMT = "UPDATE OrderForProduct SET userID = ?, purchaseDate = ?, totalPrice = ?,"
			+ "clearDate = ? WHERE orderSN = ?";
	private static final String GET_ONE_BY_ORDERSN = "SELETE * FROM OderForProduct WHERE orderSN = ?";
	private static final String GET_ALL = "SELETE * FROM OrderForProduct ORDER BY orderSN";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(OrderForProductVO orderForProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderForProductVO.getUserID());
			pstmt.setInt(2, orderForProductVO.getTotalPrice());
			pstmt.setString(3, orderForProductVO.getOrderStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
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
	public void changeStatus(OrderForProductVO orderForProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGESTATUS_STMT);

			pstmt.setString(1, orderForProductVO.getOrderStatus());
			pstmt.setInt(2, orderForProductVO.getOrderSN());

			pstmt.executeUpdate();

		} catch (SQLException e) {
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
	public void update(OrderForProductVO orderForProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, orderForProductVO.getUserID());
			pstmt.setTimestamp(2, orderForProductVO.getPurchaseDate());
			pstmt.setInt(3, orderForProductVO.getTotalPrice());
			pstmt.setTimestamp(4, orderForProductVO.getClearDate());
			pstmt.setInt(5, orderForProductVO.getOrderSN());

			pstmt.executeUpdate();

		} catch (SQLException e) {
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
	public OrderForProductVO getOneByOrderSN(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderForProductVO orderForProductVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ORDERSN);

			pstmt.setInt(1, orderSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderForProductVO = new OrderForProductVO();
				int oSN = rs.getInt("orderSN");
				orderForProductVO.setOrderSN(oSN);

				orderForProductVO.setUserID(rs.getInt("userID"));
				orderForProductVO.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				orderForProductVO.setTotalPrice(rs.getInt("totalPrice"));
				orderForProductVO.setOrderStatus(rs.getString("orderStatus"));
				orderForProductVO.setClearDate(rs.getTimestamp("clearDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return orderForProductVO;
	}

	@Override
	public List<OrderForProductVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderForProductVO orderForProductVO = null;
		List<OrderForProductVO> list = new ArrayList<OrderForProductVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderForProductVO = new OrderForProductVO();
				orderForProductVO.setOrderSN(rs.getInt("orderSN"));
				orderForProductVO.setUserID(rs.getInt("userID"));
				orderForProductVO.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				orderForProductVO.setTotalPrice(rs.getInt("totalPrice"));
				orderForProductVO.setOrderStatus(rs.getString("orderStatus"));
				orderForProductVO.setClearDate(rs.getTimestamp("clearDate"));
				list.add(orderForProductVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return list;
	}

}

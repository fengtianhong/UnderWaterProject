package com.shoppingcar.model;

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

public class ShoppingCarDAO implements ShoppingCarDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO ShoppingCar (userID, productSN, purchaseQuantity,"
			+ "productPrice, totalPrice) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_STMT = "DELETE FROM ShoppingCar WHERE shoppingCarSN = ?";
	private static final String UPDATE_STMT = "UPDATE ShoppingCar SET userID = ?, productSN = ?,"
			+ "purchaseQuantity = ?, productPrice = ?, totalPrice = ? WHERE shoppingCarSN = ?";
	private static final String GET_ONE_BY_SHOPPINGCARSN = "SELECT * FROM ShoppingCar WHERE shoppingCarSN = ?";
	private static final String GET_ALL = "SELECT * FROM ShoppingCar ORDER BY shoppingCarSN";

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
	public void insert(ShoppingCarVO shoppingCarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shoppingCarVO.getUserID());
			pstmt.setInt(2, shoppingCarVO.getProductSN());
			pstmt.setInt(3, shoppingCarVO.getPurchaseQuantity());
			pstmt.setInt(4, shoppingCarVO.getProductPrice());
			pstmt.setInt(5, shoppingCarVO.getTotalPrice());

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
	public void delete(Integer shoppingCarSN) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, shoppingCarSN);

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
	public void update(ShoppingCarVO shoppingCarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, shoppingCarVO.getUserID());
			pstmt.setInt(2, shoppingCarVO.getProductSN());
			pstmt.setInt(3, shoppingCarVO.getPurchaseQuantity());
			pstmt.setInt(4, shoppingCarVO.getProductPrice());
			pstmt.setInt(5, shoppingCarVO.getTotalPrice());
			pstmt.setInt(6, shoppingCarVO.getShoppingCarSN());

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
	public ShoppingCarVO getOneByShoppingCarSN(Integer shoppingCarSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShoppingCarVO shoppingCarVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_SHOPPINGCARSN);

			pstmt.setInt(1, shoppingCarSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shoppingCarVO = new ShoppingCarVO();
				shoppingCarVO.setShoppingCarSN(rs.getInt("shoppingCarSN"));
				shoppingCarVO.setUserID(rs.getInt("userID"));
				shoppingCarVO.setProductSN(rs.getInt("productSN"));
				shoppingCarVO.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				shoppingCarVO.setProductPrice(rs.getInt("productPrice"));
				shoppingCarVO.setTotalPrice(rs.getInt("totalPrice"));
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

		return shoppingCarVO;
	}

	@Override
	public List<ShoppingCarVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShoppingCarVO shoppingCarVO = null;
		List<ShoppingCarVO> list = new ArrayList<ShoppingCarVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shoppingCarVO = new ShoppingCarVO();
				shoppingCarVO.setShoppingCarSN(rs.getInt("shoppingCarSN"));
				shoppingCarVO.setUserID(rs.getInt("userID"));
				shoppingCarVO.setProductSN(rs.getInt("productSN"));
				shoppingCarVO.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				shoppingCarVO.setProductPrice(rs.getInt("productPrice"));
				shoppingCarVO.setTotalPrice(rs.getInt("totalPrice"));
				list.add(shoppingCarVO);
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

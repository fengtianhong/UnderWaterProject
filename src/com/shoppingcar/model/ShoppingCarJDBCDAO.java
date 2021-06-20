package com.shoppingcar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orderlist.model.OrderListJDBCDAO;

public class ShoppingCarJDBCDAO implements ShoppingCarDAO_interface {

	String driver = util.Util.DRIVER;
	String url = util.Util.URL;
	String userid = "robert";
	String passwd = "55688";

	private static final String INSERT_STMT = "INSERT INTO ShoppingCar (userID, productSN, purchaseQuantity,"
			+ "productPrice, totalPrice) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_STMT = "DELETE FROM ShoppingCar WHERE shoppingCarSN = ?";
	private static final String UPDATE_STMT = "UPDATE ShoppingCar SET userID = ?, productSN = ?,"
			+ "purchaseQuantity = ?, productPrice = ?, totalPrice = ? WHERE shoppingCarSN = ?";
	private static final String GET_ONE_BY_SHOPPINGCARSN = "SELECT * FROM ShoppingCar WHERE shoppingCarSN = ?";
	private static final String GET_ALL = "SELECT * FROM ShoppingCar ORDER BY shoppingCarSN";

	public static void main(String[] args) {

		ShoppingCarJDBCDAO dao = new ShoppingCarJDBCDAO();

		// 新增
//		ShoppingCarVO o1 = new ShoppingCarVO();
//		o1.setUserID(4);
//		o1.setProductSN(1);
//		o1.setPurchaseQuantity(50);
//		o1.setProductPrice(1000);
//		o1.setTotalPrice(50000);
//		dao.insert(o1);

		// 移除
//		dao.delete(8);

		// 修改
//		ShoppingCarVO o1 = new ShoppingCarVO();
//		o1.setUserID(1);
//		o1.setProductSN(4);
//		o1.setPurchaseQuantity(20);
//		o1.setProductPrice(400);
//		o1.setTotalPrice(4000);
//		o1.setShoppingCarSN(9);
//		dao.update(o1);

		// 功能查詢
//		ShoppingCarVO o2 = dao.getOneByShoppingCarSN(1);
//		System.out.print(o2.getShoppingCarSN());
//		System.out.print(o2.getUserID());
//		System.out.print(o2.getProductSN());
//		System.out.print(o2.getPurchaseQuantity());
//		System.out.print(o2.getProductPrice());
//		System.out.print(o2.getTotalPrice());
//		
//		System.out.println("---------------------");

		// 查詢全部
		List<ShoppingCarVO> list = dao.getAll();
		for (ShoppingCarVO o1 : list) {
			System.out.print(o1.getShoppingCarSN());
			System.out.print(o1.getUserID());
			System.out.print(o1.getProductSN());
			System.out.print(o1.getPurchaseQuantity());
			System.out.print(o1.getProductPrice());
			System.out.print(o1.getTotalPrice());
		}
	}

	@Override
	public void insert(ShoppingCarVO shoppingCarVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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

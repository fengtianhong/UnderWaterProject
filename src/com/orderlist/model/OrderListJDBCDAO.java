package com.orderlist.model;

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

import com.orderforproduct.model.OrderForProductJDBCDAO;

import util.Util;

public class OrderListJDBCDAO implements OrderListDAO_interface {

	String driver = util.Util.DRIVER;
	String url = util.Util.URL;
	String userid = "robert";
	String passwd = "55688";

	private static final String INSERT_STMT = "INSERT INTO OrderList (productSN, orderSN, purchaseQuantity,"
			+ "productPrice, rating) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_STMT = "DELETE FROM OrderList WHERE orderListSN = ?";
	private static final String UPDATE_STMT = "UPDATE OrderList SET productSN = ?, orderSN = ?,"
			+ "purchaseQuantity = ?, productPrice = ? WHERE orderListSN = ?";
	private static final String GET_ONE_BY_ORDERLISTSN = "SELECT * FROM OrderList WHERE orderListSN = ?";
	private static final String GET_ALL = "SELECT * FROM OrderList ORDER BY orderListSN";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {

		OrderListJDBCDAO dao = new OrderListJDBCDAO();

		// 新增
//		OrderListVO o1 = new OrderListVO();
//		o1.setProductSN(5);
//		o1.setOrderSN(6);
//		o1.setPurchaseQuantity(100);
//		o1.setProductPrice(100);
//		o1.setRating(99);
//		dao.insert(o1);

		// 移除
//		dao.delete(13);

		// 修改
//		OrderListVO o1 = new OrderListVO();
//		o1.setProductSN(2);
//		o1.setOrderSN(4);
//		o1.setPurchaseQuantity(1);
//		o1.setProductPrice(500);
//		o1.setOrderListSN(14);
//		dao.update(o1);

		// 功能查詢
//		OrderListVO o1 = dao.getOneByOrderListSN(1);
//		System.out.print(o1.getOrderListSN());
//		System.out.print(o1.getProductSN());
//		System.out.print(o1.getOrderSN());
//		System.out.print(o1.getPurchaseQuantity());
//		System.out.print(o1.getProductPrice());
//		System.out.print(o1.getRating());
//		
//		System.out.println("---------------------");

		// 查詢全部
		List<OrderListVO> list = dao.getAll();
		for (OrderListVO o : list) {
			System.out.print(o.getOrderListSN());
			System.out.print(o.getProductSN());
			System.out.print(o.getOrderSN());
			System.out.print(o.getPurchaseQuantity());
			System.out.print(o.getProductPrice());
			System.out.print(o.getRating());
			System.out.println();
		}
	}

	@Override
	public void insert(OrderListVO orderListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderListVO.getProductSN());
			pstmt.setInt(2, orderListVO.getOrderSN());
			pstmt.setInt(3, orderListVO.getPurchaseQuantity());
			pstmt.setInt(4, orderListVO.getProductPrice());
			pstmt.setInt(5, orderListVO.getRating());

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
	public void delete(Integer orderListSN) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, orderListSN);

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
	public void update(OrderListVO orderListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, orderListVO.getProductSN());
			pstmt.setInt(2, orderListVO.getOrderSN());
			pstmt.setInt(3, orderListVO.getPurchaseQuantity());
			pstmt.setInt(4, orderListVO.getProductPrice());
			pstmt.setInt(5, orderListVO.getOrderListSN());

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
	public OrderListVO getOneByOrderListSN(Integer orderListSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderListVO orderListVO = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_ORDERLISTSN);

			pstmt.setInt(1, orderListSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderListVO = new OrderListVO();
				orderListVO.setOrderListSN(rs.getInt("orderListSN"));
				orderListVO.setProductSN(rs.getInt("productSN"));
				orderListVO.setOrderSN(rs.getInt("orderSN"));
				orderListVO.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				orderListVO.setProductPrice(rs.getInt("productPrice"));
				orderListVO.setRating(rs.getInt("rating"));
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

		return orderListVO;
	}

	@Override
	public List<OrderListVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderListVO = new OrderListVO();
				orderListVO.setOrderListSN(rs.getInt("orderListSN"));
				orderListVO.setProductSN(rs.getInt("productSN"));
				orderListVO.setOrderSN(rs.getInt("orderSN"));
				orderListVO.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				orderListVO.setProductPrice(rs.getInt("productPrice"));
				orderListVO.setRating(rs.getInt("rating"));
				list.add(orderListVO);
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

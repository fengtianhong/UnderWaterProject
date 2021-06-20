package com.orderforproduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductJDBCDAO;

import util.Util;

public class OrderForProductJDBCDAO implements OrderForProductDAO_interface {

	String driver = util.Util.DRIVER;
	String url = util.Util.URL;
	String userid = "robert";
	String passwd = "55688";

	private static final String INSERT_STMT = "INSERT INTO OrderForProduct (userID, totalPrice, orderStatus) VALUES (?, ?, ?)";
	private static final String CHANGESTATUS_STMT = "UPDATE OrderForProduct SET orderStatus = ? WHERE orderSN = ?";
	private static final String UPDATE_STMT = "UPDATE OrderForProduct SET userID = ?, purchaseDate = ?, totalPrice = ?,"
			+ "clearDate = ? WHERE orderSN = ?";
	private static final String GET_ONE_BY_ORDERSN = "SELECT * FROM OrderForProduct WHERE orderSN = ?";
	private static final String GET_ALL = "SELECT * FROM OrderForProduct ORDER BY orderSN";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {

		OrderForProductJDBCDAO dao = new OrderForProductJDBCDAO();

		// 新增
//		OrderForProductVO o1 = new OrderForProductVO();
//		o1.setUserID(1);
//		o1.setPurchaseDate(Timestamp.valueOf("2021-06-06 12:21:12"));
//		o1.setTotalPrice(5000);
//		o1.setOrderStatus("1");
//		dao.insert(o1);

		// 更新狀態
//		OrderForProductVO o2 = new OrderForProductVO();
//		o2.setOrderStatus("1");
//		o2.setOrderSN(6);
//		dao.changeStatus(o2);

		// 修改
//		OrderForProductVO o3 = new OrderForProductVO();
//		o3.setUserID(5);
//		o3.setPurchaseDate(Timestamp.valueOf("2021-07-07 12:21:12"));
//		o3.setTotalPrice(4000);
//		o3.setClearDate(Timestamp.valueOf("2021-08-08 12:21:12"));
//		o3.setOrderSN(6);
//		dao.update(o3);

		// 功能查詢
//		OrderForProductVO os = dao.getOneByOrderSN(1);
//		System.out.print(os.getOrderSN());
//		System.out.print(os.getUserID());
//		System.out.print(os.getPurchaseDate());
//		System.out.print(os.getTotalPrice());
//		System.out.print(os.getOrderStatus());
//		System.out.print(os.getClearDate());
//		
//		System.out.println("---------------------");

		// 查詢全部
		List<OrderForProductVO> list = dao.getAll();
		for (OrderForProductVO oo : list) {
			System.out.print(oo.getOrderSN());
			System.out.print(oo.getUserID());
			System.out.print(oo.getPurchaseDate());
			System.out.print(oo.getTotalPrice());
			System.out.print(oo.getOrderStatus());
			System.out.print(oo.getClearDate());
			System.out.println();
		}
	}

	@Override
	public void insert(OrderForProductVO orderForProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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
			con = DriverManager.getConnection(url, userid, passwd);
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

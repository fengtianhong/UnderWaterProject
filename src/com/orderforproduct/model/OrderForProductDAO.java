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

import com.orderlist.model.OrderListDAO;
import com.orderlist.model.OrderListVO;

public class OrderForProductDAO implements OrderForProductDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO OrderForProduct (userID, totalPrice, orderStatus) VALUES (?, ?, ?)";
	private static final String CHANGESTATUS_STMT = "UPDATE OrderForProduct SET orderStatus = ? WHERE orderSN = ?";
	private static final String UPDATE_STMT = "UPDATE OrderForProduct SET userID = ?, purchaseDate = ?, totalPrice = ?,"
			+ "clearDate = ? WHERE orderSN = ?";
	private static final String GET_ONE_BY_ORDERSN = "SELECT * FROM OrderForProduct WHERE orderSN = ?";
	private static final String GET_ALL = "SELECT * FROM OrderForProduct ORDER BY orderSN";

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

	@Override
	public void insertWithOrderList(OrderForProductVO orderForProductVO, List<OrderListVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String nextOrderSN = null;

		try {
			con = ds.getConnection();

			// 關閉自動交易控制
			con.setAutoCommit(false);

			// 先新增訂單
			String cols[] = { "productSN" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, orderForProductVO.getUserID());
			pstmt.setInt(2, orderForProductVO.getTotalPrice());
			pstmt.setString(3, orderForProductVO.getOrderStatus());
			pstmt.executeUpdate();

			// 取得對應的自增主鍵值
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrderSN = rs.getString("productSN");
				System.out.println("自增主鍵值= " + nextOrderSN + "(剛剛新增的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 同時新增該訂單主檔內的明細
			OrderListDAO orderListDAO = new OrderListDAO();
			for (OrderListVO orderListVO : list) {
				orderListVO.setOrderSN(new Integer(nextOrderSN));
				orderListDAO.insertWithOrderForProduct(orderListVO, con);
			}

			con.commit();
			con.setAutoCommit(true);

			System.out.println("新增訂單編號" + nextOrderSN + "時，共有明細" + list.size() + "筆同時被新增");

		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error occured. " + e1.getMessage());
				}
			}
			
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			
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

}

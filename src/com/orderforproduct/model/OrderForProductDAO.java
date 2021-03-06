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
			+ " WHERE orderSN = ?";
	
	private static final String GET_ONE_BY_ORDERSN = "SELECT * FROM OrderForProduct WHERE orderSN = ?";
	private static final String GET_ALL = "SELECT * FROM OrderForProduct ORDER BY orderSN";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
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
			pstmt.setInt(4, orderForProductVO.getOrderSN());

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

			// ????????????????????????
			con.setAutoCommit(false);

			// ???????????????
			String cols[] = { "orderSN" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, orderForProductVO.getUserID());
			pstmt.setInt(2, orderForProductVO.getTotalPrice());
			pstmt.setString(3, orderForProductVO.getOrderStatus());
			pstmt.executeUpdate();

			// ??????????????????????????????
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				
				// getString(), ????????????????????????
				nextOrderSN = rs.getString(1);
				System.out.println("???????????????= " + nextOrderSN + "(???????????????????????????)");
			} else {
				System.out.println("????????????????????????");
			}
			rs.close();

			// ???????????????????????????????????????
			OrderListDAO orderListDAO = new OrderListDAO();
			for (OrderListVO orderListVO : list) {
				orderListVO.setOrderSN(new Integer(nextOrderSN));
				orderListDAO.insertWithOrderForProduct(orderListVO, con);
			}

			con.commit();
			con.setAutoCommit(true);

			System.out.println("??????????????????" + nextOrderSN + "??????????????????" + list.size() + "??????????????????");

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

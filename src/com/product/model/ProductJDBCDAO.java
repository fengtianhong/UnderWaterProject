package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class ProductJDBCDAO implements ProductDAO_interface {

	String driver = util.Util.DRIVER;
	String url = util.Util.URL;
	String userid = "robert";
	String passwd = "55688";

	private static final String INSERT_STMT = "INSERT INTO Product (productClass, productName, productPrice,"
			+ "productQuantity, productStatus, productDetail, productDiscount, productPrime, ratingPoint, ratingNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String OFFSHELF_STMT = "UPDATE Product SET productStatus = 0 WHERE productSN = ?";

	private static final String UPDATE_STMT = "UPDATE Product SET productClass = ?, productName = ?, productPrice = ?,"
			+ "productQuantity = ?, productDetail = ?, productCreateTime = ?, productDiscount =?, productPrime = ?, ratingPoint = ?, ratingNumber = ? WHERE productSN = ?";

	private static final String GET_ONE_BY_PRODUCTSN = "SELECT * FROM Product WHERE productSN = ?";

	private static final String GET_PRODUCT_BY_CLASS = "SELECT * FROM Product WHERE productClass = ? ORDER BY productSN";

	private static final String GET_PRODUCT_BY_DISCOUNT = "SELECT * FROM Product WHERE productDiscount = ? ORDER BY productPrice";

	private static final String GET_PRODUCT_BY_PRIME = "SELECT * FROM Product WHERE productPrime = ? ORDER BY productPrice";

	private static final String GET_ALL = "SELECT * FROM Product ORDER BY productSN";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// 新增
//		ProductVO p1 = new ProductVO();
//		p1.setProductClass("氧氣瓶");
//		p1.setProductName("氧氣瓶TEST");
//		p1.setProductPrice(1000);
//		p1.setProductQuantity(10);
//		p1.setProductStatus("1");
//		p1.setProductDetail("便宜啦");
//		p1.setProductDiscount(true);
//		p1.setProductPrime(true);
//		p1.setRatingPoint(22);
//		p1.setRatingNumber(2);
//		dao.insert(p1);

		// 修改
//		ProductVO p2 = new ProductVO();
//		p2.setProductClass("泳鏡");
//		p2.setProductName("2000度泳鏡");
//		p2.setProductPrice(1500);
//		p2.setProductQuantity(35);
//		p2.setProductDetail("跟你說不是還買");
//		p2.setProductCreateTime(Timestamp.valueOf("2021-07-01 12:21:12"));
//		p2.setProductDiscount(false);
//		p2.setProductPrime(false);
//		p2.setRatingPoint(10);
//		p2.setRatingNumber(4);
//		p2.setProductSN(8);
//		dao.update(p2);

		// 單一查詢
//		ProductVO p = dao.getOneByProductSN(2);
//		System.out.print(p.getProductSN());
//		System.out.print(p.getProductClass());
//		System.out.print(p.getProductName());
//		System.out.print(p.getProductPrice());
//		System.out.print(p.getProductQuantity());
//		System.out.print(p.getProductStatus());
//		System.out.print(p.getProductDetail());
//		System.out.print(p.getProductCreateTime());
//		System.out.print(p.getProductDiscount());
//		System.out.print(p.getProductPrime());
//		System.out.print(p.getRatingPoint());
//		System.out.print(p.getRatingNumber());
//		
//		System.out.println("---------------------");

		// 功能查詢複數商品
//		List<ProductVO> list = dao.getAll();
//		List<ProductVO> list = dao.getProductByClass("防寒衣");
//		List<ProductVO> list = dao.getProductByDiscount(true);
//		List<ProductVO> list = dao.getProductByPrime(false);
//		for (ProductVO pa : list) {
//			System.out.print(pa.getProductSN());
//			System.out.print(pa.getProductClass());
//			System.out.print(pa.getProductName());
//			System.out.print(pa.getProductPrice());
//			System.out.print(pa.getProductQuantity());
//			System.out.print(pa.getProductStatus());
//			System.out.print(pa.getProductDetail());
//			System.out.print(pa.getProductCreateTime());
//			System.out.print(pa.getProductDiscount());
//			System.out.print(pa.getProductPrime());
//			System.out.print(pa.getRatingPoint());
//			System.out.print(pa.getRatingNumber());
//			System.out.println();
//		}
	}

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getProductClass());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductPrice());
			pstmt.setInt(4, productVO.getProductQuantity());
			pstmt.setString(5, productVO.getProductStatus());
			pstmt.setString(6, productVO.getProductDetail());
			pstmt.setString(7, productVO.getProductDiscount());
			pstmt.setString(8, productVO.getProductPrime());
			pstmt.setInt(9, productVO.getRatingPoint());
			pstmt.setInt(10, productVO.getRatingNumber());

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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, productVO.getProductClass());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductPrice());
			pstmt.setInt(4, productVO.getProductQuantity());
			pstmt.setString(5, productVO.getProductDetail());
			pstmt.setDate(6, productVO.getProductCreateTime());
			pstmt.setString(7, productVO.getProductDiscount());
			pstmt.setString(8, productVO.getProductPrime());
			pstmt.setInt(9, productVO.getRatingPoint());
			pstmt.setInt(10, productVO.getRatingNumber());
			pstmt.setInt(11, productVO.getProductSN());

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
	public ProductVO getOneByProductSN(Integer productSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_PRODUCTSN);

			pstmt.setInt(1, productSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				int pSN = rs.getInt("productSN"); // 包裝資料一次回傳
				productVO.setProductSN(pSN);

				productVO.setProductClass(rs.getString("productClass"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductPrice(rs.getInt("productPrice"));
				productVO.setProductQuantity(rs.getInt("productQuantity"));
				productVO.setProductStatus(rs.getString("productStatus"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getDate("productCreateTime"));
				productVO.setProductDiscount(rs.getString("productDiscount"));
				productVO.setProductPrime(rs.getString("productPrime"));
				productVO.setRatingPoint(rs.getInt("ratingPoint"));
				productVO.setRatingNumber(rs.getInt("ratingNumber"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getProductByClass(String productClass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PRODUCT_BY_CLASS);

			pstmt.setString(1, productClass);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setProductClass(rs.getString("productClass"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductPrice(rs.getInt("productPrice"));
				productVO.setProductQuantity(rs.getInt("productQuantity"));
				productVO.setProductStatus(rs.getString("productStatus"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getDate("productCreateTime"));
				productVO.setProductDiscount(rs.getString("productDiscount"));
				productVO.setProductPrime(rs.getString("productPrime"));
				productVO.setRatingPoint(rs.getInt("ratingPoint"));
				productVO.setRatingNumber(rs.getInt("ratingNumber"));
				list.add(productVO);
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
	public List<ProductVO> getProductByDiscount(String productDiscount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PRODUCT_BY_DISCOUNT);

			pstmt.setString(1, productDiscount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setProductClass(rs.getString("productClass"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductPrice(rs.getInt("productPrice"));
				productVO.setProductQuantity(rs.getInt("productQuantity"));
				productVO.setProductStatus(rs.getString("productStatus"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getDate("productCreateTime"));
				productVO.setProductDiscount(rs.getString("productDiscount"));
				productVO.setProductPrime(rs.getString("productPrime"));
				productVO.setRatingPoint(rs.getInt("ratingPoint"));
				productVO.setRatingNumber(rs.getInt("ratingNumber"));
				list.add(productVO);
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
	public List<ProductVO> getProductByPrime(String productPrime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PRODUCT_BY_PRIME);

			pstmt.setString(1, productPrime);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setProductClass(rs.getString("productClass"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductPrice(rs.getInt("productPrice"));
				productVO.setProductQuantity(rs.getInt("productQuantity"));
				productVO.setProductStatus(rs.getString("productStatus"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getDate("productCreateTime"));
				productVO.setProductDiscount(rs.getString("productDiscount"));
				productVO.setProductPrime(rs.getString("productPrime"));
				productVO.setRatingPoint(rs.getInt("ratingPoint"));
				productVO.setRatingNumber(rs.getInt("ratingNumber"));
				list.add(productVO);
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
	public List<ProductVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductSN(rs.getInt("productSN"));
				productVO.setProductClass(rs.getString("productClass"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setProductPrice(rs.getInt("productPrice"));
				productVO.setProductQuantity(rs.getInt("productQuantity"));
				productVO.setProductStatus(rs.getString("productStatus"));
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getDate("productCreateTime"));
				productVO.setProductDiscount(rs.getString("productDiscount"));
				productVO.setProductPrime(rs.getString("productPrime"));
				productVO.setRatingPoint(rs.getInt("ratingPoint"));
				productVO.setRatingNumber(rs.getInt("ratingNumber"));
				list.add(productVO);
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

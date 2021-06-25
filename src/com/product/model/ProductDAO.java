package com.product.model;

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

public class ProductDAO implements ProductDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO Product (productClass, productName, productPrice, productQuantity,"
			+ "productStatus, productPhoto, productDetail, productCreateTime, productDiscount, productPrime, ratingPoint, ratingNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE Product SET productClass = ?, productName = ?, productPrice = ?, productQuantity = ?,"
			+ "productStatus = ?, productPhoto = ?, productDetail = ?, productCreateTime = ?, productDiscount =?, productPrime = ?, ratingPoint = ?, ratingNumber = ? WHERE productSN = ?";

	private static final String GET_ONE_BY_PRODUCTSN = "SELECT * FROM Product WHERE productSN = ?";

	private static final String GET_PRODUCT_BY_CLASS = "SELECT * FROM Product WHERE productClass = ? ORDER BY productSN";
	
	private static final String GET_PRODUCT_BY_DISCOUNT = "SELECT * FROM Product WHERE productDiscount = ? ORDER BY productPrice";

	private static final String GET_PRODUCT_BY_PRIME = "SELECT * FROM Product WHERE productPrime = ? ORDER BY productCreateTime";

	private static final String GET_ALL = "SELECT * FROM Product ORDER BY productSN";
	
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
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProductClass());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductPrice());
			pstmt.setInt(4, productVO.getProductQuantity());
			pstmt.setString(5, productVO.getProductStatus());
			pstmt.setBytes(6, productVO.getProductPhoto());
			pstmt.setString(7, productVO.getProductDetail());
			pstmt.setDate(8, productVO.getProductCreateTime());
			pstmt.setString(9, productVO.getProductDiscount());
			pstmt.setString(10, productVO.getProductPrime());
			pstmt.setInt(11, productVO.getRatingPoint());
			pstmt.setInt(12, productVO.getRatingNumber());

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, productVO.getProductClass());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getProductPrice());
			pstmt.setInt(4, productVO.getProductQuantity());
			pstmt.setString(5, productVO.getProductStatus());
			pstmt.setBytes(6, productVO.getProductPhoto());
			pstmt.setString(7, productVO.getProductDetail());
			pstmt.setDate(8, productVO.getProductCreateTime());
			pstmt.setString(9, productVO.getProductDiscount());
			pstmt.setString(10, productVO.getProductPrime());
			pstmt.setInt(11, productVO.getRatingPoint());
			pstmt.setInt(12, productVO.getRatingNumber());
			pstmt.setInt(13, productVO.getProductSN());

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
			con = ds.getConnection();
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
				productVO.setProductPhoto(rs.getBytes("productPhoto"));
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
			con = ds.getConnection();
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
				productVO.setProductPhoto(rs.getBytes("productPhoto"));
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
			con = ds.getConnection();
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
				productVO.setProductPhoto(rs.getBytes("productPhoto"));
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
			con = ds.getConnection();
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
				productVO.setProductPhoto(rs.getBytes("productPhoto"));
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
			con = ds.getConnection();
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
				productVO.setProductPhoto(rs.getBytes("productPhoto"));
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

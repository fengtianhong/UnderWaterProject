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

	private static final String INSERT_STMT = "INSERT INTO Product (productClass, productName, productPrice,"
			+ "productQuantity, productStatus, productDetail, productDiscount, productPrime) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String OFFSHELF_STMT = "UPDATE Product SET productStatus = 0 WHERE productSN = ?";

	private static final String UPDATE_STMT = "UPDATE Product SET productClass = ?, productName = ?, productPrice = ?,"
			+ "productQuantity = ?, productDetail = ?, productCreateTime = ?, productDiscount =?, productPrime = ? WHERE productSN = ?";

	private static final String GET_ONE_BY_PRODUCTSN = "SELECT * FROM Product pdt JOIN ProductPhoto ppto ON pdt.productSN = ppto.productSN WHERE productSN = ?";

	private static final String GET_CLASS_PRODUCT = "SELECT * FROM Product pdt JOIN ProductPhoto ppto ON pdt.productSN = ppto.productSN WHERE productClass = ? ORDER BY productSN";

	private static final String GET_DISCOUNT_PRODUCT = "SELECT * FROM Product pdt JOIN ProductPhoto ppto ON pdt.productSN = ppto.productSN WHERE productDiscount = ? ORDER BY productPrice";

	private static final String GET_PRIME_PRODUCT = "SELECT * FROM Product pdt JOIN ProductPhoto ppto ON pdt.productSN = ppto.productSN WHERE productPrime = ? ORDER BY productPrice";

	private static final String GET_ALL = "SELECT * FROM Product pdt JOIN ProductPhoto ppto ON pdt.productSN = ppto.productSN ORDER BY productSN";

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
			pstmt.setString(6, productVO.getProductDetail());
			pstmt.setBoolean(7, productVO.getProductDiscount());
			pstmt.setBoolean(8, productVO.getProductPrime());

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
	public void offShelf(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(OFFSHELF_STMT);

			pstmt.setInt(1, productVO.getProductSN());

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
			pstmt.setString(5, productVO.getProductDetail());
			pstmt.setTimestamp(6, productVO.getProductCreateTime());
			pstmt.setBoolean(7, productVO.getProductDiscount());
			pstmt.setBoolean(8, productVO.getProductPrime());
			pstmt.setInt(9, productVO.getProductSN());

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
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getTimestamp("productCreateTime"));
				productVO.setProductDiscount(rs.getBoolean("productDiscount"));
				productVO.setProductPrime(rs.getBoolean("productPrime"));
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
	public List<ProductVO> getProductClass(String productClass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CLASS_PRODUCT);

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
				productVO.setProductCreateTime(rs.getTimestamp("productCreateTime"));
				productVO.setProductDiscount(rs.getBoolean("productDiscount"));
				productVO.setProductPrime(rs.getBoolean("productPrime"));
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
	public List<ProductVO> getDiscountProduct(Boolean productDiscount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DISCOUNT_PRODUCT);

			pstmt.setBoolean(1, productDiscount);

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
				productVO.setProductCreateTime(rs.getTimestamp("productCreateTime"));
				productVO.setProductDiscount(rs.getBoolean("productDiscount"));
				productVO.setProductPrime(rs.getBoolean("productPrime"));
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
	public List<ProductVO> getPrimeProduct(Boolean productPrime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO productVO = null;
		List<ProductVO> list = new ArrayList<ProductVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PRIME_PRODUCT);

			pstmt.setBoolean(1, productPrime);

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
				productVO.setProductCreateTime(rs.getTimestamp("productCreateTime"));
				productVO.setProductDiscount(rs.getBoolean("productDiscount"));
				productVO.setProductPrime(rs.getBoolean("productPrime"));
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
				productVO.setProductDetail(rs.getString("productDetail"));
				productVO.setProductCreateTime(rs.getTimestamp("productCreateTime"));
				productVO.setProductDiscount(rs.getBoolean("productDiscount"));
				productVO.setProductPrime(rs.getBoolean("productPrime"));
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

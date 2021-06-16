package com.productphoto.model;

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

public class ProductPhotoDAO implements ProductPhotoDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO ProductPhoto (productSN, productImages) VALUES (?, ?)";
	private static final String DELETE_STMT = "DELETE FROM ProductPhoto WHERE photoSN = ?";
	private static final String UPDATE_STMT = "UPDATE ProductPhoto SET productSN = ?, productImages = ? WHERE photoSN = ?";
	private static final String GET_ON_BY_PHOTOSN = "SELECT * FROM ProductPhoto WHERE photoSN = ?";
	private static final String GET_ALL = "SELECT * FROM ProductPhoto ORDER BY photoSN";

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
	public void insert(ProductPhotoVO productPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productPhotoVO.getProductSN());
			pstmt.setBytes(2, productPhotoVO.getProductImages());

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
	public void delete(Integer photoSN) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, photoSN);

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
	public void update(ProductPhotoVO productPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, productPhotoVO.getProductSN());
			pstmt.setBytes(2, productPhotoVO.getProductImages());
			pstmt.setInt(3, productPhotoVO.getPhotoSN());

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
	public ProductPhotoVO getOneByPhotoSN(Integer photoSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductPhotoVO productPhotoVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ON_BY_PHOTOSN);

			pstmt.setInt(1, photoSN);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productPhotoVO = new ProductPhotoVO();
				productPhotoVO.setPhotoSN(rs.getInt("photoSN"));
				productPhotoVO.setProductSN(rs.getInt("productSN"));
				productPhotoVO.setProductImages(rs.getBytes("productImages"));
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
		return productPhotoVO;
	}

	@Override
	public List<ProductPhotoVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductPhotoVO productPhotoVO = null;
		List<ProductPhotoVO> list = new ArrayList<ProductPhotoVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productPhotoVO = new ProductPhotoVO();
				productPhotoVO.setPhotoSN(rs.getInt("photoSN"));
				productPhotoVO.setProductSN(rs.getInt("productSN"));
				productPhotoVO.setProductImages(rs.getBytes("productImages"));
				list.add(productPhotoVO);
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

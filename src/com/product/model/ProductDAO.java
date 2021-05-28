package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO implements ProductDAO_interface {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://114.34.127.72:3306/UnderWater?serverTimezone=Asia/Taipei";
	private static final String USER = "robert";
	private static final String PASSWORD = "55688";

	private static final String INSERT_STMT = "INSERT INTO Product (productClass,productName,productPrice,"
			+ "productQuantity,productStatus,productDetail,productDiscount,productPrime,productPoint,"
			+ "productNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String OFFSHELF_STMT = "SELETE FROM Product WHERE productSN = ?";

	private static final String UPDATE_STMT = "UPDATE productSN SET productClass = ?, productName = ?, productPrice = ?,"
			+ "productQuantity = ?, productStatus = ?, productDetail = ?, productDiscount =?, productPrime = ?,"
			+ "productPoint = ?, productNumber = ? WHERE productSN = ?";

	private static final String GET_ONE_BY_PRODUCTSN = "SELECT * FROM Product WHERE productSN = ?";

	private static final String GET_ALL = "SELECT * FROM Product";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			
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
	public void offShelf(Integer productSN) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ProductVO productVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductVO getOneByProductSN(Integer productSN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.qa.model;

import java.sql.*;
import java.util.*;
import util.Util;

public class QaDAO implements QaDAO_interface{

	private static final String INSERT_STMT = "INSERT INTO QA (menu, submenu, `system`, question, answer, popularQuestion) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE QA set menu=?, submenu=?, `system`=?, question=?, answer=?, clicks=?, popularQuestion=?, popularQuestionSort=? where questionSN = ?";
	private static final String DELETE_STMT = "DELETE FROM QA where questionSN = ?";
	private static final String GET_QA_BySystem_STMT = "SELECT * FROM QA where `system` = ? order by questionSN";
	private static final String GET_QA_ByMenu_STMT = "SELECT * FROM QA where menu=? and submenu=? order by submenu";
	private static final String GET_QA_ByPopular_STMT = "SELECT * FROM QA where popularQuestion = 1 order by popularQuestionSort";

//		public static void main(String[] args) {		// FOR TEST
//			QaDAO dao = new QaDAO();
//			QaVO qaVO = new QaVO();
//			qaVO.setQuestionSN(6001); // use default
//			qaVO.setMenu("1");	
//			qaVO.setSubmenu("1");
//			qaVO.setSystem("1");
//			qaVO.setQuestion("晚餐吃甚麼? 午餐呢?");
//			qaVO.setAnswer("泡菜海鮮煎餅");
//			qaVO.setClicks(0);
//			qaVO.setPopularQuestion(true);
//			qaVO.setPopularQuestionSort(null);	// insert use default
//			System.out.println(qaVO.toString());
//			dao.insert(qaVO);			// OK
//			dao.update(qaVO);			// OK
//			dao.delete(6002); 				// OK
//			System.out.println(dao.getByMenu("1", "1"));  	// OK
//			System.out.println(dao.getBySystem("1")); 		// OK
//			System.out.println(dao.getPopularQuestion()); 	// OK
//}
	
	@Override
	public void insert(QaVO qaVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, qaVO.getMenu());			
			ps.setString(2, qaVO.getSubmenu());			
			ps.setString(3, qaVO.getSystem());			
			ps.setString(4, qaVO.getQuestion());			
			ps.setString(5, qaVO.getAnswer());						
			ps.setBoolean(6, qaVO.isPopularQuestion());						
			ps.executeUpdate();

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}

	@Override
	public void update(QaVO qaVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setString(1, qaVO.getMenu());			
			ps.setString(2, qaVO.getSubmenu());			
			ps.setString(3, qaVO.getSystem());			
			ps.setString(4, qaVO.getQuestion());			
			ps.setString(5, qaVO.getAnswer());	
			ps.setInt(6, qaVO.getClicks());
			ps.setBoolean(7, qaVO.isPopularQuestion());										
			ps.setInt(8, (qaVO.getPopularQuestionSort()==null)?Types.NULL:qaVO.getPopularQuestionSort() );				
			ps.setInt(9, qaVO.getQuestionSN());						
			ps.executeUpdate();
			System.out.println("success");

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	@Override
	public void delete(Integer questionSN) {	// exception msg need check

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			ps = con.prepareStatement(DELETE_STMT);

			ps.setInt(1, questionSN);
			ps.executeUpdate();

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	@Override
	public List<QaVO> getByMenu(String menu, String submenu) {
		List<QaVO> list = new ArrayList<QaVO>();
		QaVO qaVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				ps = con.prepareStatement(GET_QA_ByMenu_STMT);
				ps.setString(1, menu);
				ps.setString(2, submenu);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					qaVO = new QaVO();
					qaVO.setQuestionSN(rs.getInt("questionSN"));
					qaVO.setMenu(rs.getString("menu"));
					qaVO.setSubmenu(rs.getString("submenu"));
					qaVO.setSystem(rs.getString("system"));
					qaVO.setQuestion(rs.getString("question"));
					qaVO.setAnswer(rs.getString("answer"));
					qaVO.setClicks(rs.getInt("clicks"));
					qaVO.setPopularQuestion(rs.getBoolean("popularQuestion"));
					qaVO.setPopularQuestionSort(rs.getInt("popularQuestionSort"));
					list.add(qaVO);
				}
			} catch (ClassNotFoundException ce) {
				throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} finally {
				if(ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
			}	
		return list;
	}

	@Override
	public List<QaVO> getBySystem(String system) {
		List<QaVO> list = new ArrayList<QaVO>();
		QaVO qaVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				ps = con.prepareStatement(GET_QA_BySystem_STMT);
				ps.setString(1, system);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					qaVO = new QaVO();
					qaVO.setQuestionSN(rs.getInt("questionSN"));
					qaVO.setMenu(rs.getString("menu"));
					qaVO.setSubmenu(rs.getString("submenu"));
					qaVO.setSystem(rs.getString("system"));
					qaVO.setQuestion(rs.getString("question"));
					qaVO.setAnswer(rs.getString("answer"));
					qaVO.setClicks(rs.getInt("clicks"));
					qaVO.setPopularQuestion(rs.getBoolean("popularQuestion"));
					qaVO.setPopularQuestionSort(rs.getInt("popularQuestionSort"));
					list.add(qaVO);
				}
			} catch (ClassNotFoundException ce) {
				throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} finally {
				if(ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
			}	
		return list;
	}

	@Override
	public List<QaVO> getPopularQuestion() {
		//	private static final String GET_QA_ByPopular_STMT = "SELECT * FROM QA where popularQuestion=1 order by popularQuestionSort";
		List<QaVO> list = new ArrayList<QaVO>();
		QaVO qaVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				Class.forName(Util.DRIVER);
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				ps = con.prepareStatement(GET_QA_ByPopular_STMT);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					qaVO = new QaVO();
					qaVO.setQuestionSN(rs.getInt("questionSN"));
					qaVO.setMenu(rs.getString("menu"));
					qaVO.setSubmenu(rs.getString("submenu"));
					qaVO.setSystem(rs.getString("system"));
					qaVO.setQuestion(rs.getString("question"));
					qaVO.setAnswer(rs.getString("answer"));
					qaVO.setClicks(rs.getInt("clicks"));
					qaVO.setPopularQuestion(rs.getBoolean("popularQuestion"));
					qaVO.setPopularQuestionSort(rs.getInt("popularQuestionSort"));
					list.add(qaVO);
				}
			} catch (ClassNotFoundException ce) {
				throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} finally {
				if(ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
			}	
		return list;
	}
}

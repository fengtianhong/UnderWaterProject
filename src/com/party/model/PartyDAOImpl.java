package com.party.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class PartyDAOImpl implements PartyDAO_interface{
	
	public static void main(String[] args) {
		
			PartyVO t1 = new PartyVO();
			t1.setPartyHost(1);
			t1.setPartyTitle("好想去台灣0528");
			t1.setRegDate(java.sql.Date.valueOf("2010-01-02"));
			t1.setCloseDate(java.sql.Date.valueOf("2010-01-31"));
			t1.setStartDate(java.sql.Date.valueOf("2010-07-28"));
			t1.setEndDate(java.sql.Date.valueOf("2010-08-02"));
			t1.setPartyMinSize(6);
			t1.setPartyLocation(200002);
			t1.setPartyDetail("testInsertByDAOImpl");
			
			PartyDAOImpl dao = new PartyDAOImpl();
			
// test insert
//			dao.insert(t1);
//			System.out.println("insert data to DB!");
		
// test updateStatus
//			dao.updateStatus(400010, "3");
//			System.out.println("update data to DB!");
			
// test findByPartySN
//			PartyVO party1 = dao.findByPartySN(400009);
//			System.out.println("Host = " + party1.getPartyHost() + ", Title = " + party1.getPartyTitle());

			
// test findByPartyHost
//			List<PartyVO> L1 = dao.findByPartyHost(1);
//			for (PartyVO i : L1) {
//				System.out.println(i.getPartyTitle());
//			}
			
// test findByPartyLocation
//			List<PartyVO> L3 = dao.findByPartyLocation(200002);
//			for (PartyVO j : L3) {
//				System.out.println(j.getPartyHost() + ", " + j.getPartyTitle() + ", " + j.getPartyLocation());
//			}
			
// test getAll
//			List<PartyVO> L2 = dao.getAll();
//			for (PartyVO i : L2) {
//				System.out.println(i.getPartyDetail());
//			}
			
// test deleteByPartySN
//			dao.deleteByPartySN(400007);
			
		}

	private static final String INSERT_STMT = 
			"insert into party (partyHost, partyTitle, regDate, closeDate, startDate, endDate, partyMinSize," +
			" partyLocation, partyDetail) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATESTATUS_STMT = 
			"update Party set status = ? where partySN = ?";
	private static final String UPDATE_STMT = 
			"update Party set partyTitle = ?, regDate = ?, closeDate = ?, startDate = ?," +
			" endDate = ?, partyMinSize = ?, partyLocation = ?, partyDetail = ?, status = ? where partySN = ?";
	private static final String FINDBYPARTYSN_STMT = "select * from party where partySN = ? order by partySN desc";
	private static final String FINDBYPARTYHOST_STMT = "select * from party where partyHost = ? order by partySN desc";
	private static final String FINDBYPARTYLOCATION_STMT = "select * from party where partyLocation = ? order by partySN desc";
	private static final String GETALL_STMT = "select * from party order by partySN desc";
	private static final String DELETEBYPARTYSN_STMT = "delete from party where partySN = ?";
	private static final String FINDBYSEARCH_STMT = "select * from party where (partySN like ? or partyTitle like ?) and partyLocation like ? and partyMinSize >= ? order by partySN desc";
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
	@Override	
	public PartyVO insert(PartyVO partyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, partyVO.getPartyHost());
			pstmt.setString(2, partyVO.getPartyTitle());
			pstmt.setDate(3, partyVO.getRegDate());
			pstmt.setDate(4, partyVO.getCloseDate());
			pstmt.setDate(5, partyVO.getStartDate());
			pstmt.setDate(6, partyVO.getEndDate());
			pstmt.setInt(7, partyVO.getPartyMinSize());
			pstmt.setInt(8, partyVO.getPartyLocation());
			pstmt.setString(9, partyVO.getPartyDetail());
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return partyVO;
	}

	@Override
	public PartyVO update(PartyVO partyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, partyVO.getPartyTitle());
			pstmt.setDate(2, partyVO.getRegDate());
			pstmt.setDate(3, partyVO.getCloseDate());
			pstmt.setDate(4, partyVO.getStartDate());
			pstmt.setDate(5, partyVO.getEndDate());
			pstmt.setInt(6, partyVO.getPartyMinSize());
			pstmt.setInt(7, partyVO.getPartyLocation());
			pstmt.setString(8, partyVO.getPartyDetail());
			pstmt.setString(9, partyVO.getStatus());
			pstmt.setInt(10, partyVO.getPartySN());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return partyVO;
		
	}

	@Override
	public PartyVO updateStatus(Integer partySN, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, partySN);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		PartyJNDIDAO dao = new PartyJNDIDAO();
		return dao.findByPartySN(partySN);
	}


	@Override
	public PartyVO findByPartySN(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYPARTYSN_STMT);
			
			pstmt.setInt(1, partySN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return p1;
	}

	@Override
	public List<PartyVO> findByPartyHost(Integer partyHost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
		List<PartyVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYPARTYHOST_STMT);
			
			pstmt.setInt(1, partyHost);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
				list1.add(p1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

	@Override
	public List<PartyVO> findByPartyLocation(Integer partyLocation) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
		List<PartyVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYPARTYLOCATION_STMT);
			
			pstmt.setInt(1, partyLocation);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
				list1.add(p1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

	@Override
	public List<PartyVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
		List<PartyVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
				list1.add(p1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

	@Override
	public int deleteByPartySN(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETEBYPARTYSN_STMT);

			pstmt.setInt(1, partySN);
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		return i;
	}

	@Override
	public List<PartyVO> findBySearch(String keyword, Integer pointSN, Integer partyMinSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
		List<PartyVO> list1 = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYSEARCH_STMT);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + pointSN + "%");
			pstmt.setInt(4, partyMinSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
				list1.add(p1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	
	}
	
	@Override //0617 updated
	public List<PartyVO> findByPartySNLike(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
		List<PartyVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYSEARCH_STMT);
			
			pstmt.setString(1, "%" + partySN + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyVO();
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyHost(rs.getInt("partyHost"));
				p1.setPartyTitle(rs.getString("partyTitle"));
				p1.setRegDate(rs.getDate("regDate"));
				p1.setCloseDate(rs.getDate("closeDate"));
				p1.setStartDate(rs.getDate("startDate"));
				p1.setEndDate(rs.getDate("endDate"));
				p1.setPartyMinSize(rs.getInt("partyMinSize"));
				p1.setPartyLocation(rs.getInt("partyLocation"));
				p1.setPartyDetail(rs.getString("partyDetail"));
				p1.setCreateTime(rs.getTimestamp("createTime"));
				p1.setStatus(rs.getString("status"));
				list1.add(p1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

}

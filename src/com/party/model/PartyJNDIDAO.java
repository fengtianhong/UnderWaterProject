package com.party.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PartyJNDIDAO implements PartyDAO_interface {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"insert into party (partyHost, partyTitle, regDate, closeDate, startDate, endDate, partyMinSize," +
			" partyLocation, partyDetail) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATESTATUS_STMT = 
			"update Party set status = ? where partySN = ?";
	private static final String UPDATE_STMT = 
			"update Party set partyHost = ?, partyTitle = ?, regDate = ?, closeDate = ?, startDate = ?," +
			" endDate = ?, partyMinSize = ?, partyLocation = ?, partyDetail = ?, status = ? where partySN = ?";
	private static final String FINDBYPARTYSN_STMT = "select * from party where partySN = ?";
	private static final String FINDBYPARTYHOST_STMT = "select * from party where partyHost = ?";
	private static final String FINDBYPARTYLOCATION_STMT = "select * from party where partyLocation = ?";
	private static final String GETALL_STMT = "select * from party";
	private static final String DELETEBYPARTYSN_STMT = "delete from party where partySN = ?";
	

	@Override
	public int insert(PartyVO partyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = ds.getConnection();
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
			
			i = pstmt.executeUpdate();
			
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
		return i;
	}

	@Override
	public void update(PartyVO partyVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateStatus(Integer partySN, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, partySN);
			i = pstmt.executeUpdate();
			
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
		return i;
	}

	@Override
	public PartyVO findByPartySN(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyVO p1 = null;
	
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

	
	
	
}

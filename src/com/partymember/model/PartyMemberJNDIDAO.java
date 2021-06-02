package com.partymember.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

public class PartyMemberJNDIDAO implements PartyMemberDAO_interface {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into PartyMember (partySN, partyMember, gender, email," + 
			" phone, birthDate, personID, certification, certificationPic, comment) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATESTATUS_STMT = 
			"update PartyMember set status = ? where partyMemberSN = ?";
	private static final String FINDBYPARTYMEMBERSN_STMT = "select * from partyMember where partyMemberSN = ?";
	private static final String FINDBYPARTYMEMBER_STMT = "select * from partyMember where partyMember = ?";
	private static final String FINDBYPARTYSN_STMT = "select * from partyMember where partySN = ?";
	private static final String GETALL_STMT = "select * from partyMember";
	private static final String DELETEBYPARTYMEMBERSN_STMT = "delete from partyMember where partyMemberSN = ?";

	@Override
	public int insert(PartyMemberVO partyMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, partyMemberVO.getPartySN());
			pstmt.setInt(2, partyMemberVO.getPartyMember());
			pstmt.setString(3, partyMemberVO.getGender());
			pstmt.setString(4, partyMemberVO.getEmail());
			pstmt.setString(5, partyMemberVO.getPhone());
			pstmt.setDate(6, partyMemberVO.getBirthDate());
			pstmt.setString(7, partyMemberVO.getPersonID());
			pstmt.setString(8, partyMemberVO.getCertification());
			pstmt.setBytes(9, partyMemberVO.getCertificationPic());
			pstmt.setString(10, partyMemberVO.getComment());

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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return i;
	}

	@Override
	public void update(PartyMemberVO partyMemberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateStatus(Integer partyMemberSN, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, partyMemberSN);
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
	public PartyMemberVO findByPartyMemberSN(Integer partyMemberSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyMemberVO p1 = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYPARTYMEMBERSN_STMT);
			
			pstmt.setInt(1, partyMemberSN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyMemberVO();
				p1.setPartyMemberSN(rs.getInt("partyMemberSN"));
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyMember(rs.getInt("partyMember"));
				p1.setGender(rs.getString("gender"));
				p1.setEmail(rs.getString("email"));
				p1.setPhone(rs.getString("phone"));
				p1.setBirthDate(rs.getDate("birthDate"));
				p1.setPersonID(rs.getString("personID"));
				p1.setCertification(rs.getString("certification"));
				p1.setCertificationPic(rs.getBytes("certificationPic"));
				p1.setAppliedTime(rs.getTimestamp("appliedTime"));
				p1.setComment(rs.getString("comment"));
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
	public List<PartyMemberVO> findByPartyMember(Integer partyMember) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyMemberVO p1 = null;
		List<PartyMemberVO> list1 = new ArrayList<>();
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYPARTYMEMBER_STMT);
			
			pstmt.setInt(1, partyMember);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyMemberVO();
				p1.setPartyMemberSN(rs.getInt("partyMemberSN"));
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyMember(rs.getInt("partyMember"));
				p1.setGender(rs.getString("gender"));
				p1.setEmail(rs.getString("email"));
				p1.setPhone(rs.getString("phone"));
				p1.setBirthDate(rs.getDate("birthDate"));
				p1.setPersonID(rs.getString("personID"));
				p1.setCertification(rs.getString("certification"));
				p1.setCertificationPic(rs.getBytes("certificationPic"));
				p1.setAppliedTime(rs.getTimestamp("appliedTime"));
				p1.setComment(rs.getString("comment"));
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
	public List<PartyMemberVO> findByPartySN(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyMemberVO p1 = null;
		List<PartyMemberVO> list1 = new ArrayList<>();
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYPARTYSN_STMT);
			
			pstmt.setInt(1, partySN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyMemberVO();
				p1.setPartyMemberSN(rs.getInt("partyMemberSN"));
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyMember(rs.getInt("partyMember"));
				p1.setGender(rs.getString("gender"));
				p1.setEmail(rs.getString("email"));
				p1.setPhone(rs.getString("phone"));
				p1.setBirthDate(rs.getDate("birthDate"));
				p1.setPersonID(rs.getString("personID"));
				p1.setCertification(rs.getString("certification"));
				p1.setCertificationPic(rs.getBytes("certificationPic"));
				p1.setAppliedTime(rs.getTimestamp("appliedTime"));
				p1.setComment(rs.getString("comment"));
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
	public List<PartyMemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyMemberVO p1 = null;
		List<PartyMemberVO> list1 = new ArrayList<>();
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p1 = new PartyMemberVO();
				p1.setPartyMemberSN(rs.getInt("partyMemberSN"));
				p1.setPartySN(rs.getInt("partySN"));
				p1.setPartyMember(rs.getInt("partyMember"));
				p1.setGender(rs.getString("gender"));
				p1.setEmail(rs.getString("email"));
				p1.setPhone(rs.getString("phone"));
				p1.setBirthDate(rs.getDate("birthDate"));
				p1.setPersonID(rs.getString("personID"));
				p1.setCertification(rs.getString("certification"));
				p1.setCertificationPic(rs.getBytes("certificationPic"));
				p1.setAppliedTime(rs.getTimestamp("appliedTime"));
				p1.setComment(rs.getString("comment"));
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
	public int deleteByPartyMemberSN(Integer partyMemberSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEBYPARTYMEMBERSN_STMT);
			
			pstmt.setInt(1, partyMemberSN);
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

}

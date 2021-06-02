package com.partymember.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PartyMemberDAOImpl implements PartyMemberDAO_interface {
	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		byte[] b = null;
		
		try {
			fis = new FileInputStream("C:\\UnderWarter\\UnderWater\\src\\com\\partymember\\model\\3_6M.jpg");
			b = new byte[fis.available()];
			fis.read(b);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		}
		
		PartyMemberVO pm1 = new PartyMemberVO();
		pm1.setPartySN(400003);
		pm1.setPartyMember(1);
		pm1.setGender("1");
		pm1.setEmail("firsttest@firsttest.gmail.com");
		pm1.setPhone("0988111222");
		pm1.setBirthDate(java.sql.Date.valueOf("1997-07-07"));
		pm1.setPersonID("V777788888");
		pm1.setCertification("11");
		pm1.setCertificationPic(b);
		pm1.setComment("nothing to comment~");
		
		PartyMemberDAOImpl dao = new PartyMemberDAOImpl();
		
// test insert
//		dao.insert(pm1);
//		System.out.println("insert to DB!");
		
// test updateStatus
//		dao.updateStatus(400004, "1");
//		System.out.println("update status!");
		
// test findByPartyMemberSN
//		PartyMemberVO pm2 = dao.findByPartyMemberSN(400002);
//		System.out.println(pm2.getEmail());
//		System.out.println(pm2.getBirthDate());
		
// test findByPartyMember
//		List<PartyMemberVO> list_findByPartyMember = dao.findByPartyMember(2);
//		for (PartyMemberVO i : list_findByPartyMember) {
//			System.out.println(i.getAppliedTime());
//		}
		
// test findByPartySN
//		List<PartyMemberVO> list_findByPartySN = dao.findByPartySN(400003);
//		for (PartyMemberVO i : list_findByPartySN) {
//			System.out.println(i.getPartyMember());
//		}
		
// test getAll
//		List<PartyMemberVO> list_getAll = dao.getAll();
//		for (PartyMemberVO i : list_getAll) {
//			System.out.println(i.getPersonID());
//		}
		
// test deleteByPartyMemberSN
//		System.out.println(dao.deleteByPartyMemberSN(400005));
		
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
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public int insert(PartyMemberVO partyMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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

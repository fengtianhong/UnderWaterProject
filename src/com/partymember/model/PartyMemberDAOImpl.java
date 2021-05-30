package com.partymember.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.party.model.PartyVO;

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
		pm1.setPartyMember(3);	//應該會錯, 重複了
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
		PartyMemberVO pm2 = dao.findByPartyMemberSN(400004);
		System.out.println(pm2.getEmail());
		System.out.println(pm2.getBirthDate());
	
	}
	
	
	private static final String INSERT_STMT = "insert into PartyMember (partySN, partyMember, gender, email," + 
			" phone, birthDate, personID, certification, certificationPic, comment) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATESTATUS_STMT = 
			"update PartyMember set status = ? where partyMemberSN = ?";
	private static final String FINDBYPARTYMEMBERSN_STMT = "select * from partyMember where partyMemberSN = ?";
	
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
				p1.setGender(rs.getString("gender"));
				p1.setEmail(rs.getString("email"));
				p1.setPhone(rs.getString("phone"));
				p1.setBirthDate(rs.getDate("birthDate"));
				p1.setPersonID(rs.getString("personID"));
				p1.setCertification(rs.getString("certification"));
				p1.setCertificationPic(rs.getBytes("certificationPic"));
				p1.setAppliedDate(rs.getDate("appliedDate"));
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
	public List<PartyMemberVO> findByPartyMember(Integer partyMemebr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyMemberVO> findByPartySN(Integer partySN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyMemberVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByPartyMemberSN(Integer partyMemberSN) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

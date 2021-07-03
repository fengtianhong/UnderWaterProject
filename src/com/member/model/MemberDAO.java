package com.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class MemberDAO implements MemberDAO_interface{
	
	private static final String INSERT_STMT = "INSERT INTO Member (account, pwd,nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address, personphoto) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Member SET NickName=?, userName=?, gender=?, birthDate=?, phone=?, Certification=?, CertificationPic=?, personID=?, address=?, status=?, updateTime=?, ratePeople=?, ratePoint=?, personphoto=? WHERE userID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Member where userid=?";
	private static final String FINDBYACCOUNT_STMT = "SELECT * FROM Member where account=?";
	private static final String GET_ALL_STMT = "SELECT * FROM Member ORDER BY userID";
	private static final String LOGIN_STMT = "SELECT * FROM MEMBER WHERE ACCOUNT=? AND PWD=?";
	private static final String CHECK_ACCOUNT = "SELECT * FROM Member where account=?";
	private static final String PERSONINFOCHANGE = "UPDATE Member SET nickName=?, userName=?, gender=?, birthDate=?, phone=?, Certification=?, CertificationPic=?, personID=?, address=?, personphoto=? WHERE userID = ?";
	private static final String PWDUPDATE_STMT = "UPDATE Member SET pwd=? WHERE userID = ?";
	private static final String FINDBYSEARCH_STMT = "SELECT * FROM Member where account like ? or nickname like ? or username like ? order by account";
	public static void main(String[] args) {
//		測試insert
//		MemberVO vo = new MemberVO();
//		vo.setAccount("tibame2");
//		vo.setPwd("12345678");
//		vo.setNickName("阿宏");
//		vo.setUserName("馮天宏4帥哥");
//		vo.setGender("男");
//		vo.setBirthDate(Date.valueOf("2010-10-10"));
//		vo.setPhone("0912345678");
//		vo.setCertification(null);
//		vo.setCertificationPic(null);
//		vo.setPersonID("F123456788");
//		vo.setAddress("花蓮縣壽豐鄉中山路6X號");
//
//		
//		MemberDAO dao = new MemberDAO();
//		dao.insert(vo);
//		System.out.println("已加入成功");
//		//測試insert
		
//		//測試update ok
//		MemberVO vo = new MemberVO();
//		vo.setPwd("55677");
//		vo.setNickName("紀穎JJ");
//		vo.setUserName("黃紀穎");
//		vo.setGender("女");
//		vo.setBirthDate(Date.valueOf("2000-10-10"));
//		vo.setPhone("0912345678");
//		vo.setCertification("1");
//		vo.setCertificationPic(null);
//		vo.setPersonID("F220123456");
//		vo.setAddress("新北市");
//		vo.setStatus(1);
//		vo.setUpDateTime(new Timestamp(System.currentTimeMillis()));
//		vo.setRatePeople(10);
//		vo.setRatePoint(50);
//		vo.setUserID(1);
//		dao.update(vo);
//		System.out.println("更新成功");
//		//測試update ok
		
		//測試 findByPrimaryKey ok
//		MemberDAO dao = new MemberDAO();
//		MemberVO M1 = dao.findByPrimaryKey(2);
//		System.out.println(M1.getAccount());
		//測試 findByPrimaryKey ok
		
		//測試  findaccount ok
//		MemberDAO dao = new MemberDAO();
//		List<MemberVO> L1 = dao.findByAccount("Feng");
//		for(MemberVO xx : L1) {
//			System.out.println(xx.getUserID());
//		}
		//測試  findaccount ok
		
		//測試getall ok
//		MemberDAO dao = new MemberDAO();
//		List<MemberVO> la = dao.getAll();
//		for(MemberVO xx:la) {
//			System.out.println(xx.getAccount());
//		}
		//測試getall ok
		
		//測試login
//		MemberVO vo = new MemberVO();
//		MemberDAO dao = new MemberDAO();
//		vo.setAccount("12312");
//		vo.setPwd("qweqwe");
//		dao.login(vo);
		//測試login
	}	
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動成功");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
//	@Override
	public void insert(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, MemberVO.getAccount());
			pstmt.setString(2, MemberVO.getPwd());
			pstmt.setString(3, MemberVO.getNickName());
			pstmt.setString(4, MemberVO.getUserName());
			pstmt.setString(5, MemberVO.getGender());
			pstmt.setDate(6,MemberVO.getBirthDate());
			pstmt.setString(7, MemberVO.getPhone());
			pstmt.setString(8, MemberVO.getCertification());
			pstmt.setBytes(9, MemberVO.getCertificationPic());
			pstmt.setString(10, MemberVO.getPersonID());
			pstmt.setString(11, MemberVO.getAddress());
			pstmt.setBytes(12, MemberVO.getPersonPhoto());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	@Override
	public void update(MemberVO MemberVO) {//ok
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
		
			pstmt.setString(1, MemberVO.getNickName());
			pstmt.setString(2, MemberVO.getUserName());
			pstmt.setString(3, MemberVO.getGender());
			pstmt.setDate(4, MemberVO.getBirthDate());
			pstmt.setString(5, MemberVO.getPhone());
			pstmt.setString(6, MemberVO.getCertification());
			pstmt.setBytes(7, MemberVO.getCertificationPic());
			pstmt.setString(8, MemberVO.getPersonID());
			pstmt.setString(9, MemberVO.getAddress());
			pstmt.setInt(10, MemberVO.getStatus());
			pstmt.setTimestamp(11, MemberVO.getUpDateTime());
			pstmt.setInt(12, MemberVO.getRatePeople());
			pstmt.setInt(13, MemberVO.getRatePoint());
			pstmt.setBytes(14, MemberVO.getPersonPhoto());
			pstmt.setInt(15, MemberVO.getUserID());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
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
	
	
//	@Override
	public MemberVO findByPrimaryKey(Integer userID) {
		MemberVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setUserID(rs.getInt("userId"));
				vo.setAccount(rs.getString("account"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setUserName(rs.getString("userName"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthDate(rs.getDate("birthDate"));
				vo.setPhone(rs.getString("phone"));
				vo.setCertification(rs.getString("certification"));
				vo.setCertificationPic(rs.getBytes("certificationPic"));
				vo.setPersonID(rs.getString("personID"));
				vo.setAddress(rs.getString("address"));
				vo.setCreateTime(rs.getTimestamp("createTime"));
				vo.setStatus(rs.getInt("status"));
				vo.setUpDateTime(rs.getTimestamp("upDateTime"));
				vo.setRatePeople(rs.getInt("ratePeople"));
				vo.setRatePoint(rs.getInt("ratePoint"));
				vo.setPersonPhoto(rs.getBytes("personPhoto"));
			}
			
		} catch (Exception e) {
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
		return vo;
	}
	
	
//	@Override
	public MemberVO findByAccount(String account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYACCOUNT_STMT);
			
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setUserID(rs.getInt("userId"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setUserName(rs.getString("userName"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthDate(rs.getDate("birthDate"));
				vo.setPhone(rs.getString("phone"));
				vo.setCertification(rs.getString("Certification"));
				vo.setCertificationPic(rs.getBytes("CertificationPic"));
				vo.setPersonID(rs.getString("personID"));
				vo.setAddress(rs.getString("address"));
				vo.setCreateTime(rs.getTimestamp("CreateTime"));
				vo.setStatus(rs.getInt("Status"));
				vo.setUpDateTime(rs.getTimestamp("UpDateTime"));
				vo.setRatePeople(rs.getInt("RatePeople"));
				vo.setRatePoint(rs.getInt("RatePoint"));
				vo.setPersonPhoto(rs.getBytes("personPhoto"));
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
		return vo;
	}
	
//	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemberVO> list = new ArrayList<>();
		MemberVO vo = null;
		ResultSet rs = null; 
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setUserID(rs.getInt("userId"));
				vo.setAccount(rs.getString("account"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setUserName(rs.getString("userName"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthDate(rs.getDate("birthDate"));
				vo.setPhone(rs.getString("phone"));
				vo.setCertification(rs.getString("Certification"));
				vo.setCertificationPic(rs.getBytes("CertificationPic"));
				vo.setPersonID(rs.getString("personID"));
				vo.setAddress(rs.getString("address"));
				vo.setCreateTime(rs.getTimestamp("CreateTime"));
				vo.setStatus(rs.getInt("Status"));
				vo.setUpDateTime(rs.getTimestamp("UpDateTime"));
				vo.setRatePeople(rs.getInt("RatePeople"));
				vo.setRatePoint(rs.getInt("RatePoint"));
				vo.setPersonPhoto(rs.getBytes("personPhoto"));
				list.add(vo);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
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
public Boolean login(MemberVO MemberVO) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberVO vo =null;
	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(LOGIN_STMT);
		pstmt.setString(1, MemberVO.getAccount());
		pstmt.setString(2, MemberVO.getPwd());
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			vo = new MemberVO();
			vo.setAccount(rs.getString("account"));
//			vo.setPwd(rs.getString("pwd"));
//			System.out.println("登入成功");
			return true;
		}else {
//			System.out.println("使用者帳號或密碼錯誤");
			return false;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		if(pstmt != null) {
			try {
				pstmt.close();
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
//	return vo;
	return false;
}


@Override
public Boolean checkAccount(MemberVO MemberVO) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberVO vo = null;
	
	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareCall(CHECK_ACCOUNT);
		pstmt.setString(1, MemberVO.getAccount());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println("沒有重複");
			return true;
		}else {
			System.out.println("重複了");
			return false;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		if(pstmt != null) {
			try {
				pstmt.close();
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
	System.out.println("走到最後");
	return false;
}
	
public void personInfoUpdate(MemberVO MemberVO) {		
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(PERSONINFOCHANGE);
		
		pstmt.setString(1, MemberVO.getNickName());
		pstmt.setString(2, MemberVO.getUserName());
		pstmt.setString(3, MemberVO.getGender());
		pstmt.setDate(4, MemberVO.getBirthDate());
		pstmt.setString(5, MemberVO.getPhone());
		pstmt.setString(6, MemberVO.getCertification());
		pstmt.setBytes(7, MemberVO.getCertificationPic());
		pstmt.setString(8, MemberVO.getPersonID());
		pstmt.setString(9, MemberVO.getAddress());
		pstmt.setBytes(10, MemberVO.getPersonPhoto());
		pstmt.setInt(11, MemberVO.getUserID());
		
		pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
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
public void pwdUpdate(MemberVO MemberVO) {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(PWDUPDATE_STMT);
		pstmt.setString(1, MemberVO.getPwd());
		pstmt.setInt(2, MemberVO.getUserID());
		pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
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

public List<MemberVO> findBySearch(String account, String nickName, String userName){
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberVO vo = null;
	List<MemberVO> list = new ArrayList<MemberVO>();
	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(FINDBYSEARCH_STMT);
		
		pstmt.setString(1, "%" + account + "%");
		pstmt.setString(2, "%" + nickName + "%");
		pstmt.setString(3, "%" + userName + "%");
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			vo = new MemberVO();
			vo.setAccount(rs.getString("account"));
			vo.setNickName(rs.getString("nickName"));
			vo.setUserName(rs.getString("userName"));
			vo.setUserID(rs.getInt("userID"));
			list.add(vo);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
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
	return list;
}
	
	
	
	
}

	

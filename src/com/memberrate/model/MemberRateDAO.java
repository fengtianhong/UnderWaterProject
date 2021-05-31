package com.memberrate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class MemberRateDAO implements MemberRateDAO_interface {
	
	public static void main(String[] args) {
		
		MemberRateVO mr1 = new MemberRateVO();
		mr1.setPartySN(400002);
		mr1.setRateMaker(2);
		mr1.setRateRecipiant(1);
		mr1.setRate(2);
		mr1.setRateDetail("兩星好評");
		
		MemberRateDAO dao = new MemberRateDAO();
		
// test insertPartyRate
//		dao.insertPartyRate(mr1);
		
		MemberRateVO mr2 = new MemberRateVO();
		mr2.setOrderSN(6002);
		mr2.setRateMaker(2);
		mr2.setRateRecipiant(1);
		mr2.setRate(5);
		mr2.setRateDetail("五星好評");
		
// test insertOrderRate
//		dao.insertOrderRate(mr2);
		
// test findByMemberRateSN
//		MemberRateVO mr3 = dao.findByMemberRateSN(2);
//		System.out.println(mr3.getPartySN());
//		System.out.println(mr3.getOrderSN());
		
// test findByPartySN
//		List<MemberRateVO> list4 = dao.findByPartySN(400002);
//		for (MemberRateVO mr : list4) {
//			System.out.printf("in partySN %d, member %d has rated member%d \"%d\" scores and left comment: %s \n", mr.getPartySN(), mr.getRateMaker(), mr.getRateRecipiant(), mr.getRate(), mr.getRateDetail());
//		}
		
// test findByOrderSN
//		List<MemberRateVO> list5 = dao.findByOrderSN(6002);
//		for (MemberRateVO mr : list5) {
//			System.out.printf("in Group orderSN %d, member %d has rated member%d \"%d\" scores and left comment: %s \n", mr.getOrderSN(), mr.getRateMaker(), mr.getRateRecipiant(), mr.getRate(), mr.getRateDetail());
//		}
		
// test findByRateMaker
//		List<MemberRateVO> list6 = dao.findByRateMaker(1);
//		for (MemberRateVO mr : list6) {
//			System.out.println(mr.getRateDetail());
//		}
//		System.out.printf("has made %d comments", list6.size());
		
// test findByRateMaker
//		List<MemberRateVO> list7 = dao.findByRateRecipiant(1);
//		for (MemberRateVO mr : list7) {
//			System.out.println(mr.getSN());
//		}
//		System.out.printf("has been made %d comments", list7.size());
		
// test findByMember
//		List<MemberRateVO> list8 = dao.findByMember(2);
//		for (MemberRateVO mr : list8) {
//			System.out.println(mr.getSN());
//		}
		
// test getAll
//		List<MemberRateVO> list9 = dao.getAll();
//		for (MemberRateVO mr : list9) {
//			System.out.println(mr.getPartySN() == 0? mr.getOrderSN() : mr.getPartySN());
//		}
		
// test deleteBySN
//		System.out.println(dao.deleteBySN(8));
		
	}
	
	
	private static final String INSERTPARTYRATE_STMT = 
			"insert into MemberRate (partySN, rateMaker, rateRecipiant, rate, rateDetail) values (?, ?, ?, ?, ?)";
	private static final String INSERTORDERRATE_STMT = 
			"insert into MemberRate (orderSN, rateMaker, rateRecipiant, rate, rateDetail) values (?, ?, ?, ?, ?)";
	private static final String FINDBYMEMBERRATESN_STMT = "select * from MemberRate where SN = ?";
	private static final String FINDBYPARTYSN_STMT = "select * from MemberRate where partySN = ?";
	private static final String FINDBYORDERSN_STMT = "select * from MemberRate where orderSN = ?";
	private static final String FINDBYRATEMAKER_STMT = "select * from MemberRate where rateMaker = ?";
	private static final String FINDBYRATERECIPIANT_STMT = "select * from MemberRate where rateRecipiant = ?";
	private static final String FINDBYMEMBER_STMT = "select * from MemberRate where rateMaker = ? or rateRecipiant = ?";
	private static final String GETALL_STMT = "select * from MemberRate";
	private static final String DELETEBYSN_STMT = "delete from MemberRate where SN = ?";
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}


	@Override
	public int insertPartyRate(MemberRateVO memberRateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERTPARTYRATE_STMT);
			
			pstmt.setInt(1, memberRateVO.getPartySN());
			pstmt.setInt(2, memberRateVO.getRateMaker());
			pstmt.setInt(3, memberRateVO.getRateRecipiant());
			pstmt.setInt(4, memberRateVO.getRate());
			pstmt.setString(5, memberRateVO.getRateDetail());

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
	public int insertOrderRate(MemberRateVO memberRateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERTORDERRATE_STMT);
			
			pstmt.setInt(1, memberRateVO.getOrderSN());
			pstmt.setInt(2, memberRateVO.getRateMaker());
			pstmt.setInt(3, memberRateVO.getRateRecipiant());
			pstmt.setInt(4, memberRateVO.getRate());
			pstmt.setString(5, memberRateVO.getRateDetail());

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
	public void update(MemberRateVO memberRateVO) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void updateStatus() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public MemberRateVO findByMemberRateSN(Integer SN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYMEMBERRATESN_STMT);
			
			pstmt.setInt(1, SN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
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
		return mr1;
	}

	@Override
	public List<MemberRateVO> findByPartySN(Integer partySN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYPARTYSN_STMT);
			
			pstmt.setInt(1, partySN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public List<MemberRateVO> findByOrderSN(Integer orderSN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYORDERSN_STMT);
			
			pstmt.setInt(1, orderSN);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public List<MemberRateVO> findByRateMaker(Integer rateMaker) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYRATEMAKER_STMT);
			
			pstmt.setInt(1, rateMaker);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public List<MemberRateVO> findByRateRecipiant(Integer rateRecipiant) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYRATERECIPIANT_STMT);
			
			pstmt.setInt(1, rateRecipiant);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public List<MemberRateVO> findByMember(Integer member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FINDBYMEMBER_STMT);
			
			pstmt.setInt(1, member);
			pstmt.setInt(2, member);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public List<MemberRateVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberRateVO mr1 = null;
		List<MemberRateVO> list1 = new ArrayList<>();
	
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mr1 = new MemberRateVO();
				mr1.setSN(rs.getInt("SN"));
				mr1.setPartySN(rs.getInt("partySN"));
				mr1.setOrderSN(rs.getInt("orderSN"));
				mr1.setRateMaker(rs.getInt("rateMaker"));
				mr1.setRateRecipiant(rs.getInt("rateRecipiant"));
				mr1.setRate(rs.getInt("rate"));
				mr1.setRateDetail(rs.getString("rateDetail"));
				mr1.setCreateTime(rs.getTimestamp("createTime"));
				list1.add(mr1);
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
	public int deleteBySN(Integer SN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETEBYSN_STMT);

			pstmt.setInt(1, SN);
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

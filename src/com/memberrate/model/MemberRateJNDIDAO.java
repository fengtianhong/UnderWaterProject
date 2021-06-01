package com.memberrate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class MemberRateJNDIDAO implements MemberRateDAO_interface {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UnderWater");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
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
	
	
	@Override
	public int insertPartyRate(MemberRateVO memberRateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
	
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

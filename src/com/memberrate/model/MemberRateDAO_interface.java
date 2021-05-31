package com.memberrate.model;

import java.util.List;
import com.party.model.PartyVO;

public interface MemberRateDAO_interface {
	
	// Function update and updateStatus pending
	
	public int insertPartyRate(MemberRateVO memberRateVO);	//done
	
	public int insertOrderRate(MemberRateVO memberRateVO);  //done
	
	public void update(MemberRateVO memberRateVO);  //pending
	
//	public int updateStatus(Integer partySN, String status);  //pending
	
	public MemberRateVO findByMemberRateSN(Integer SN);  //done
	
	public List<MemberRateVO> findByPartySN(Integer partySN);  //done
	
	public List<MemberRateVO> findByOrderSN(Integer orderSN);  //done
	
	public List<MemberRateVO> findByRateMaker(Integer rateMaker);  //done
	
	public List<MemberRateVO> findByRateRecipiant(Integer rateRecipiant);  //done

	public List<MemberRateVO> findByMember(Integer member);  //done
	
	public List<MemberRateVO> getAll();  //done
	
	public int deleteBySN(Integer SN);  //done
	
}

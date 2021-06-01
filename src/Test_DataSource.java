/** 自行取得DataSource的 servlet
 
 1.需配合 web.xml 如下:
    <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/TestDB</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
    </resource-ref>
 2.需配合 server.xml
    -參考: http://localhost:8080/index.jsp 首頁
             之 Tomcat Documentation 之 JNDI DataSource HOW-TO 的說明
    -注意: 隨 servlet container 版本寫法會不同              
 */
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.*;
import com.diveinfo.model.*;
import com.memberrate.model.MemberRateDAO;
import com.memberrate.model.MemberRateJNDIDAO;
import com.memberrate.model.MemberRateVO;
import com.news.model.*;
import com.party.model.PartyDAOImpl;
import com.party.model.PartyJNDIDAO;
import com.party.model.PartyVO;
import com.partymember.model.PartyMemberDAOImpl;
import com.partymember.model.PartyMemberJNDIDAO;
import com.partymember.model.PartyMemberVO;

@WebServlet("/Test_DataSource")
public class Test_DataSource extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();

		try {

//==========================NewsDAO  Test=========================			
//			NewsDAO newsDAO = new NewsDAO();
//			NewsVO newsVO = new NewsVO();
//			newsVO.setTitle("烏鴉吃大便");
//			newsVO.setContent("烏鴉一直在吃屎");
//			newsVO.setImage(new byte[1]);
//			newsVO.setNewsDate(java.sql.Date.valueOf("2001-12-31"));
//			newsVO.setNewsFrom("胡說八道");
//			newsVO.setNewsType("1");
//			newsDAO.insert(newsVO);
//			out.println(newsDAO.findByPrimaryKey(500002).getTitle());
//			
//			newsVO.setTitle("聒聒");
//			newsVO.setContent("刮刮刮");
//			newsVO.setImage(new byte[1]);
//			newsVO.setNewsDate(java.sql.Date.valueOf("1992-12-31"));
//			newsVO.setNewsFrom("唬爛");
//			newsVO.setNewsType("2");
//			newsVO.setNewsSN(500002);
//			newsDAO.update(newsVO);
//			
//			
//			out.println(newsDAO.getAll());
//			out.println(newsDAO.findByPrimaryKey(500002).getTitle());
			
//==============================================================	
			
//======================DiveInfoDAO Test========================			

//			DiveInfoDAO diveinfoDAO = new DiveInfoDAO();
//			DiveInfoVO diveinfoVO = new DiveInfoVO();
//					
//			diveinfoVO.setPointName("墾丁");
//			diveinfoVO.setLatitude(21.945590);
//			diveinfoVO.setLongitude(120.798932);
//			diveinfoVO.setView("墾丁禾森旅店");
//			diveinfoVO.setIntroduction("幹你娘墾丁東西貴到靠北，去給人家騙還潛水?");
//			diveinfoVO.setSeason("春,秋");
//			diveinfoVO.setLocal("南部");
//			diveinfoVO.setPic(new byte[1]);
//			diveinfoVO.setRatePoint(1000);
//			diveinfoVO.setRatePeople(1000);
//			diveinfoVO.setStatus("0");
//			diveinfoDAO.insert(diveinfoVO);
//			out.println(diveinfoDAO.findByPrimaryKey(200001).getIntroduction());
//			
//			diveinfoVO.setPointName("墾丁");
//			diveinfoVO.setLatitude(21.945590);
//			diveinfoVO.setLongitude(120.798932);
//			diveinfoVO.setView("墾丁禾森旅店");
//			diveinfoVO.setIntroduction("幹你娘墾丁東西貴到靠北，去給人家騙還潛水?");
//			diveinfoVO.setSeason("春,秋");
//			diveinfoVO.setLocal("南部");
//			diveinfoVO.setPic(new byte[1]);
//			diveinfoVO.setRatePoint(1000);
//			diveinfoVO.setRatePeople(1000);
//			diveinfoVO.setStatus("0");
//			diveinfoVO.setPointSN(200001);
//			diveinfoDAO.update(diveinfoVO);
//				
//			out.println(diveinfoDAO.getAll());
//			out.println(diveinfoDAO.findByPrimaryKey(200001).getIntroduction());
			
//==============================================================			
	
			
//=======================ChatDAO Test===========================
//			ChatDAO chatDAO = new ChatDAO();
//			ChatVO chatVO = new ChatVO();
//			chatVO.setFromAccount(1);
//			chatVO.setToAccount(2);
//			chatVO.setContent("現在時間1040部隊起床");
//			chatVO.setDateTime(new Timestamp(System.currentTimeMillis()));
//			chatDAO.insert(chatVO);
//			out.println(chatDAO.findByAccount(1));
//			out.println(chatDAO.findByAtoB(1, 2));
//==============================================================
			

//========================Party Test============================
//			PartyVO t1 = new PartyVO();
//			t1.setPartyHost(3);
//			t1.setPartyTitle("好想出去玩~");
//			t1.setRegDate(java.sql.Date.valueOf("2021-06-03"));
//			t1.setCloseDate(java.sql.Date.valueOf("2011-06-21"));
//			t1.setStartDate(java.sql.Date.valueOf("2011-09-15"));
//			t1.setEndDate(java.sql.Date.valueOf("2011-09-18"));
//			t1.setPartyMinSize(1);
//			t1.setPartyLocation(200006);
//			t1.setPartyDetail("testInsertByDS");
			
//			PartyJNDIDAO pDao = new PartyJNDIDAO();
			
			//test insert
//			pDao.insert(t1);
//			out.println("Testing: New Party data insert successfully. createTime = "); //not done yet
			
			// test updateStatus & findByPartySN
//			pDao.updateStatus(400020, "2");
//			out.println("Testing: Party status updated to \"" + pDao.findByPartySN(400020).getStatus() + "\" successfully.");  //not done yet

			// test findByPartyHost
//			List<PartyVO> L1 = pDao.findByPartyHost(1);
//			out.println("Testing: member 1 has hosting \"" + L1.size() + "\" parties. Parties' title as below:");
//			for (PartyVO i : L1) {
//				out.println(i.getPartyTitle());
//			}
			
			// test findByPartyLocation
//			List<PartyVO> L3 = pDao.findByPartyLocation(200002);
//			out.println("Testing: find below information about Dive Location = 200002 :");
//			for (PartyVO j : L3) {
//				out.println(j.getPartyHost() + ", " + j.getPartyTitle() + ", " + j.getPartyLocation());
//			}
			
			// test getAll
//			List<PartyVO> L2 = pDao.getAll();
//			out.println("Testing: There are " + L2.size() + " parties as below:");
//			for (PartyVO i : L2) {
//				out.println("PartySN = " + i.getPartySN() + ", PartyDetail = " + i.getPartyDetail());
//			}
			
			// test deleteByPartySN
//			pDao.deleteByPartySN(400018);
//			out.println("Testing: PartySN 400018 has been deleted.");
			
//==============================================================
			
			
//======================PartyMember TEST========================
			
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
			pm1.setPartySN(400004);
			pm1.setPartyMember(1);
			pm1.setGender("1");
			pm1.setEmail("tibame@bitema.com");
			pm1.setPhone("0988555555");
			pm1.setBirthDate(java.sql.Date.valueOf("2019-11-11"));
			pm1.setPersonID("F555566666");
			pm1.setCertification("21");
			pm1.setCertificationPic(b);
			pm1.setComment("nothing to comment~");
			
			PartyMemberJNDIDAO pmDao = new PartyMemberJNDIDAO();
			
			// test insert
//			pmDao.insert(pm1);
//			out.println("Testing: New PartyMember data insert successfully. createTime = "); //not done yet
			
			// test updateStatus & findByPartyMemberSN
//			pmDao.updateStatus(400001, "1");
//			out.println("Testing: Party status updated to \"" + pmDao.findByPartyMemberSN(400001).getStatus() + "\" successfully.");  //not done yet
			
			// test findByPartyMember
//			List<PartyMemberVO> list_findByPartyMember = pmDao.findByPartyMember(2);
//			out.println("Testing: member 2 has participated \"" + list_findByPartyMember.size() + "\" parties. PartySN as below:");
//			for (PartyMemberVO i : list_findByPartyMember) {
//				out.println(i.getPartySN());
//			}
			
			// test findByPartySN
//			List<PartyMemberVO> list_findByPartySN = pmDao.findByPartySN(400003);
//			out.println("Testing: Below members have participated PartySN 400003");
//			for (PartyMemberVO i : list_findByPartySN) {
//				out.println(i.getPartyMember());
//			}
			
			// test getAll
//			List<PartyMemberVO> list_getAll = pmDao.getAll();
//			out.println("Testing: getAll()");
//			for (PartyMemberVO i : list_getAll) {
//				out.println(i.getPersonID());
//			}
			
			// test deleteByPartyMemberSN
//			pmDao.deleteByPartyMemberSN(400009);
//			out.println("Testing: PartyMemberSN 400009 has been deleted.");
			
			
//==============================================================
			
			
//====================MemberRate Test===========================
			
//			MemberRateVO mr1 = new MemberRateVO();
//			mr1.setPartySN(400003);
//			mr1.setRateMaker(1);
//			mr1.setRateRecipiant(2);
//			mr1.setRate(5);
//			mr1.setRateDetail("參加Party五星好評");
			
			MemberRateJNDIDAO mrDao = new MemberRateJNDIDAO();
			
			// test insertPartyRate
//			mrDao.insertPartyRate(mr1);
//			out.println("Testing: New MemberRate(Party) data insert successfully. createTime = "); //not done yet
			
//			MemberRateVO mr2 = new MemberRateVO();
//			mr2.setOrderSN(6002);
//			mr2.setRateMaker(2);
//			mr2.setRateRecipiant(1);
//			mr2.setRate(5);
//			mr2.setRateDetail("參加GroupTour五星好評");
			
			// test insertOrderRate
//			mrDao.insertOrderRate(mr2);
//			out.println("Testing: New MemberRate(Group Tour) data insert successfully. createTime = "); //not done yet
			
			// test findByMemberRateSN
//			MemberRateVO mr3 = mrDao.findByMemberRateSN(4);
//			out.println("Testing: PartySN / GroupTourOrderSN = " + ( mr3.getPartySN() == 0? mr3.getOrderSN() : mr3.getPartySN()) );
			
			// test findByPartySN
//			List<MemberRateVO> list4 = mrDao.findByPartySN(400002);
//			out.println("Testing: findByPartySN 400002");
//			for (MemberRateVO mr : list4) {
//				out.printf("member %d has rated member%d \"%d\" scores and left comment: %s \n", mr.getRateMaker(), mr.getRateRecipiant(), mr.getRate(), mr.getRateDetail());
//			}
			
			// test findByOrderSN
//			List<MemberRateVO> list5 = mrDao.findByOrderSN(6002);
//			out.println("Testing: findByOrderSN 6002");
//			for (MemberRateVO mr : list5) {
//				out.printf("member %d has rated member%d \"%d\" scores and left comment: %s \n", mr.getRateMaker(), mr.getRateRecipiant(), mr.getRate(), mr.getRateDetail());
//			}
			
			// test findByRateMaker
//			List<MemberRateVO> list6 = mrDao.findByRateMaker(1);
//			out.printf("Testing: member 1 has made %d comments: \n", list6.size());
//			for (MemberRateVO mr : list6) {
//				out.println("PartySN/GroupTourSN = " + ( mr.getPartySN() == 0? mr.getOrderSN() : mr.getPartySN()) + ", RateDetail= " + mr.getRateDetail());
//			}
			
			// test findByRateMaker
//			List<MemberRateVO> list7 = mrDao.findByRateRecipiant(1);
//			out.printf("Testing: member 2 has recieved %d comments: \n", list7.size());
//			for (MemberRateVO mr : list7) {
//				out.println("PartySN/GroupTourSN = " + ( mr.getPartySN() == 0? mr.getOrderSN() : mr.getPartySN()) + ", RateDetail= " + mr.getRateDetail());
//			}
			
			//	 test findByMember
//			List<MemberRateVO> list8 = mrDao.findByMember(2);
//			out.printf("Testing: There are %d comments regarding member 2: \n", list8.size());
//			for (MemberRateVO mr : list8) {
//				out.println("PartySN/GroupTourSN = " + (mr.getPartySN() == 0? mr.getOrderSN() : mr.getPartySN()) + 
//						(mr.getRateMaker() == 2? ", RateRecipiant= " + mr.getRateRecipiant() : ", RateMaker= " + mr.getRateMaker()) );
//			}
			
			// test getAll
//			List<MemberRateVO> list9 = mrDao.getAll();
//			out.printf("Testing: There are total %d comments in DB \n", list9.size());
//			for (MemberRateVO mr : list9) {
//				out.println("PartySN/GroupOrderSN = " + (mr.getPartySN() == 0? mr.getOrderSN() : mr.getPartySN()) + ", MemberRateSN = " + mr.getSN());
//			}
			
			// test deleteBySN
//			mrDao.deleteBySN(9);
//			out.println("Testing: SN 9 has been deleted.");
			
//==============================================================
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
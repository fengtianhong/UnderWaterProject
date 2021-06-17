import java.io.*;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.collections.model.CollectionsDAO;
import com.collections.model.CollectionsVO;
import com.grouptour.model.*;
import com.locationrate.model.LocationRateDAO;
import com.locationrate.model.LocationRateVO;
import com.news.model.*;
import com.orderforgroup.model.OderForGroupDAO;
import com.orderforgroup.model.OderForGroupVO;
import com.qa.model.QaDAO;
import com.qa.model.QaVO;

@WebServlet("/Test_DataSource_Katy")
public class Test_DataSource_Katy extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();

		try {
//=============================== GroupTour equals ===============================
			GroupTourService svc = new GroupTourService();

			GroupTourVO vo1 = svc.getOne(6001);
			
			GroupTourVO vo2 = svc.getOne(6001);
			System.out.println(vo1);
			System.out.println(vo2);
			System.out.println(vo1 == vo2);
			System.out.println(vo1.equals(vo2));
		
//=============================== GroupTourDAO ===============================
//			GroupTourDAO dao = new GroupTourDAO();
//			GroupTourVO vo = new GroupTourVO();
////			vo.setGroupTourSN(6001);	// auto_increment start 6001
//			vo.setTourName("墾丁後壁湖出水口找海龜");
//			vo.setStartTime(java.sql.Date.valueOf("2021-09-01"));
//			vo.setEndTime(java.sql.Date.valueOf("2021-09-03"));
//			vo.setRegTime(java.sql.Date.valueOf("2021-08-03"));
//			vo.setCloseTime(java.sql.Date.valueOf("2001-08-28"));	// stop 3 days ago
////			vo.setCreateTime(rs.getTimestamp("createTime"));	// default
//			vo.setPointSN(200002);
//			vo.setPrice(8000);
//			vo.setAttendNumber(0);	//default 0? or insert 0?
//			vo.setLimitNumder(6);
//			vo.setCertificationLimit("0");
//			vo.setStatus("0");
//			vo.setContent("2233");
//			System.out.println(vo.toString());
//			dao.insert(vo);			// OK
////			dao.update(vo);			// OK
////			GroupTourVO testVO = dao.findByPrimaryKey(6001);
////			out.println(testVO.getTourName());  	// OK
//			out.println(dao.getAll()); 	// OK

			
//=============================== CollectionsDAO ===============================
//			insert, delete, findByUserID 已確認可以在 DB 執行 (會卡FK)	
// 兩個問題 	1. 兩欄位是否綁複合 UK  2. 由 controller 判斷要 insert 還是 delete ?
			
//			CollectionsDAO dao = new CollectionsDAO();
//			CollectionsVO collectionsVO = new CollectionsVO();
//			collectionsVO.setGroupTourSN(6001); 	// 1
//			collectionsVO.setUserID(1);			// 1 2 3
//			dao.insert(collectionsVO);			// OK
////			dao.delete(collectionsVO); 			// OK
//			out.println(dao.findByUserID(1));   // OK

//=============================== LocationRateDAO ===============================
//			LocationRateDAO dao = new LocationRateDAO();
//			LocationRateVO vo = new LocationRateVO();
//			vo = new LocationRateVO();
//			vo.setSN(6002);
//			vo.setPointSN(200002);
//			vo.setUserID(3);
//			vo.setRate(1);
//			vo.setRateDetail("不好玩");
////			vo.setCreateTime();
//			out.println(vo.toString());
//			dao.insert(vo);			// OK
//			dao.update(vo);			// OK
////			dao.delete(6003); 				// OK
//			out.println(dao.getByPointSN(200002));  	// OK
//			out.println(dao.getByUser(1)); 		// OK
			
//=============================== OderForGroupDAO ===============================
// 訂單結束時間的話吃 GroupTour 的行程結束時間 > 但要寫在DAO?
//			OderForGroupDAO dao = new OderForGroupDAO();
//			OderForGroupVO vo = new OderForGroupVO();
//			vo.setOrderSN(6005); // use default
//			vo.setUserID(2);	
//			vo.setGroupTourSN(6001);
//			vo.setTotalPrice(10000);
//			vo.setPurchaseDate(java.sql.Date.valueOf("2021-04-28"));
//			vo.setPhone("0918210612");
//			vo.setPersonID("A9999999");
//			vo.setBirthdate(java.sql.Date.valueOf("1996-07-19"));
//			out.println(vo.toString());
////			dao.insert(vo);			// OK
////			dao.update(vo);			// OK
//			out.println(dao.findByPrimaryKey(6003));  	// OK
//			out.println(dao.getOrderByUserID(2)); 		// OK
			
//=============================== QaDAO ===============================
//			QaDAO dao = new QaDAO();
//			QaVO qaVO = new QaVO();
//			qaVO.setQuestionSN(6001); // use default
//			qaVO.setMenu("1");	
//			qaVO.setSubmenu("1");
//			qaVO.setSystem("1");
//			qaVO.setQuestion("晚餐吃甚麼? 午餐呢?");
//			qaVO.setAnswer("泡菜海鮮煎餅");
//			qaVO.setClicks(0);
//			qaVO.setPopularQuestion(true);
//			qaVO.setPopularQuestionSort(null);	// insert use default
//			out.println(qaVO.toString());
//			dao.insert(qaVO);			// OK
//			dao.update(qaVO);			// OK
////			dao.delete(6002); 				// OK
//			out.println(dao.getByMenu("1", "1"));  	// OK
//			out.println(dao.getBySystem("1")); 		// OK
//			out.println(dao.getPopularQuestion()); 	// OK
          
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
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

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.*;
import com.diveinfo.model.*;
import com.news.model.*;

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
			ChatDAO chatDAO = new ChatDAO();
//			ChatVO chatVO = new ChatVO();
//			chatVO.setFromAccount(1);
//			chatVO.setToAccount(2);
//			chatVO.setContent("現在時間1040部隊起床");
//			chatVO.setDateTime(new Timestamp(System.currentTimeMillis()));
//			chatDAO.insert(chatVO);
			out.println(chatDAO.findByAccount(1));
//			out.println(chatDAO.findByAtoB(1, 2));
//==============================================================
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
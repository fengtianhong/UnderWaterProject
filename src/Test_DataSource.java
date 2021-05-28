/** �ۦ���oDataSource�� servlet
 
 1.�ݰt�X web.xml �p�U:
    <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/TestDB</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
    </resource-ref>
 2.�ݰt�X server.xml
    -�Ѧ�: http://localhost:8080/index.jsp ����
             �� Tomcat Documentation �� JNDI DataSource HOW-TO ������
    -�`�N: �H servlet container �����g�k�|���P              
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.news.model.*;

@WebServlet("/Test_DataSource")
public class Test_DataSource extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=Big5");
		PrintWriter out = res.getWriter();

		try {
			
			NewsDAO newsDAO = new NewsDAO();
			NewsVO newsVO = new NewsVO();
			newsVO.setTitle("烏鴉吃大便");
			newsVO.setContent("烏鴉一直在吃屎");
			newsVO.setImage(new byte[1]);
			newsVO.setNewsDate(java.sql.Date.valueOf("2001-12-31"));
			newsVO.setNewsFrom("胡說八道");
			newsVO.setNewsType("1");
			newsDAO.insert(newsVO);
			out.println(newsDAO.findByPrimaryKey(500002).getTitle());
			
			newsVO.setTitle("聒聒");
			newsVO.setContent("刮刮刮");
			newsVO.setImage(new byte[1]);
			newsVO.setNewsDate(java.sql.Date.valueOf("1992-12-31"));
			newsVO.setNewsFrom("唬爛");
			newsVO.setNewsType("2");
			newsVO.setNewsSN(500002);
			newsDAO.update(newsVO);
			
			
			out.println(newsDAO.getAll());
			out.println(newsDAO.findByPrimaryKey(500002).getTitle());
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
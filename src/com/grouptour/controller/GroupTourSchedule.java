package com.grouptour.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.grouptour.model.GroupTourService;
import com.grouptour.model.GroupTourVO;

@WebServlet(
        urlPatterns = "/grouptour/GroupTourSchedule.do",
        loadOnStartup = 1
)
public class GroupTourSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;
    
	public void destroy() {
		timer.cancel();;
	}
    public void init() {
    	timer = new Timer();
    	TimerTask task = new TimerTask() {
    		
    		@Override
    		public void run() {
    			GroupTourService svc = new GroupTourService();
    			List<GroupTourVO> list = svc.getAll();
    			
    			
    			for(GroupTourVO groupTourVO : list) {
    				Integer groupTourSN = groupTourVO.getGroupTourSN();
    				Date regTime = groupTourVO.getRegTime();
    				Date closeTime = groupTourVO.getCloseTime();
    				Date now = new Date(System.currentTimeMillis());
    				String status = groupTourVO.getStatus();
    				
    				
    				if("1".equals(status) && regTime.before(now) && closeTime.after(now)) {
    					// 狀態下架改上架
    					
//    					System.out.println("狀態下架改上架 groupTourSN" + groupTourSN);
//    					System.out.println("報名始" + regTime);
//    					System.out.println("報名終" + closeTime);
//    					System.out.println("============================");
    					svc.updateStatus(groupTourSN, "0");
    					
    					
    				}else if("0".equals(status) && (regTime.after(now) || closeTime.before(now)) ) {
    					// 狀態上架改下架
    					
//    					System.out.println("狀態上架改下架 groupTourSN" + groupTourSN);
//    					System.out.println("報名始" + regTime);
//    					System.out.println("報名終" + closeTime);
//    					System.out.println("============================");
    					svc.updateStatus(groupTourSN, "1");
    				}
    			}
    		}
    	};
    	Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 1, 0, 0);
    	
    	timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
    }

}

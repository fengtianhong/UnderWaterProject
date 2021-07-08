package com.party.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.party.model.PartyVO;
import com.party.model.PartyService;

@WebServlet(value="/PartySchedule", loadOnStartup=1)
public class PartySchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;
    
    public void destroy() {
    	timer.cancel();
    }
    
    public void init() {
    	timer = new Timer();
    	TimerTask task = new TimerTask() {
    	int count = 0;
    	
    		public void run() {
    			PartyService partySvc = new PartyService();
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			
    			long executionTime = scheduledExecutionTime();	// 23:59
//    			long now = System.currentTimeMillis();
    			System.out.println("排定執行時間= " + sdf.format(executionTime));
    			
    			try {
					Thread.sleep(60*1000);  // 60秒 => 隔日 00:00
					List<PartyVO> listAll = partySvc.getAll();
	    			for (PartyVO partyVO : listAll) {
	    				long closeDate = partyVO.getCloseDate().getTime();
//	    				System.out.println("closeDate= " + closeDate);
	    				if (closeDate < executionTime && !"2".equals(partyVO.getStatus())) {
	    					if ("0".equals(partyVO.getStatus()) || "4".equals(partyVO.getStatus())) {
	    						System.out.println("partySN= " + partyVO.getPartySN() + ", closeDate= " + sdf.format(closeDate));
	    						partyVO.setStatus("2");	//活動已結束(活動開始日期 < 當下日期) 除了已取消和已下架
	    						partySvc.update(partyVO);
	    					}
	    				}
	    			}
	    			System.out.println("PartySchedule.java排程從7/7開始算執行第" + (++count) + "次");
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
				}
    		}
    	};
    	Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 7, 23, 59, 00);
    	timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
    }
}

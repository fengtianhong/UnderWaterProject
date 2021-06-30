package com.party.controller;

import java.io.IOException;
import java.util.Calendar;
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
	Timer timer = new Timer();
       
    public PartySchedule() {
        super();
    }
    
    public void destroy() {
    	timer.cancel();
    }
    
    public void init() {
    	PartyService partySvc = new PartyService();
//測試
//    	System.out.println("now= " + System.currentTimeMillis());
//    	PartyVO t1 = new PartyVO();
//    	t1.setPartyHost(1);
//		t1.setPartyTitle("深海init測起來");
//		t1.setRegDate(java.sql.Date.valueOf("2021-06-29"));
//		t1.setCloseDate(java.sql.Date.valueOf("2021-06-30"));
//		t1.setStartDate(java.sql.Date.valueOf("2021-07-28"));
//		t1.setEndDate(java.sql.Date.valueOf("2021-08-02"));
//		t1.setPartyMinSize(6);
//		t1.setPartyLocation(200002);
//		t1.setPartyDetail("testInsertByDAOImpl");
//		System.out.println("closeDate= " + t1.getCloseDate().getTime());
    	
    	TimerTask task = new TimerTask() {
    		int count = 0;
    		public void run() {
    			long now = System.currentTimeMillis();
//    			System.out.println(now);
    			
    			List<PartyVO> listAll = partySvc.getAll();
    			for (PartyVO partyVO : listAll) {
    				long closeDate = partyVO.getCloseDate().getTime();
//    				System.out.println("closeDate= " + closeDate);
    				if (closeDate < now) {
    					partyVO.setStatus("2");	//活動已結束(活動開始日期 < 當下日期)
    					partySvc.update(partyVO);
    				}
    			}
    			System.out.println("PartySchedule.java排程從6/30開始算執行第" + (++count) + "次");
    		}
    	};
    	Calendar cal = new GregorianCalendar(2021, Calendar.JUNE, 30, 0, 0, 0);
    	timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000); //check 0630 & 0631凌晨
    }
}

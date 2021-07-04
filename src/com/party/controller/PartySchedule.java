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
    
    public void destroy() {
    	timer.cancel();
    }
    
    public void init() {
    	PartyService partySvc = new PartyService();

    	TimerTask task = new TimerTask() {
    		int count = 0;
    		public void run() {
    			long now = System.currentTimeMillis();
//    			System.out.println(now);
    			try {
					Thread.sleep(1000);
					List<PartyVO> listAll = partySvc.getAll();
	    			for (PartyVO partyVO : listAll) {
	    				long closeDate = partyVO.getCloseDate().getTime();
//	    				System.out.println("closeDate= " + closeDate);
	    				if (closeDate < now) {
	    					partyVO.setStatus("2");	//活動已結束(活動開始日期 < 當下日期)
	    					partySvc.update(partyVO);
	    				}
	    			}
	    			System.out.println("PartySchedule.java排程從6/30開始算執行第" + (++count) + "次");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			
    		}
    	};
    	Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 1, 23, 59, 59);
    	timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000); //check 0702 凌晨 (0701凌晨時截止日期0701的都被改了)
    }
}

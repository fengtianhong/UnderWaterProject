package com.party.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	TimerTask task = new TimerTask() {
    		int count = 0;
    		public void run() {
    			long now = System.currentTimeMillis();	// 當天23:59
    			System.out.println("now = " + sdf.format(new java.sql.Timestamp(now)));
    			try {
					Thread.sleep(60*1000);
					List<PartyVO> listAll = partySvc.getAll();
	    			for (PartyVO partyVO : listAll) {
	    				long closeDate = partyVO.getCloseDate().getTime();
	    				int partySN = partyVO.getPartySN();
	    				if (closeDate < now) {
	    					System.out.println("the ones = " + partySN + "/ closeDate = " + sdf.format(closeDate));
	    					partyVO.setStatus("2");	//活動已結束(報名截止日期 < 當下日期)
	    					partySvc.update(partyVO);
	    				}
	    			}
	    			System.out.println("PartySchedule.java排程從7/4 23:59 開始算執行第" + (++count) + "次");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	};
    	Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 4, 23, 59, 00);
    	timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	long now = System.currentTimeMillis();
//		System.out.println("now = " + sdf.format(new java.sql.Timestamp(now)));
    }
}

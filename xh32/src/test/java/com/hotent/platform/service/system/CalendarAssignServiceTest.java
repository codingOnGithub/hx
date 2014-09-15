package com.hotent.platform.service.system;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.service.worktime.CalendarAssignService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class CalendarAssignServiceTest
{
	@Resource
	private CalendarAssignService calendarAssignService;
	
	/**
	 * 根据工作日计算真正的到期时间。
	 */
	@Test
	public void getTaskTimeByUser(){
		Date date= calendarAssignService.getTaskTimeByUser(new Date(), 16*60, 1);
		System.out.println(TimeUtil.getDateTimeString(date));
	}
	
	/**
	 * 根据开始时间，结束时间计算实际的工作时间
	 */
	@Test
	public void getTaskTimeTest(){
		Date startDate=TimeUtil.getDateTimeByTimeString("2012-06-11 11:32:00");
		Date endDate=TimeUtil.getDateTimeByTimeString("2012-06-13 13:42:00");
		String time=calendarAssignService.getTaskTime(startDate, endDate, 1338948824574L);
		System.out.println("任务执行时间："+time);
	}
	@Test
	public void getLeaveTimeTest(){
		Date startDate=TimeUtil.getDateTimeByTimeString("2012-06-13 11:32:00");
		Date endDate=TimeUtil.getDateTimeByTimeString("2012-06-13 13:42:00");
		long  longtime=calendarAssignService.getLeaveTime(1338948824574L, startDate, endDate);
		String time=TimeUtil.getTime(longtime);
		System.out.println("请假时间："+time);
	}
	
	@Test
	public void getTaskTimeEndTest(){
		Date startDate=TimeUtil.getDateTimeByTimeString("2012-06-13 11:32:00");
		Date endDate=calendarAssignService.getTaskTimeByUser(startDate,70,1338948824574L);
		System.out.println(TimeUtil.getDateTimeString(endDate));
	}
}

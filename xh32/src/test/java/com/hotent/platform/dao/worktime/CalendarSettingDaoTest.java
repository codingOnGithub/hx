package com.hotent.platform.dao.worktime;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.model.worktime.WorkTime;
import com.hotent.platform.service.worktime.CalendarSettingService;

public class CalendarSettingDaoTest extends BaseTestCase {
	
	@Resource
	private CalendarSettingDao calendarSettingDao;
	
	@Resource
	private CalendarSettingService calendarSettingService;
	
	//@org.junit.Test
	public void getByCalendarId(){
		Date time=TimeUtil.getDateTimeByTimeString("2012-06-12 21:34:00");
		List<CalendarSetting> list=calendarSettingDao.getByCalendarId(1L,time);
		for(CalendarSetting obj:list){
			String calDay=obj.getCalDay();
			System.out.println("calDay:" + calDay);
//			int months=obj.getMonths();
//			int days=obj.getDays();
			List<WorkTime> workList=obj.getWorkTimeList();
			for(WorkTime workTime:workList){
				System.out.println(workTime.getStartTime() +"," + workTime.getEndTime());
			}	
		}
		System.out.println("getByCalendarId");
	}
	
	//@org.junit.Test
	public void getSvcByCalendarId(){
		Date time=TimeUtil.getDateTimeByTimeString("2012-06-12 21:34:00");
		List<WorkTime> list=calendarSettingService.getByCalendarId(1L,time);
		for(WorkTime workTime:list){
			String start=TimeUtil.getDateTimeString(workTime.getStartDateTime());
			String end=TimeUtil.getDateTimeString(workTime.getEndDateTime());
			System.out.println(start +"," + end);
		}
		
		System.out.println(list.size());
	}
	
	

}

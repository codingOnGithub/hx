package com.hotent.base.core.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.hotent.base.core.util.time.DateFormatUtil;
import com.hotent.base.core.util.time.DateUtil;

/**
 * 日期格式测试类
 * 
 * @author zxh
 * 
 */
public class DateUtilTest {
	Log log = LogFactory.getLog(DateUtilTest.class);

	private static final long MILLIS_TEST;
	static {
		GregorianCalendar cal = new GregorianCalendar(2013, 6, 5, 4, 3, 2);
		cal.set(Calendar.MILLISECOND, 1);
		MILLIS_TEST = cal.getTime().getTime();
	}

	private static final String DATE_STRING_1 = "2014-01-13 10:23:22.231";
	private static final String DATE_STRING_2 = "2014-01-13 10:23:22";
	private static final String DATE_STRING_3 = "2014-01-13 10:23";
	private static final String DATE_STRING_4 = "2014-01-13 10";
	private static final String DATE_STRING_5 = "2014-01-13";
	// private static final String DATE_STRING_6 = "2014-01";
	// private static final String DATE_STRING_7 = "2014";
	private static final String DATE_STRING_8 = "10:23:22";
	private static final String DATE_STRING_9 = "10:23";

	/**
	 * 测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		log.info("========测试1parse自动识别格式==========");
		// 测
		log.info(DateFormatUtil.parse(DATE_STRING_1));
		log.info(DateFormatUtil.parse(DATE_STRING_2));
		log.info(DateFormatUtil.parse(DATE_STRING_3));
		log.info(DateFormatUtil.parse(DATE_STRING_4));
		log.info(DateFormatUtil.parse(DATE_STRING_5));
		// log.info(DateUtil.parse(DATE_STRING_6));//错误
		// log.info(DateUtil.parse(DATE_STRING_7));//错误
		log.info(DateFormatUtil.parse(DATE_STRING_8));
		log.info(DateFormatUtil.parse(DATE_STRING_9));
		log.info("========测试2parse自定义格式==========");
		// 测试2parse()
		log.info(DateFormatUtil.parse(DATE_STRING_1, "yyyy-MM-dd HH:mm:ss.SSS"));
		// log.debug(DateUtil.parse(DATE_STRING_5,
		// "yyyy-MM-dd HH:mm:ss.SSS"));//错误

		log.info("========测试parse指定格式的数组==========");
		String a[] = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
		log.info(DateFormatUtil.parse(DATE_STRING_2, a));
		log.info(DateFormatUtil.parse(DATE_STRING_5, a));

		log.info("========测试parseDate指定格式（yyyy-MM-dd）==========");
		log.info(DateFormatUtil.parseDate(DATE_STRING_5));

		log.info("========测试format==========");
		log.info(DateFormatUtil.format(new Date()));
		log.info(DateFormatUtil.format(DateFormatUtil.parse(DATE_STRING_1)));
		log.info(DateFormatUtil.format(DateFormatUtil.parse(DATE_STRING_8)));
		log.info("========测试format==========");

		log.info(DateFormatUtil.format(DateFormatUtil.parse(DATE_STRING_1),
				"yyyy-MM-dd"));

		Date date = new Date();
		log.info("当前时间：" + date);
		DateUtils.addDays(date, 1);
		// log.info("当前时间加3个小时：" + DateUtil.addHours(date, 3));
		// log.info("当前时间加3天：" + DateUtil.addDays(date, 3));
		// ==可以扩展了，用Calendar的变量
		// log.info("当前时间加3年：" + DateUtil.addTimeInterval(date, Calendar.YEAR,
		// 3));

		log.info("初始化为当天的最初时间：" + DateUtil.setAsBegin(date));
		log.info("初始化为当天的结束时间：" + DateUtil.setAsEnd(date));

		log.info("获取当前的系统时间：" + DateUtil.getCurrentTime());
		log.info("按指定格式获取当前的系统时间：" + DateUtil.getCurrentTime("yyyy年MM月dd"));

		log.info("获取当前的系统时间：" + DateUtil.getCurrentDate());
		log.info("获取当前的系统时间的Long：" + DateUtil.getCurrentTimeInMillis());

		Date startDate = DateFormatUtil.parse("2013-01-02", "yyyy-MM-dd");

		Date endDate = DateFormatUtil.parse("2013-01-10", "yyyy-MM-dd");
		Date[] date1 = DateUtil.getDaysBetween(startDate, endDate);
		String str = "";
		for (int i = 0; i < date1.length; i++) {
			str += "\n" + date1[i];
		}

		log.info("得到两日期间所有日期，含起始和结束日期:" + str);

		log.info("取得指定年月的天数:" + DateUtil.getDaysOfMonth(2014, 2));

		log.info("取得某月第一天为星期几:" + DateUtil.getWeekDayOfMonth(2014, 2));

		log.info("比较两个时间大小，结束时间是否大于开始时间 :"
				+ DateUtil.compare("2013-01-23", "2014-02-21"));
		log.info("比较两个时间大小 :" + DateUtil.compareTo("2013-01-23", "2014-02-21"));

		log.info("取得日期:" + DateUtil.getDate(2013, 1, 2));
		log.info("取得日期:" + DateUtil.getDate(2013, 1, 2, 10, 32, 23));

		log.info("取得2个时间的毫秒差:" + DateUtil.getTime(startDate, endDate));

		Date date3 = DateFormatUtil.parse("2014-02-20", "yyyy-MM-dd");
		log.info("获取持续时间:" + DateUtil.getDurationTime(date3));

	}

}

/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.base.core.util.string;

import java.math.BigDecimal;
import java.util.Date;

import com.hotent.base.core.util.time.DateFormatUtil;

/**
 * @功能描述：字符串转换器
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-11-26 下午4:22:38
 */
public class StringConverter {
	/**
	 * 字符串转换整型
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static Integer toInteger(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换短整型
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static Short toShort(String str) {
		try {
			return Short.valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换长整型
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static Long toLong(String str) {
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换BigDecimal
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static BigDecimal toBigDecimal(String str) {
		try {
			return new BigDecimal(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期型
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		try {
			return DateFormatUtil.parse(str);
		} catch (Exception e) {
			return null;
		}
	}
}

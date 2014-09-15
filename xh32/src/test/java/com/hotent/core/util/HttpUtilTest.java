package com.hotent.core.util;

import java.io.IOException;

import org.apache.http.ParseException;
import org.junit.Test;

public class HttpUtilTest {

		@Test
		public void getContentByUrl() throws ParseException, IOException{
			String str=HttpUtil.getContentByUrl("http://www.163.com");
			System.out.println(str);
		}
}

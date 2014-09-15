package com.hotent.core.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hotent.core.bpm.util.BpmUtil;

public class Dom4jUtilTest {

	// @Test
	public void testTransFormXsl() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransXmlByXslt() {
		try {
			InputStream is = Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(
							"com/hotent/core/bpm/graph/process.idfl");
			String xml = FileUtil.inputStream2String(is, "utf-8");
			xml = xml.trim();

			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "testid");
			map.put("name", "testname");
			/*
			InputStream isXlst = Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(
							"com/hotent/core/bpm/graph/transform.xsl".replace(
									"/", File.separator));
			String xlstPath = FileUtil.inputStream2String(isXlst, "utf-8");
			xlstPath = xlstPath.trim();
			System.out.println(xlstPath);
			*/
			String xlstPath = "src/main/resources/com/hotent/core/bpm/graph/transform.xsl";
			String str = Dom4jUtil.transXmlByXslt(xml, xlstPath, map);
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Test
	public void testTransFileXmlByXslt() {
		fail("Not yet implemented");
	}

}

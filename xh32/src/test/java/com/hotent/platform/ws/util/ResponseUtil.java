package com.hotent.platform.ws.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;

import com.hotent.core.util.Dom4jUtil;

public class ResponseUtil {

	/**
	 * 获取请求的数据
	 * 
	 * @param url
	 * @param soapXml
	 * @return map的key :
	 *         <p>
	 *         success : 是否成功，true 成功，false 失败
	 *         <p>
	 *         code : 状态码，http 请求状态码200 表示成功，500 表示服务器异常 -100表示服务器未启动
	 *         <p>
	 *         response ： 请求返回的soap包
	 *         <p>
	 *         result 错误正确返回的结果
	 * 
	 */
	public static Map<String, Object> getResponse(String url, String soapXml) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		int code = -100;// 状态码
		boolean success = false;
		String result = "";

		HttpURLConnection conn = null;
		OutputStreamWriter os = null;
		InputStreamReader isr = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			URL servletURL = new URL(url);
			conn = (HttpURLConnection) servletURL.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");// 解决乱码问题
			// Send data
			os = new OutputStreamWriter(conn.getOutputStream());
			os.write(soapXml);
			os.flush();
			code = conn.getResponseCode();
			if (code == 200) {// 200 : OK（成功） 一切正常
				is = conn.getInputStream();
				success = true;
			} else
				is = conn.getErrorStream();
			// Get the response
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			result = sw.toString();
		} finally {
			try {
				if (os != null)
					os.close();
				if (is != null)
					is.close();
				if (isr != null)
					isr.close();
				if (br != null)
					br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String response = sb.toString();
		if (StringUtils.isNotEmpty(response)) {
			try {
				// 解析XML
				Document doc = Dom4jUtil.loadXml(response);
				SoapVisitor t = new SoapVisitor();
				if (success)
					t.op = "return";
				else
					t.op = "faultstring";
				doc.accept(t);
				result = t.val;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		rtnMap.put("success", success);
		rtnMap.put("code", code);
		rtnMap.put("response", response);
		rtnMap.put("result", result);
		return rtnMap;
	}
}

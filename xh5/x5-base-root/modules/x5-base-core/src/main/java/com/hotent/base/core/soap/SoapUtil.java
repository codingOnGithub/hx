package com.hotent.base.core.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.w3c.dom.Node;

/**
 * SOAP调用工具类
 * 
 * @author heyifan
 */
public class SoapUtil {

	private static Log logger = LogFactory.getLog(SoapUtil.class);
	// 连接超时时间
	private static Integer _connTimeout = 0;
	// 读取超时时间
	private static Integer _readTimeout = 0;

	/**
	 * 通过绑定的key获取到变量map中的根级流程变量
	 * 
	 * @param variables
	 * @param binding
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Object getWholeObject(Map<?, ?> variables, String binding)
			throws Exception {
		Object obj = null;
		Pattern regex = Pattern.compile("(\\w*)\\..*");
		Matcher regexMatcher = regex.matcher(binding);
		if (regexMatcher.find())
			obj = variables.get(regexMatcher.group(1));
		// 否则可以直接取值
		else
			obj = PropertyUtils.getProperty(variables, binding);
		return obj;
	}

	/**
	 * 节点设值
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getAttribute(Node node, String name) {
		Node tmp = node.getAttributes().getNamedItem(name);
		return tmp != null ? tmp.getTextContent() : null;
	}
	
	/**
	 * 校验是否调用失败
	 * 
	 * @param message
	 * @throws SOAPException
	 * @throws InvokeException
	 */
	public static void checkFault(SOAPMessage message) throws SOAPException,
			InvokeException {
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		SOAPBody body = envelope.getBody();
		SOAPFault fault = body.getFault();
		if (fault != null && fault.getFaultCode() != null)// 出现异常
			throw new InvokeException(fault.getFaultCode(),
					fault.getFaultString());
	}



	/**
	 * webservice 调用
	 * 
	 * @param invokeURL
	 * @param request
	 * @return
	 * @throws SOAPException
	 * @throws UnsupportedOperationException
	 * @throws MalformedURLException
	 * @throws Exception
	 */
	private static SOAPMessage invoke(URL invokeURL, SOAPMessage request)
			throws Exception {
		// 创建连接
		SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection connection = null;
		try {
			URL endPoint = new URL(null, invokeURL.toString(),
					new URLStreamHandler() {
						@Override
						protected URLConnection openConnection(URL u)
								throws IOException {
							URL clone_url = new URL(u.toString());
							HttpURLConnection clone_urlconnection = (HttpURLConnection) clone_url
									.openConnection();
							// 从配置文件中获取超时时间设置
							// _connTimeout = Integer.parseInt(AppConfigUtil
							// .get("webservice.connTimeout"));
							if (_connTimeout <= 0)
								_connTimeout = 3000;
							// _readTimeout = Integer.parseInt(AppConfigUtil
							// .get("webservice.readTimeout"));
							if (_readTimeout <= 0)
								_readTimeout = 3000;

							clone_urlconnection.setConnectTimeout(_connTimeout);
							clone_urlconnection.setReadTimeout(_readTimeout);
							return (clone_urlconnection);
						}
					});
			connection = soapConnFactory.createConnection();
			// 响应消息
			SOAPMessage reply = connection.call(request, endPoint);
			// 设置字符编码
			// reply.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
			return reply;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	public static SOAPMessage execute(Map<String, Object> variables,
			JSONObject jObject) throws Exception {
		JSONArray inputs = jObject.getJSONArray("inputs");
		JSONArray inputParams = null;
		if (jObject.containsKey("inputParams"))
			inputParams = jObject.getJSONArray("inputParams");
		String url = jObject.getString("url");
		String namespace = jObject.getString("namespace");
		String method = jObject.getString("method");
		String soapaction = "";
		if (jObject.containsKey("soapaction")) 
			soapaction = jObject.getString("soapaction");
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(namespace)
				|| StringUtils.isEmpty(method)) 
			throw new Exception("没有获取到webservice的调用地址、名称空间或调用方法.");

		
		SOAPMessage requestMessage = RequestBuilder.build(inputs, inputParams,
				namespace, method, variables, soapaction);
		// 调用
		SOAPMessage responseMessage = invoke(new URL(url), requestMessage);
		//校验是否调用失败
		checkFault(responseMessage);

		return responseMessage;
	}

	/**
	 * 根据配置模板调用webservice
	 * 
	 * @param variables
	 *            变量值
	 * @param jArray
	 *            webservice绑定设置
	 * @throws Exception
	 */
	public static void invoke(Map<String, Object> variables, JSONArray jArray)
			throws Exception {
		if (jArray.size() == 0) {
			logger.warn("没有找到webservice的调用配置." + jArray);
			return;
		}
		try {
			// 遍历所有的webservice调用
			for (Object obj : jArray) {
				JSONObject jObject = (JSONObject) obj;
				JSONArray inputs = jObject.getJSONArray("inputs");
				JSONArray outputs = jObject.getJSONArray("outputs");
				JSONArray inputParams = null;
				if (jObject.containsKey("inputParams"))
					inputParams = jObject.getJSONArray("inputParams");
				String url = jObject.getString("url");
				String namespace = jObject.getString("namespace");
				String method = jObject.getString("method");
				String soapaction = "";
				if (jObject.containsKey("soapaction")) {
					soapaction = jObject.getString("soapaction");
				}
				if (StringUtils.isEmpty(url) || StringUtils.isEmpty(namespace)
						|| StringUtils.isEmpty(method)) {
					logger.warn("没有获取到webservice的调用地址、名称空间或调用方法." + jObject);
					continue;
				}
				SOAPMessage requestMessage = RequestBuilder.build(inputs,
						inputParams, namespace, method, variables, soapaction);
				// 调用
				SOAPMessage responseMessage = invoke(new URL(url),
						requestMessage);
				// 构造response
				ResponseBuilder.build(variables, outputs, responseMessage);
			}
		} catch (Exception e) {
			logger.error("调用webservice出错.", e);
			throw e;
		}
	}

	/**
	 * 获取请求的数据
	 * 
	 * @param url
	 *            请求的URl
	 * @param soapXml
	 *            soap 包的Xml
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
		Map<String, Object> map = new HashMap<String, Object>();
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
			if (code == HttpStatus.OK.value()) {// 200 : OK（成功） 一切正常
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
			logger.error("请求出错了.", e);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			result = sw.toString();
		} finally {
			// 关闭流
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
				logger.error("关闭流出错.", e);
			}
		}
		map.put("success", success);
		map.put("code", code);
		map.put("response", sb.toString());
		map.put("result", result);
		return map;
	}
}

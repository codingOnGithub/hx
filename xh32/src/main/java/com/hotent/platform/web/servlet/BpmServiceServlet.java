package com.hotent.platform.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
/**
 * 基于Servlet方式的BPM流程服务类
 * @author csx
 *
 */
public class BpmServiceServlet extends HttpServlet{
	
	private static Log logger=LogFactory.getLog(BpmServiceServlet.class);
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProcessRunService processRunService = (ProcessRunService)AppUtil.getBean(ProcessRunService.class);
		
		String url=req.getRequestURI();
		//invoke the service method
		String invokeMethod=null;
		boolean mark = true;
		int index=url.indexOf(req.getContextPath()+"/xmlservice/");
		if(index!=-1){
			invokeMethod=url.substring(index+(req.getContextPath()+"/xmlservice/").length());
		}
		StringBuffer returnXml=new StringBuffer();
		if(invokeMethod!=null){
			 BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));  
	         String line=null;
			 StringBuffer xmlBuffer = new StringBuffer();              
	         while((line = reader.readLine()) != null) {  
	        	 xmlBuffer.append(line);  
	         }
	         logger.debug("post the xml or json is " + xmlBuffer.toString());
	      //   System.out.println("post the xml or json is " + xmlBuffer.toString());
	         try {
	        	 ProcessCmd processCmd = null;
	        	 if(xmlBuffer.toString().substring(0, 10).toLowerCase().contains("<?xml")){
		        	 processCmd = (ProcessCmd)XmlBeanUtil.unmarshall(xmlBuffer.toString(), ProcessCmd.class);
		        	 mark = true;
	        	 }else{
					 processCmd = (ProcessCmd) JSONObject.toBean(JSONObject.fromObject(xmlBuffer.toString()), ProcessCmd.class);
					 mark = false;
	        	 }
				
				if(processCmd.getUserAccount()!=null){
					ContextUtil.removeCurrentUser();
					//SysUser sysUser=sysUserService.getByAccount(processCmd.getUserAccount());
					ContextUtil.setCurrentUserAccount(processCmd.getUserAccount());
				}
				if("start".equals(invokeMethod)){
					ProcessRun processRun=processRunService.startProcess(processCmd);
					if(mark){
						returnXml.append(XmlBeanUtil.marshall(processRun, ProcessRun.class));
					}else{
						JSONObject obj = new JSONObject().fromObject(processRun);
						returnXml.append(obj.toString());
					}					
				}else{
					processRunService.nextProcess(processCmd);
					if(mark){
						returnXml.append("<result><success>true</success></result>");
					}else{
						/*JSONObject jsonObject = new  JSONObject();
						jsonObject.accumulate("success", true);
						Map map = new HashMap();  
				        map.put( "result", jsonObject); 
				        JSONObject obj = new JSONObject().fromObject(map);*/
						returnXml.append("{\"result\":{\"success\":true}}");
					}	
				}
			} catch (JAXBException e) {	
				if(mark){
					returnXml.append("<result><success>false</success></result>");
				}else{
					returnXml.append("{\"result\":{\"success\":false}}");
				}	
			} catch (Exception e) {
				e.printStackTrace();
				if(mark){
					returnXml.append("<result><success>false</success></result>");
				}else{
					returnXml.append("{\"result\":{\"success\":false}}");
				}	
			}
		}		       
        if(mark){
        	 resp.setContentType("text/xml;charset=UTF-8");
		}else{
			 resp.setContentType("text/json;charset=UTF-8");
		}	
        resp.getWriter().println(returnXml);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}

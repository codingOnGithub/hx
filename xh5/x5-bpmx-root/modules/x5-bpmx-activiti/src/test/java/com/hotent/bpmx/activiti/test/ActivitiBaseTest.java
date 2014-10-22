package com.hotent.bpmx.activiti.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.test.BaseTestCase;

@ContextConfiguration({"classpath:conf/x5-bpmx-activiti-test.xml"})
public class ActivitiBaseTest extends BaseTestCase{
	/**
	 * 通过流程定义文件获取流程的具体定义字符串
	 * @param fileName
	 * @return 
	 * String
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	protected String getBpmnFileString(String fileName) throws IOException{
		StringBuffer buffer=new StringBuffer();
		BufferedReader reader=null;
		InputStream is=null;
		try{
			is = new FileInputStream(fileName);
	        String line;
	        reader = new BufferedReader(new InputStreamReader(is));
	        line = reader.readLine(); 
	        while (line != null) { 
	            buffer.append(line);
	            buffer.append("\n");
	            line = reader.readLine();
	        }
		}catch(Exception ex){
        	ex.printStackTrace();
        }finally{
	        reader.close();
	        is.close();
		}
		return buffer.toString();
	}
}

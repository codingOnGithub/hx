package com.hotent.examples.school.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest {

	/**
	 * TODO方法名称描述
	 * @param args 
	 * void
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static void main(String[] args) throws IOException {
		Properties p=new Properties();
		InputStream input= ClassLoader.getSystemResourceAsStream("conf/x5-base-db.properties");
		 p.load(input);
		 System.out.println(p.getProperty("db.url"));
		

	}

}

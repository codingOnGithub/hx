package com.hotent.core.bpm;

import groovy.lang.GroovyShell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.hotent.core.util.AppUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class GroovyTest  {
	
	
	@Test
	public void expressionTest(){
		
		
	}
	
	public static void main(String[] arg){
		GroovyShell shell=new GroovyShell(GroovyTest.class.getClassLoader());
		shell.setVariable("i",0);
		shell.setVariable("pay",200);
		
		String str="return  (i==1 || pay==200);";
		
		Boolean str1=(Boolean)shell.evaluate(str);
		System.out.println(str1);
	}
}



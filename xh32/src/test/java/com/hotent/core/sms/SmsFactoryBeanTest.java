package com.hotent.core.sms;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.core.sms.impl.ShortMessageImpl;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:conf/app-test.xml" })
@ContextConfiguration(locations = { "classpath:conf/app-test-raise.xml" })
public class SmsFactoryBeanTest {

//	@Resource
//	ShortMessageImpl2 shortMessageImpl2;
//	
//
//
//	@Test
//	public void testGetObject() {
//
//		
//		try {
//		
//			List<String > phones=new ArrayList<String>();
//			phones.add("15013280098");
//			shortMessageImpl2.sendSms(phones, "测试一下你的人品");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}

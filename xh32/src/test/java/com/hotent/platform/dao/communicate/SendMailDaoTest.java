package com.hotent.platform.dao.communicate;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.core.jms.MessageProducer;
import com.hotent.core.model.MailModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class SendMailDaoTest {
	
	@Resource
	private MessageProducer mailMessageProducer;
	
	@BeforeClass
	public static void aa() {
		//mailProducer = conte
		
		System.out.println("0000");
	}
	
	@Test
	public void getMailQueue()
	{
//		List<SysRole> list=dao.getRole(null);
//		MailMessageProducer mailProducer = new SendMailDaoTest();
		MailModel mailModel = new MailModel();
		mailModel.setFrom("cjj");
		mailModel.setTo("ht".split(""));
		mailModel.setSubject("你好");
		mailModel.setContent("吃饭了么");
		mailMessageProducer.send(mailModel);
	}

}

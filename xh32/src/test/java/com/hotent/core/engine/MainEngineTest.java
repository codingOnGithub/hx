package com.hotent.core.engine;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.hotent.BaseTestCase;

public class MainEngineTest extends BaseTestCase {
	
	@Resource
	JavaMailSender mailSender;
	
	@org.junit.Test
	public void testSendMail(){
		SimpleMailMessage message=new SimpleMailMessage();
		message.setSubject("这是秘密抄送测试邮件");
		message.setTo(new String[]{"heyifan@jsoft.com"});
//		message.setCc(new String[]{"zyg@jsoft.com","phl@jsoft.com"});
		message.setFrom("heyifan@jsoft.com");
		message.setText("这是一个测试邮件");
		mailSender.send(message);
	}

}

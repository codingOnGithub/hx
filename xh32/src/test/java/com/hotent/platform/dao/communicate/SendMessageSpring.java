package com.hotent.platform.dao.communicate;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

import javax.jms.TextMessage;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.region.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

//import com.hotent.core.jms.ActiveMQMonitor;
import com.hotent.core.jms.MessageProducer;
import com.hotent.core.model.MailModel;
import com.hotent.platform.service.jms.QueuesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/app-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class SendMessageSpring {

	@Resource
	private MessageProducer messageProducer;
	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private ConnectionFactory simpleJmsConnectionFactory;
	@Resource(name="messageQueue")
	private ActiveMQQueue messageQueue;
    
	@Resource
    protected BrokerService broker;
	@Resource
	protected QueuesService queuesService;
	@BeforeClass
	public static void aa() {
		// mailProducer = conte
		System.out.println("0000");
	}

	@Test
	public void activeMQMonitorTest() throws Exception {
	//	ActiveMQMonitor monitor = new ActiveMQMonitor(simpleJmsConnectionFactory, broker,messageProducer);
	//	monitor.test();
	}
	
	public void activeMQMonitorTest2() throws Exception {
		//queuesService.test();
		System.out.println("0000");
	}
	
	public void getMailQueue() throws InterruptedException,
			UnsupportedEncodingException {

		String msg = "";
		int i = 0;
		do {
			msg = "第" + i + "次发送的消息：" + new Random();
			// TextMessage message = session.createTextMessage(msg);
			Thread.sleep(1000);
			// 发送消息到目的地方
			String msg2 = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
			messageProducer.send(msg2);
			System.out.println("发送消息：" + msg2);
			i++;
		} while (i < 20);
	}

}

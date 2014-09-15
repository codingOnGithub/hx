package com.hotent.platform.service.system;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.worktime.CalendarAssignService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class MessageHandlerTest
{
	//LinkedHashMap有循序
	@Resource
	Map<String, IMessageHandler> handlersMap;
	/**
	 * 根据工作日计算真正的到期时间。
	 */
	@Test
	public void getOne(){
		IMessageHandler iMessageHandler=handlersMap.get("1");
		System.out.println(iMessageHandler.getTitle());
	}
	/**
	 * 循环消息发送方式
	 * @author hjx
	 * @version 创建时间：2013-12-25  下午5:32:37
	 */
	@Test
	public void getAll(){
		//循环map
		for (Map.Entry<String, IMessageHandler> entry : handlersMap.entrySet()) {
			//System.out.println(entry.getKey()+": "+entry.getValue());
			System.out.println(entry.getKey() + ": "+((IMessageHandler)entry.getValue()).getTitle());
		}       
	}


}

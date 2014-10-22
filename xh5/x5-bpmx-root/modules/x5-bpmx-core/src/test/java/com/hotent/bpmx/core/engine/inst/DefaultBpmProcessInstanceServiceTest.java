package com.hotent.bpmx.core.engine.inst;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.db.id.UniqueIdUtil;
import com.hotent.bpmx.api.model.process.inst.BpmProcessInstance;
import com.hotent.bpmx.core.context.ContextUtil;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.org.persistence.model.DefaultUser;

public class DefaultBpmProcessInstanceServiceTest extends BpmxBaseTest{
	@Resource
	DefaultProcessInstanceService processInstanceService;
	
	@Rollback(false)
	@Test
	public void testStartProcessInstance(){
		DefaultUser user=new DefaultUser();
		user.setId("111");
		user.setAccount("zyg");
		user.setFullname("zhangyg");
		
		ContextUtil.setCurrentUser(user);
		
		String buzKey=UniqueIdUtil .getSuid();
		DefaultProcessInstCmd cmd=new DefaultProcessInstCmd();
		cmd.setFlowKey("demo3");
		cmd.setBusinessKey(buzKey);
		cmd.setSubject("test-" + buzKey);
		cmd.setStartUserId("10000000164001");
		cmd.getVariables().put("buzKey", buzKey);
		BpmProcessInstance instance=processInstanceService.startProcessInst(cmd);
		
		if(instance!=null){
			System.out.println("instance:" + instance.getId());
		}else{
			System.out.println("null");
		}
	}
}

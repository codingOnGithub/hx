package com.hotent.core.bpm;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.hotent.BaseTestCase;
import com.hotent.core.util.FileUtil;

public class BpmDefinitionTest  extends BaseTestCase{
	

	@Autowired 
	private RepositoryService repositoryService;
	
	@org.junit.Test
	public void getDef() throws Exception{
		long t=System.currentTimeMillis();
		ProcessDefinitionEntity ent= (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition("demo_hq:1:10000001190018");
		ProcessDefinitionEntity ent2=(ProcessDefinitionEntity)FileUtil.cloneObject(ent);
		System.out.println(ent2);
	}
	
	//@org.junit.Test
//	public void cloneTest(){
//		Boy boy=new Boy("zyg", 1);
//		Cloner c=new Cloner();
//		long t=System.currentTimeMillis();
//		for(int i=0;i<1000000;i++){
//			c.deepClone(boy);
//		}
//		System.out.println(System.currentTimeMillis()-t);
//		
//	}

}

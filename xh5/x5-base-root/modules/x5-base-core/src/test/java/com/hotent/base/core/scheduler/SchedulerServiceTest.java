package com.hotent.base.core.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.hotent.base.core.test.BaseTestCase;
/**
 * 需要再pom 加jira包  
 * 			<dependency>
			<groupId>org.logicalcobwebs</groupId>
			<artifactId>proxool</artifactId>
		</dependency>
		<dependency>
			<groupId>org.logicalcobwebs</groupId>
			<artifactId>proxool-cglib</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
 * @author Administrator
 *
 */
@ContextConfiguration(locations = { "classpath:conf/x5-base-core-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SchedulerServiceTest extends BaseTestCase {
	
	@Resource
	SchedulerService schedulerService;

	public void test() {
	}

	//@Test
	public void isJobExists() throws SchedulerException {
		boolean flag = schedulerService.isJobExists("test");
		System.out.println("是否存在："+flag);
	}
	

	//@Test
	public void addJob() throws SchedulerException, ClassNotFoundException{
		boolean flag=schedulerService.addJob("test", "com.hotent.base.core.scheduler.job.MyJob", "", "");
		//查看 qrtz_job_details
		System.out.println("是否创建成功："+flag);
	}
	
	@Test
	public void getJobList() throws SchedulerException{
		List<JobDetail> list = schedulerService.getJobList();
		System.out.println("取得任务列表:"+list);
	}
	
	

}

package com.hotent.base.core.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;

public class MyJob implements Job {
	protected Logger logger = LoggerFactory.getLogger(MyJob.class);


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("com.hotent.platform.job.MyJob");
	}

}

package com.rajesh.sprig.batch.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobResultListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Called before JOB()");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Called after job()");
	}

}

package com.study.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	@Scheduled(cron = "*/10 * * * * ?")
	public void start() throws Exception {
		System.out.println("ScheduledTask1任务启动了...");
		Thread.sleep(10000);
	}
	
	@Scheduled(cron = "*/1 * * * * ?")
	public void start2() throws Exception {
		System.out.println("ScheduledTask2任务启动了...");
	}
}

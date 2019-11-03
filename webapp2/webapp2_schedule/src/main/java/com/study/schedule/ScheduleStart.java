package com.study.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * 初始化定时任务
 * @author User
 *
 */
@Component
public class ScheduleStart implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleStart.class);
	
	@Autowired
	ThreadPoolTaskScheduler threadPoolTaskScheduler;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null){
            logger.info("applicationContext初始化完成...");
            this.start();
		}
	}

	public void start() {
		//在这里启动threadPoolTaskScheduler相关的定时任务
	}

}

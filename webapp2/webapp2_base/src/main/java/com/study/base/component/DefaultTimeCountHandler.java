package com.study.base.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("com.study.base.component.DefaultTimeCountHandler")
public class DefaultTimeCountHandler {

	private static Logger logger = LoggerFactory.getLogger(DefaultTimeCountHandler.class);
	
	public long start() {
		logger.info("DefaultTimeCountHandler start");
		return System.currentTimeMillis();
	}
	
	public long end() {
		logger.info("DefaultTimeCountHandler end");
		return System.currentTimeMillis();
	}
	
}

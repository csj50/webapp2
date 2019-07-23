package com.study.base.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.base.jms.AbstractJmsReceiveTemplate;

public class queueAsyHandler1 extends AbstractJmsReceiveTemplate {
	
	private final static Logger logger = LoggerFactory.getLogger(queueAsyHandler1.class);
    
	@Override
	protected void receive(Object param) throws Exception {
		logger.info("queue handler1 msg receive begin...");
		logger.info("param: {}", (String)param);
		logger.info("queue handler1 msg receive end...");
		Thread.currentThread().sleep(30*1000);
	}

}

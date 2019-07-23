package com.study.base.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.base.jms.AbstractJmsReceiveTemplate;

public class topicAsyHandler2 extends AbstractJmsReceiveTemplate {

	private final static Logger logger = LoggerFactory.getLogger(topicAsyHandler2.class);
	
	@Override
	protected void receive(Object param) throws Exception {
		logger.info("topic handler2 msg receive begin...");
		logger.info("param: {}", (String)param);
		logger.info("topic handler2 msg receive end...");
	}

}

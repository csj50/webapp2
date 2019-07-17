package com.study.base.sender;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 队列发送者
 * @author User
 *
 */
@Component
public class QueueSender {

	private final static Logger logger = LoggerFactory.getLogger(QueueSender.class);
	
	@Resource(name = "helloWorldQueue")
    private Queue helloWorldQueue;
	
	@Autowired
	JmsTemplate jmsQueueTemplate;
	
	public void send(String message) {
		logger.info("send msg: {}", message);
		jmsQueueTemplate.convertAndSend(helloWorldQueue, message);
		logger.info("msg send end...");
	}
}

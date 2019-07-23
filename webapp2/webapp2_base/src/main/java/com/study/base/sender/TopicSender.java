package com.study.base.sender;

import javax.annotation.Resource;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 主题发布者
 * @author User
 *
 */
@Component
public class TopicSender {
	private final static Logger logger = LoggerFactory.getLogger(QueueSender.class);
	
	@Resource(name = "whoAreYouTopic")
    private Topic whoAreYouTopic;
	
	@Autowired
	JmsTemplate jmsTopicTemplate;
	
	public void send(String message) {
		logger.info("send topic msg: {}", message);
		jmsTopicTemplate.convertAndSend(whoAreYouTopic, message);
		logger.info("msg send end...");
	}
}

package com.study.base.handler;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * rocketmq消息监听器 并发消费模式
 * 
 * @author User
 *
 */
public class RocketmqMessageListeners implements MessageListenerConcurrently {

	protected Logger logger = LoggerFactory.getLogger(RocketmqMessageListeners.class);

	@Value("${rocketmq.topic}")
	private String topic;

	@Value("${rocketmq.result.tag}")
	private String tag;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		for (MessageExt msg : msgs) {
			try {
				if (msg.getTopic().equals(topic)) {
					// 指定的Topic
					if (msg.getTags() != null && msg.getTags().equals(tag)) {
						// 指定Tag的消费
						String str = new String(msg.getBody());
						System.out.println("消费：" + str);
					}
				}

			} catch (Exception ex) {
				logger.error("系统异常：{}", ex);
			}
		}
		// 如果没有return success，consumer会重新消费该消息，直到return success
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

	}

}

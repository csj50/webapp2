package com.study.base.jms;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * rocketmq消费者延迟加载
 * @author User
 *
 */
public class DefaultMQPushConsumerLazyInit extends DefaultMQPushConsumer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMQPushConsumerLazyInit.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (event.getApplicationContext().getParent() == null){
                logger.info("rocketmq consumer execute start method ...");
                this.start();
            }
        } catch (Exception e) {
            //调用start失败，导致rocket启动失败
            logger.error(e.getMessage(), e);
        }
    }

}

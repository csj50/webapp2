package webapp;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
@ActiveProfiles("dev")
public class RocketTest {
	protected static Logger logger = LoggerFactory.getLogger(RocketTest.class);
	
	@Autowired
	DefaultMQProducer defaultMQProducer;
	
	@Test
	public void producerTest() throws Exception {
		for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest1" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //投递消息到其中一个brokers
            SendResult sendResult = defaultMQProducer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
		Thread.sleep(60*1000);
	}
	
	@Test
	public void consumerTest() throws Exception {
		System.out.println("消费者测试开始...");
		Thread.sleep(60*1000);
		System.out.println("消费者测试结束...");
	}
}

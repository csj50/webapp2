package webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.schedule.MessagePrinterTask;
import com.study.schedule.MyTask1;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
@ActiveProfiles("dev")
public class TaskTest {
	protected static Logger logger = LoggerFactory.getLogger(TaskTest.class);
	
//	@Autowired
//	ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	ThreadPoolTaskScheduler threadPoolTaskScheduler;
	
	@Before
	public void start() {
		System.out.println("测试开始----------");
	}
	
	@After
	public void end() {
		System.out.println("测试结束----------");
	}
	
	@Test
	public void testTaskExecutor() {
		//执行实现了Runnable接口的类
		MyTask1 myTask1 = new MyTask1();
		myTask1.execute(new MessagePrinterTask("apple"));
		myTask1.execute(new MessagePrinterTask("banana"));
	}
	
	@Test
	public void testSimpleAsyncTaskExecutor() {
		//异步线程执行
		SimpleAsyncTaskExecutor myTask2 = new SimpleAsyncTaskExecutor();
		myTask2.execute(new MessagePrinterTask("apple"));
		myTask2.execute(new MessagePrinterTask("banana"));
	}
	
	@Test
	public void testSyncTaskExecutor() {
		//同步线程执行
		//和自己实现TaskExecutor接口一样
		SyncTaskExecutor myTask3 = new SyncTaskExecutor();
		myTask3.execute(new MessagePrinterTask("apple"));
		myTask3.execute(new MessagePrinterTask("banana"));
	}
	
//	@Test
//	public void testThreadPoolTaskExecutor() {
//		//带线程池的TaskExecutor
//		//异步线程执行
//		//可自己配置线程池大小
//		threadPoolTaskExecutor.execute(new MessagePrinterTask("apple"));
//		threadPoolTaskExecutor.execute(new MessagePrinterTask("banana"));
//	}
	
	@Test
	public void testThreadPoolTaskScheduler() {
		threadPoolTaskScheduler.schedule(new MessagePrinterTask("apple"), new CronTrigger("0/10 * * * * ?"));
		threadPoolTaskScheduler.execute(new MessagePrinterTask("banana"));
		try {
			System.out.println("main thread sleep start.");
			Thread.sleep(36000); //睡眠36秒
			System.out.println("main thread sleep stop.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

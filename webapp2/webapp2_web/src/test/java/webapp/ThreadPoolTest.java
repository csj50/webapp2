package webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.base.threads.ThreadPool;
import com.study.base.threads.ThreadPool2;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
@ActiveProfiles("dev")
public class ThreadPoolTest {

//	@Autowired
//	ThreadPool pool1;

	@Autowired
	ThreadPool2 pool2;

	@Test
	public void testPool1() {
		// ThreadPool由spring实例化，代码获取单例对象
		ThreadPool pool1 = ThreadPool.getInstance();
		for (int i = 0; i < 1000; i++) {
			pool1.addTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("<" + Thread.currentThread().getName() + ">执行结束...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
		// 主线程睡眠
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool1.closePool();
	}

	@Test
	public void testPool2() {
		for (int i = 0; i < 1000; i++) {
			pool2.run(new Runnable() {
				@Override
				public void run() {
					System.out.println("<" + Thread.currentThread().getName() + ">执行结束...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
		// 主线程睡眠
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		pool2.shutdown();
	}

}

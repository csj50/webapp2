package webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.base.util.ApplicationContextHolder;
import com.study.module1.dubbo.resource.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
@ActiveProfiles("dev")
public class DubboTest {

	@Test
	public void test() throws Exception {
		DemoService demo = ApplicationContextHolder.getBean("dubboConsumer", DemoService.class);
		String result = demo.echo("我是谁");
		System.out.println(result);
		System.out.println("111111111111111111111111");
		Thread.sleep(60 * 1000);
	}
}

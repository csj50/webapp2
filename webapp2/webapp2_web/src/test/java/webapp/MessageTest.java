package webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.base.common.Constants;
import com.study.base.common.MessageSourceAccessor;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
@ActiveProfiles("dev")
public class MessageTest {

	@Autowired
	private MessageSourceAccessor msAccessor;

	@Test
	public void testMessage() {
		String error1 = msAccessor.getMessage(Constants.E000001);
		System.out.println("msg: " + error1);
		String error2 = msAccessor.getMessage(Constants.E000003, "错误码", "03");
		System.out.println("msg: " + error2);
	}
}

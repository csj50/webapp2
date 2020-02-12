package webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
@ActiveProfiles("dev")
public class RedisTest {
	@Autowired
    private RedissonClient redissonClient;
	
	@Test
	public void info() throws Exception {
		System.out.println(redissonClient.getConfig().toYAML());
	}
	
	@Test
    public void set() {
        // 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket("s1");
        keyObj.set("123456");
    }

	@Test
    public void get() {
        // 获取字符串
        RBucket<String> keyObj = redissonClient.getBucket("s1");
        System.out.println(keyObj.get());
    }

}

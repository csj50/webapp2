package webapp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.base.dao.TeacherMapper;
import com.study.base.vo.TeacherVo;
import com.study.testBean.Student;
import com.study.testBean.Teacher;
import com.study.util.ApplicationContextHolder;
import com.study.util.PropertiesUtil;
import com.study.util.ReflectInvokeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
@ActiveProfiles("dev")
public class MyTest {

	@Value("${flag}")
	String flag;
	
	@Autowired
	Student stu;
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Test
	public void testInvoke() throws Exception {
		System.out.println("第一个测试方法*******");
		ApplicationContext ac = ApplicationContextHolder.getApplicationContext();
		ReflectInvokeUtil.invokeMethod("com.study.testBean.Student", "sayHello", null);
	}
	
	@Test
	public void testById() {
		System.out.println("第二个测试方法*******");
		ApplicationContext ac = ApplicationContextHolder.getApplicationContext();
		Student stu = (Student)ApplicationContextHolder.getBean("id.student1");
		stu.sayHello();
		System.out.println("**********");
	}
	
	@Test
	public void testProperties() {
		System.out.println("第三个测试方法*******");
		System.out.println("flag is:" + flag);
		System.out.println("appName is:" + PropertiesUtil.getProperties("appName"));
	}
	
	@Test
	public void testAutowired() {
		System.out.println("第四个测试方法*******");
		stu.sayHello();
		System.out.println("**********");
	}
	
	@Test
	public void testMybatis() {
		System.out.println("第五个测试方法*******");
		Teacher teacher = teacherMapper.getTeacherById(1);
		System.out.println("id is: " + teacher.getId());
		System.out.println("name is: " + teacher.getName());
	}
	
	@Test
	public void testPageable() {
		System.out.println("第六个测试方法*******");
		TeacherVo teacherVo = new TeacherVo();
		teacherVo.setId(2);
		teacherVo.setPageNum(5);
		teacherVo.setPageSize(4);
		List<Teacher> list = teacherMapper.getTeacherPageable(teacherVo);
		System.out.println("list size is:" + list.size());
	}
}

package com.study.web.resources;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.annotation.TimeCount;
import com.study.base.component.CacheTestComp;
import com.study.base.exception.BizException;
import com.study.base.exception.SysException;
import com.study.base.sender.QueueSender;
import com.study.base.sender.TopicSender;
import com.study.domain.testBean.Teacher;

@Controller
@RequestMapping("mvc")
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	QueueSender queueSender;
	
	@Autowired
	TopicSender topicSender;
	
	@Autowired
	CacheTestComp cacheTestComp;
	
	@RequestMapping("hello")
	@TimeCount
	public String hello() {
		log.info("hello in...");
		log.info("hello out...");
		return "hello";
	}

	@RequestMapping("testError")
	public void testError() {
		log.info("testError in...");
		log.info("testError out...");
		throw new BizException("1234567890", "报错信息1");
	}
	
	@RequestMapping("testError2")
	public void testError2() {
		log.info("testError2 in...");
		log.info("testError2 out...");
		throw new SysException("0987654321", "报错信息2");
	}
	
	@RequestMapping("queueSend")
	public void queueSend(HttpServletRequest request) {
		String msg = request.getParameter("msg");
		queueSender.send(msg);
	}
	
	@RequestMapping("topicSend")
	public void topicSend(HttpServletRequest request) {
		String msg = request.getParameter("msg");
		topicSender.send(msg);
	}
	
	@RequestMapping("testCache")
	public void testCache(HttpServletRequest request) {
		String number = request.getParameter("number");
		Teacher teacher = cacheTestComp.getTeacherById(Integer.parseInt(number));
		log.info("name is: {}", teacher.getName());
	}
}

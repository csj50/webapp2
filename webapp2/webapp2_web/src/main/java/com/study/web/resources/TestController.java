package com.study.web.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.exception.BizException;
import com.study.base.exception.SysException;

@Controller
@RequestMapping("mvc")
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("hello")
	private String hello() {
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
}

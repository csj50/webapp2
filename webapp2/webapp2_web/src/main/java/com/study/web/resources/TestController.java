package com.study.web.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mvc")
public class TestController {

	@RequestMapping("hello")
	private String hello() {
		return "hello";
	}
}

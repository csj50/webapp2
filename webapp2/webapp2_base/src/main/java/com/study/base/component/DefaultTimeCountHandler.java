package com.study.base.component;

import org.springframework.stereotype.Component;

@Component("com.study.base.component.DefaultTimeCountHandler")
public class DefaultTimeCountHandler {

	public long start() {
		return System.currentTimeMillis();
	}
	
	public long end() {
		return System.currentTimeMillis();
	}
	
}

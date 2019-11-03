package com.study.schedule;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MyTask2 extends SimpleAsyncTaskExecutor {

	private static final long serialVersionUID = -8958545057173751951L;

	@Override
	public void execute(Runnable task) {
		// TODO Auto-generated method stub
		System.out.println("hello task.");
		task.run();
	}
}

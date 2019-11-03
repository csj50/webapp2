package com.study.schedule;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MyTask1 implements TaskExecutor{

	@Override
	public void execute(Runnable task) {
		// TODO Auto-generated method stub
		System.out.println("hello task.");
		task.run();
	}

}

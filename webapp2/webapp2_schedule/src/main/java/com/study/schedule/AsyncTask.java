package com.study.schedule;

import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

	@Async
	public void start(int num) throws Exception {
		Random r = new Random();
		System.out.println("" + num + "号马起跑了...");
		Thread.sleep(r.nextInt(1000));
		System.out.println("" + num + "号马已经跑完了");
	}
}

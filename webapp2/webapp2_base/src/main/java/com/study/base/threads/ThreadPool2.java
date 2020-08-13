package com.study.base.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class ThreadPool2 implements DisposableBean {
	public static ThreadPool2 threadPool;
	ExecutorService executorService = Executors.newFixedThreadPool(20);

	public static ThreadPool2 getInstance() {
		if (threadPool == null) {
			threadPool = new ThreadPool2();
		}
		return threadPool;
	}

	public void run(Runnable r) {
		executorService.execute(r);
	}

	public void shutdown() {
		if (threadPool != null) {
			executorService.shutdown();
		}
	}

	/**
	 * 优雅的关闭
	 */
	@Override
	public void destroy() throws Exception {
		shutdown();
	}
}

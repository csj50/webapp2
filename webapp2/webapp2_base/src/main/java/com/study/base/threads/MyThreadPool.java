package com.study.base.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
	//工作线程集合
	private List<WorkThread> workThreads;
	//并发队列
	private BlockingQueue<Runnable> runableQueue;
	//线程停止标志位
	private static boolean isRun = false;
	
	/**
	 * 初始化线程池
	 */
	public MyThreadPool(int maxThreadCount, int QueueSize) {
		//List保存线程信息
		workThreads = new ArrayList<WorkThread>(maxThreadCount);
		
		//初始化队列
		//限制队列容量
		runableQueue = new LinkedBlockingQueue<>(QueueSize);
		
		//启动线程
		isRun = true;
		
		//初始化线程池
		//创建固定的线程
		for (int i=0; i < maxThreadCount ; i++) {
			WorkThread work = new WorkThread();
			work.start();
			workThreads.add(work);
		}
		
	}
	
	//发现运行时会有cpu 100%的情况
	//添加Thread.sleep(1)，
	//所有线程每次进入while循环的时候，都先休眠释放资源，这cpu就不会升到100
	private class WorkThread extends Thread {
		@Override
		public void run() {
			
			//线程一直运行
			while(isRun) {
				
				//先释放资源，避免cpu占用过高
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Runnable runnable = runableQueue.poll();
				if (runnable != null) {
					runnable.run();
				}
			}
		}
	}
	
	/**
	 * 执行就是往队列里添加任务
	 */
	public boolean execute(Runnable command) {
		//offer添加成功返回true，队列满了返回false
		return runableQueue.offer(command);
	}
	
	/**
	 * 关闭线程池
	 */
	public void closePool() {
		if (isRun) {
			//停止线程
			isRun = false;
			//清空队列
			runableQueue.clear();
			workThreads.clear();
		}
	}
	
	public static void main(String[] args) {
		MyThreadPool myThreadPool = new MyThreadPool(5, 100);
		for (int i=0; i<100; i++) {
			final int finalI = i;
			myThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "," + finalI);
				}
			});
		}
		
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myThreadPool.closePool();
	}
}

package com.study.base.util;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用aqs手写Lock锁
 * @author user
 *
 */
public class AqsTryLock {

	/**
	 * 区分公平锁和非公平锁
	 */
	private static boolean fairFlag = false;
	
	/**
	 * 锁的状态：0-没有线程获取到锁；1-有线程获取到锁
	 */
	private AtomicInteger lockState = new AtomicInteger(0);
	
	/**
	 * 获取到锁的线程
	 */
	private Thread getLockThread = null;
	
	/**
	 * 存放没有获取到锁的线程的队列
	 */
	private ConcurrentLinkedDeque<Thread> concurrentLinkedDeque = new ConcurrentLinkedDeque<Thread>();
	
	/**
	 * 构造方法
	 * @param fair
	 */
	AqsTryLock(boolean fair) {
		fairFlag = fair;
	}
	
	/**
	 * 获取锁
	 */
	public void lock() {
		acquire();
	}
	
	/**
	 * 释放锁
	 */
	public void unlock() {
		release();
	}
	
	/**
	 * 获取
	 * @return
	 */
	public boolean acquire() {
		//死循环，自旋，阻塞后重新获取锁
		for(;;) {
			System.out.println(Thread.currentThread().getName() + "cas操作");
			if (compareAndSet(0, 1)) {
				//获取锁成功
				getLockThread = Thread.currentThread();
				return true;
			} else {
				//获取锁失败
				Thread thread = Thread.currentThread();
				//将线程装入链表
				concurrentLinkedDeque.add(thread);
				//阻塞当前线程
				LockSupport.park();
				
				//经测试，阻塞后，不会打印和return
				//阻塞后，会停在park()这里，唤醒后，继续往下执行
				System.out.println(thread.getName() + "被唤醒");
			}
		}
	}
	
	/**
	 * 释放
	 * @return
	 */
	public boolean release() {
		if (getLockThread == null) {
			return false;
		} 
		
		if (getLockThread == Thread.currentThread()) {
			boolean result = compareAndSet(1, 0);
			if (result) {
				if (fairFlag == true) {
					//公平锁的唤醒，唤醒队列的第一个线程
					Thread thread = concurrentLinkedDeque.poll();
					LockSupport.unpark(thread);
				} else {
					//非公平锁的唤醒，循环释放队列里的所有线程
					while(true) {
						Thread thread = concurrentLinkedDeque.poll();
						if (thread != null) {
							LockSupport.unpark(thread);
						} else {
							break;
						}
					}
				}
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	/**
	 * 实现CAS功能
	 * @param expect
	 * @param update
	 * @return
	 */
	public boolean compareAndSet(int expect, int update) {
		return lockState.compareAndSet(expect, update);
	}
	
	public static void main(String[] args) throws Exception {
		AqsTryLock aqsTryLock = new AqsTryLock(false);
		aqsTryLock.lock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "start");
				aqsTryLock.lock();
				System.out.println(Thread.currentThread().getName() + "end");
			}
		}).start();
		
		Thread.sleep(1000);
		aqsTryLock.unlock();
	}
}

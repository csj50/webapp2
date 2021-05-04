package com.study.base.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 利用cas手写一把锁
 * 
 * @author User
 *
 */
public class AtomicTryLock {

	/**
	 * 0--表示没有人获取该锁 1--表示该锁已经被线程持有了
	 */
	private AtomicInteger cas = new AtomicInteger(0);

	/**
	 * 记录这把锁当前是被哪个线程所持有的
	 */
	private Thread lockCurrentThread;

	/**
	 * 计数器实现可重入功能
	 */
	private int count;

	/**
	 * 获取锁
	 * 获取成功返回true，cas从0变为1，获取失败返回false，cas更新失败
	 * 
	 * @return
	 */
	public boolean tryLock() {
		boolean result;

		// 如果当前线程已经获取到了锁，线程数增加一，然后返回
		if (lockCurrentThread == Thread.currentThread()) {
			count++;
			return true;
		}

		// 通过自旋不断重试
		while (true) {
			result = cas.compareAndSet(0, 1);
			if (result) {
				lockCurrentThread = Thread.currentThread();
				return result;
			}else {
				System.out.println(Thread.currentThread().getName() + ", 重试");
			}
			//释放cpu资源，避免cpu飙高
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	/**
	 * 释放锁
	 * 
	 * @return
	 */
	public boolean unLock() {
		// 锁必须要当前拿到锁的线程来释放
		if (lockCurrentThread != Thread.currentThread()) {
			return false;
		}

		if (count > 0) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
			count--;
			return true;
		} else {
			return cas.compareAndSet(1, 0);
		}
	}

	public static void main(String[] args) {
		AtomicTryLock atomicTryLock = new AtomicTryLock();
		IntStream.range(1, 10).forEach((i) -> new Thread(() -> {
			try {
				boolean result = atomicTryLock.tryLock();
				if (result) {
					atomicTryLock.lockCurrentThread = Thread.currentThread();
					System.out.println(Thread.currentThread().getName() + ",获取锁成功!");
				} else {
					System.out.println(Thread.currentThread().getName() + ",获取锁失败!");
				}

			} catch (Exception e) {

			} finally {
				if (atomicTryLock != null) {
					Boolean b = atomicTryLock.unLock();
					if (b) {
						System.out.println(Thread.currentThread().getName() + ",释放锁成功!");
					}
				}
			}
		}).start());
	}

}

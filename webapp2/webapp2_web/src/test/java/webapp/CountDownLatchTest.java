package webapp;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2); //aqs状态为2
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					System.out.println("t1开始执行...");
					countDownLatch.await(); //线程阻塞
					System.out.println("t1结束执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1").start();
		
		countDownLatch.countDown(); //aqs状态减1
		countDownLatch.countDown();
	}
}

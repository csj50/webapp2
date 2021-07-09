package webapp;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5); //最多支持5个访问
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						//获取票据，aqs锁的状态减1
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "," + finalI);
						Thread.sleep(1000);
						//aqs锁的状态加1
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}

package com.study.schedule;

public class MessagePrinterTask implements Runnable {
	private String message;

	public MessagePrinterTask(String message) {
		this.message = message;
	}

	public void run() {
		for (int i = 0; i < 25; i++) {
			System.out.println(message + " num: " +i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

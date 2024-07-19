package com.test.sku.thread;

public class Consumer extends Thread {

	private Counter counter;
	
	public Consumer(Counter counter) {
		super("소비자");
		this.counter = counter;
	}
	@Override
	public void run() {
		while(true) {
			counter.decrease();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

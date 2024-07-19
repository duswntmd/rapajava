package com.test.sku.thread;

public class Producer extends Thread {

	private Counter counter;
	
	public Producer(Counter counter) {
		super("생산자");
		this.counter = counter;
		this.setPriority(MAX_PRIORITY);
	}
	
	@Override
	public void run() {
		while(true) {
			counter.increase();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

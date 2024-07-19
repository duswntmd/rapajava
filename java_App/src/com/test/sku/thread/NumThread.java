package com.test.sku.thread;

public class NumThread extends Thread {

	public NumThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		String it = Thread.currentThread().getName();
		for(int i=0; i<10; i++) {
			System.out.println(it + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Thread Dead");
	}

}

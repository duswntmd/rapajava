package com.test.sku.thread;

import java.util.Date;

public class Run2 implements Runnable {

	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(new Date());
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

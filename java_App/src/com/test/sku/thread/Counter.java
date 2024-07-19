package com.test.sku.thread;

public class Counter {
	
	int cnt = 5;
	/* cnt의 값이 항상 5~10을 유지하도록 쓰레드를 제어
	 * cnt가 5가 되면 소비를 몀추고 생산이 되어야 한다
	 * cnt가 10이 되면 생산을 멈추고 소비가 되어야 한다
	 * cnt가 5가 되면 소비 쓰레드는 기다려야 한다(wait())
	 * cnt가 10에 도달하면 생산 쓰리드는 기다려야 한다(wait())
	 * cnt가 5가 되면 생산자에게 통지를 전해야 한다(notify(), notifyAll())
	 */
	public synchronized void increase() {	

		if(cnt>=10) {
			try {
				this.notify();
				this.wait();
			} catch (InterruptedException ie) {
				// TODO Auto-generated catch block
				ie.printStackTrace();
			}
		}
		System.out.println("올리기전: " + cnt);
		cnt++;
		System.out.println("올린 후: " + cnt);
		
		}
	
	public synchronized void decrease() {
		if(cnt<=5) {
			try {
					this.notify();
					this.wait();
				
			} catch (InterruptedException ie) {
				// TODO Auto-generated catch block
				ie.printStackTrace();
			}
		}
		System.out.println("내리기전: " + cnt);
		cnt--;
		System.out.println("내린 후: " + cnt);
			
		}
}

package com.test.sku.thread;

public class ThreadMain {

	public static void main(String[] args) {
		/* Thread: 가상의 CPU(VCPU)
		 * MultiTasking:
		 * Chat : 이용자가 메시지를 네트워크로 출력, 다른컴에서 메시지를 입력하는 것
		 * Thread는 VCPU이므로 지정된 코드를 할당하여 실행할 수 있다
		 * Thread는 VCPU이므로 자신만의 Stack을 가지고 지역변수를 생성한다
		 * Thread에게는 실행가능한 코드를 부여하여 실행하게 한다
		 * Thread가 실행할 수 있는 코드는 Runnable 인터페이스 구현체이어야 한다
		 * Thread에게 전달될 코드는 Runnable이어야 한다
		 */
//		threadTest01();
//		threadTest02();
//		Thread mt = Thread.currentThread();
//		String tname = mt.getName(); //main
		
		Counter cnt = new Counter();
		Producer prod = new Producer(cnt);
		Consumer cons = new Consumer(cnt);
		prod.start();
		cons.start();
		
		System.out.println("main 프로그램 종료");
	} //end of main()
	
	private static void threadTest02() {
		NumThread nt = new NumThread("숫자 쓰레드");
		DataThread dt = new DataThread("날짜 쓰레드");		
		nt.start();
		dt.start();
	}
	
	private static void threadTest01() {
		Thread t1 = new Thread(new RunnableImpl01(), "수올리기 쓰레드");
		t1.start();
		
		Thread t2 = new Thread(new Run2(), "날짜 쓰레드");
		t2.start();
	}

}

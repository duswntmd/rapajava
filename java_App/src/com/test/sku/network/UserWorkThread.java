package com.test.sku.network;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UserWorkThread extends Thread{
	
	private Socket s;
	private ObjectInputStream oin;
	private ObjectOutputStream oos;

	public UserWorkThread() {}
	
    public UserWorkThread(Socket s) {
        this.s = s;
    }
	
	@Override
	public void run() {
		// 서버측에서 클라이언트에게 "서버접속 성공"
		try {
            OutputStream out = s.getOutputStream();
            this.oos = new ObjectOutputStream(out);
             
            ChatMsg cm = new ChatMsg("서버", "클라이언트", "업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x)");
            oos.writeObject(cm);
            oos.flush();
            
            InputStream in = s.getInputStream();
            this.oin = new ObjectInputStream(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
		System.err.println("UserWorkThread dead");
	}
}

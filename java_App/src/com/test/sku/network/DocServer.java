package com.test.sku.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DocServer {

	public static void main(String[] args) {
		try {
			ServerSocket ds = new ServerSocket(1111);
			while(true) {
				System.out.println("서버 대기 중...");
				Socket s = ds.accept(); // 클라이언트가 접속하면 통신용 소켓을 리턴한다
				// 접속한 이용자와 통신할 때 사용하는 소켓
				System.out.println("클리이언트 접속됨");
				
				new UserWorkThread(s).start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("서버 종료");

	}

}

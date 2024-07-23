package com.test.sku.network;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.net.ssl.SSLContext;

public class Server {

	public static void main(String[] args) {
		// 다중 이용자 채팅 서버
		try {
			ServerSocket ss = new ServerSocket(1234);
			while(true) {
				System.out.println("서버 대기 중...");
				Socket s = ss.accept(); // 클라이언트가 접속하면 통신용 소켓을 리턴한다
				// 접속한 이용자와 통신할 때 사용하는 소켓
				System.out.println("클리이언트 접속");
				
				new LoginThread(s).start();

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("서버 종료");
	}

}

//InputStreamReader isr = new InputStreamReader(in);
//BufferedReader br = new BufferedReader(isr);
//String line = br.readLine();
//System.out.println(line);

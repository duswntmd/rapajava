package com.test.sku.network;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	static Scanner kbd = new Scanner(System.in);

	public static void main(String[] args) {
		// 클라이언트 소켓을 이용하여 서버에 접속한다
		try {
			Socket s = new Socket("localhost", 1234); //접속 요청
			
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			ObjectInputStream oin = new ObjectInputStream(in);
			ChatMsg cm = (ChatMsg)oin.readObject();
			System.out.println(cm.msg + ":");
			
			String uid = kbd.next();
			String pwd = kbd.nextLine();
			
			//출력스트림, uid, pwd를 ChatMsg에 저장하여 서버로 전송한다
			ChatMsg cm2 = new ChatMsg(true, uid, pwd);
			cm2.login = true;
			
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(cm2);
			oos.flush();
			
			ChatMsg cm3 = (ChatMsg)oin.readObject();
			
			if(cm3.msg.equals("로그인 성공")) {
				while(true) {
					System.out.println("메세지: ");
					String msg = kbd.nextLine();		
					cm3.msg = msg;
					oos.writeObject(cm3);
					oos.flush();
					
					cm3 = (ChatMsg)oin.readObject();
					System.out.println(cm3.msg);
				}
			}
			
//			new ChatThread(oin, oos).start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("클라이언트 종료");
	}

}

//InputStreamReader isr = new InputStreamReader(in);
//BufferedReader br = new BufferedReader(isr);
//String line = br.readLine();
//System.out.println(line); // 아이디 암호:

//OutputStreamWriter osw = new OutputStreamWriter(out);
//PrintWriter pout = new PrintWriter(osw);
//pout.println(uid + ":" + pwd);
//pout.flush();

// 누가, 누구에게, 무엇을

//Thread.sleep(2000);

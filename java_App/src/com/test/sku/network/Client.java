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
//			Socket s = new Socket("220.67.113.231", 1234); //접속 요청
			
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
			
			cm = (ChatMsg)oin.readObject();
			
			if(cm.msg.equals("로그인 성공")) {
				new ClientNetinputThread(oin).start();
				while(true) {
					// 귀속말(s) 공개메시지(p):
					// s: 수신자 아이디:ㅁㅁㅁㅁ 메시지: ㄱㄱㄱ
					// msg.isSecret = true
					// msg.to = ㅁㅁㅁ
					// msg.msg = ㄱㄱㄱ
					
					while(true) {   // 채팅 시작
						System.out.println("귓속말(s) 공개메시지(p) 종료(x):");
						String m = kbd.nextLine().trim();
						
						if(m.equalsIgnoreCase("x")) {
							System.out.println("채팅을 종료합니다");
							break;
						}
						else if(m.equals("s")) {
							System.out.println("수신자:");
							String rec = kbd.nextLine();
							
							System.out.println("메시지:");
							String msg = kbd.nextLine();
							
							cm = new ChatMsg();
							cm.uid = uid;  			// 송신
							cm.isSecret = true;		// 비밀 메시지
							cm.to = rec;			// 수신
							cm.msg = msg;			// 대화
							
							oos.writeObject(cm);
							oos.flush();
							continue;
				 			
						}
						System.out.println("메시지:");
						String msg = kbd.nextLine().trim();
						
						cm = new ChatMsg();
						cm.uid = uid;
						cm.msg = msg;
						oos.writeObject(cm);
						oos.flush();
					}
				}
			}else {
				System.out.println("로그인 실패");
			}
			
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

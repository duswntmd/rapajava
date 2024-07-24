package com.test.sku.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.stream.FileImageOutputStream;



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
						}else if(m.equals("s")) {
							System.out.println("수신자:");
							String rec = kbd.nextLine();
							
							System.out.println("메시지:");
							String msg = kbd.nextLine();
							
							System.out.println("첨부:");
							String fname = kbd.nextLine();
							
							cm = new ChatMsg();
							cm.uid = uid;  			// 송신
							cm.isSecret = true;		// 비밀 메시지
							cm.to = rec;			// 수신
							cm.msg = msg;			// 대화
									
							if (fname!=null && !fname.equals("")) {
								byte[] fdata = new FileIO().load(fname);
								if(fdata!=null) {
									cm.fname = fname;
									cm.fdata = fdata;
									System.out.println("첨부파일이 전송되었습니다.");
								}else {
									System.out.println("첨부파일 없이 메시지만 전송합니다.");
								}
					        }
						
							oos.writeObject(cm);
							oos.flush();	
						}else if(m.equals("p")) {
						System.out.println("메시지:");
						String msg = kbd.nextLine().trim();
						
						cm = new ChatMsg();
						cm.uid = uid;
						cm.msg = msg;
						oos.writeObject(cm);
						oos.flush();
						}else if(m.equals("y")) {
							String fname = ClientNetinputThread.chatMsg.fname;
							byte[] fdata = ClientNetinputThread.chatMsg.fdata;
							boolean saved = new FileIO().download(fname, fdata);
							if(saved) System.out.println("다운로드 성공");
							else System.out.println("다운로드 실패");
						}
						
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

/* 파일 보내고 받기
 * 파일명(String), 파일 데이터(byte[])를 저장할 수 있는 변수
 * 이용자에게 메시지를 보낼 때 "첨부(a)" 메뉴를 제시한다
 * 이용자가 파일명을 입력하면 그 파일을 로드하여 byte[] 형식으로 ChatMsg 변수에 저장
 * 수신자가 메시지를 확인하면 "첨부파일 다운로드"(y/n)" 메뉴를 제시하고 "y"누르면 다운로드한다
 * 다운로드를 마치면 "첨부파일 다운로드 성공" 메시지를 표시한다 
 */
 
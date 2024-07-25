package com.test.sku.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class DocClient {
	
	static Scanner kbd = new Scanner(System.in);

	public static void main(String[] args) {
		// 클라이언트 소켓을 이용하여 서버에 접속한다
		try {
			Socket s = new Socket("localhost", 1111); //접속 요청
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
				while(true) {
					while(true) {   // 채팅 시작
						System.out.println("업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):");
						String m = kbd.nextLine().trim();
						
						if(m.equalsIgnoreCase("x")) {
							System.out.println("채팅을 종료합니다");
							break;
						}else if(m.equals("s")) {
							
						}else if(m.equals("a")) {
							System.out.println("첨부:");
							String fname = kbd.nextLine();
						       
		                    System.out.println("작성자:");
		                    String author = kbd.nextLine().trim();
		                    
		                    System.out.println("설명:");
			                String explanation = kbd.nextLine().trim();
		                    
		                    				
						cm = new ChatMsg();
						cm.uid = uid;
						cm.fname = fname;
						cm.explanation = explanation;
						

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


package com.test.sku.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DocClient {
	
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 1111); //접속 요청
			System.out.println("클라이언트 접속");
			
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			ObjectInputStream oin = new ObjectInputStream(in);
			ChatMsg cm = (ChatMsg)oin.readObject();
			System.out.println(cm.msg);
			
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(cm);
			oos.flush();
			
			
			 while (true) {
	                String m = kbd.nextLine();
	                if (m.equalsIgnoreCase("x")) {
	                    break;
	                }else if(m.equalsIgnoreCase("a")){
	                	System.out.println("업로드 파일:");
						String fname = kbd.nextLine();
						
						cm = new ChatMsg();
						cm.isSecret = true;		// 비밀 메시지
									
						if (fname!=null && !fname.equals("")) {
							byte[] fdata = new FileIO().load(fname);
							if(fdata!=null) {
								cm.fname = fname;
								cm.fdata = fdata;
								System.out.println("사진업로드 성공");
							}else {
								System.out.println("업로드할 사진이 없습니다.");
							}
				        }
					
						oos.writeObject(cm);
						oos.flush();
	                }
	         }
	         s.close();
			
		}  catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("클라이언트 종료");
	}
}

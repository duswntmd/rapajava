package com.test.sku.doc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DocClient {

    static Scanner kbd= new Scanner(System.in);

    public static void main(String[] args){

        try {
                Socket s = new Socket("localhost", 1234);
                System.out.println("서버 접속");

                InputStream in=s.getInputStream();
                ObjectInputStream oin= new ObjectInputStream(in);
                Msg menu =(Msg)oin.readObject();

                OutputStream out =s.getOutputStream();
                ObjectOutputStream oos= new ObjectOutputStream(out);

               
               while (true)
               {
            	   System.out.println(menu.menu);
                   String m = kbd.nextLine();

                   if (m.equals("a")) {
//                	   System.out.print("번호:");
//                	   int number = Integer.parseInt(kbd.nextLine());
                       System.out.print("파일명:");
                       String fileName = kbd.nextLine();
                       System.out.print("작성자:");
                       String who = kbd.nextLine();
                       System.out.print("내용 입력:");
                       String content = kbd.nextLine();

                       Msg a = new Msg();
                       if (fileName != null && !fileName.equals("")) {
                           byte[] fdata = new FileIO().load(fileName);
                           if (fdata != null) {
                               a.fileName = fileName;
                               a.fileData = fdata;
                           }else {
                               System.err.println("파일이 없습니다.");
                           }
                       }
//                       a.number = number;
                       a.saveFiles = true;
                       a.upload = true;
                       a.who = who;
                       a.content = content;

                       oos.writeObject(a);
                       oos.flush();

                       Msg res =(Msg)oin.readObject();
                       System.out.println(res.response);
                   }else if (m.equals("s")) {
                	   Msg ss = new Msg();
                       ss.showFiles = true;
                       oos.writeObject(ss);
                       oos.flush();
                       
                       try {
                           Msg responseMsg = (Msg) oin.readObject();
                           System.out.println(responseMsg.response);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       
                   }else if (m.equals("f")) {
                	    System.out.print("검색할 파일 번호: ");
                	    int number = Integer.parseInt(kbd.nextLine());

                	    Msg searchMsg = new Msg();
                	    searchMsg.findFiles = true;
                	    searchMsg.number = number;
                	    oos.writeObject(searchMsg);
                	    oos.flush();

                	    // 서버로부터 검색 결과 수신
                	    try {
                	        Msg responseMsg = (Msg) oin.readObject();
                	        System.out.println(responseMsg.response);
                	    } catch (Exception e) {
                	        e.printStackTrace();
                	    }
                	} else if (m.equals("u")) {
                	    System.out.print("업데이트할 파일 번호 입력: ");
                	    int number = Integer.parseInt(kbd.nextLine());
                	    System.out.print("새 파일명 입력 (현재 파일명 유지 시 빈 칸): ");
                	    String fileName = kbd.nextLine();
                	    System.out.print("새 작성자 입력 (현재 작성자 유지 시 빈 칸): ");
                	    String who = kbd.nextLine();
                	    System.out.print("새 내용 입력 (현재 내용 유지 시 빈 칸): ");
                	    String content = kbd.nextLine();


                	    Msg u = new Msg();
                	    u.updateFiles = true;
                	    u.number = number;
                	    u.fileName = fileName.isEmpty() ? null : fileName;
//                	    u.fileData = fileData.length > 0 ? fileData : null;
                	    u.who = who.isEmpty() ? null : who;
                	    u.content = content.isEmpty() ? null : content;
                	    oos.writeObject(u);
                	    oos.flush();

                	    // 서버로부터 업데이트 결과 메시지 수신
                	    try {
                	        Msg responseMsg = (Msg) oin.readObject();
                	        System.out.println(responseMsg.response);
                	    } catch (Exception e) {
                	        e.printStackTrace();
                	    }
                	}else if (m.equals("d")) {
                	    System.out.print("삭제할 파일 번호 입력: ");
                	    int number = Integer.parseInt(kbd.nextLine());

                	    Msg d = new Msg();
                	    d.deleteFiles = true;
                	    d.number = number;
                	    oos.writeObject(d);
                	    oos.flush();

                	    // 서버로부터 삭제 결과 메시지 수신
                	    try {
                	        Msg responseMsg = (Msg) oin.readObject();
                	        System.out.println(responseMsg.response);
                	    } catch (Exception e) {
                	        e.printStackTrace();
                	    }
                	}else if (m.equals("x")) {
                	   break;
                   }

               }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("클라이언트 종료");
    }




}

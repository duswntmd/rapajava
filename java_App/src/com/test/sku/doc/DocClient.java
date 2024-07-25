package com.test.sku.doc;

import java.io.*;
import java.net.Socket;
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

                   if (m.equals("a"))
                   {
                       System.out.print("파일명:");
                       String fileName = kbd.nextLine();
                       System.out.print("작성자:");
                       String who = kbd.nextLine();
                       System.out.print("내용 입력:");
                       String content = kbd.nextLine();

                       Msg a = new Msg();
                       if (fileName != null && !fileName.equals(""))
                       {
                           byte[] fdata = new FileIO().load(fileName);
                           if (fdata != null)
                           {
                               a.fileName = fileName;
                               a.fileData = fdata;
                           }
                           else
                           {
                               System.err.println("파일이 없습니다.");
                           }
                       }
                       a.saveFiles = true;
                       a.upload =true;
                       a.who = who;
                       a.content = content;

                       oos.writeObject(a);
                       oos.flush();

                       Msg res =(Msg)oin.readObject();
                       System.out.println(res.response);

                   }
                   else if (m.equals("s"))
                   {
                	   
                	   Msg ss = new Msg();
                       ss.showFiles = true;
                       oos.writeObject(ss);
                       oos.flush();

                       // 서버로부터 리스트 수신
                       try {
                           List<FileInfo> receivedList = (List<FileInfo>) oin.readObject();
                           System.out.println("서버에서 받은 파일 목록:");
                           for (FileInfo fileInfo : receivedList) {
                               System.out.println(fileInfo);
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                	   
//                	   try {
//               			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/test/list_fileinfo.ser"));
//               			List<FileInfo> list = (List<FileInfo>)ois.readObject();
//               			ois.close();
//               			System.out.println("\t역직렬화 후의 리스트 내용보기");
//               			for(int i=0; i<list.size(); i++) {
//               				System.out.println(list.get(i));
//               			}
//               			
//               		} catch (Exception e) {
//               			e.printStackTrace();
//               			System.err.println("파일 읽기 실패");
//               		}

                   }

               }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("클라이언트 종료");
    }




}

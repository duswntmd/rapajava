package com.test.sku.doc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DocServer {

    /* 1.DocServer
          + "서버 대기중"
          + 무한대기 accept()
          + "클라이언트 접속"
          + userWorkThread.start(); 작업을 수행해줄 쓰레드
       2.DocClient
          + 서버에 접속
          + 클라이언트 종료

       3.메뉴:업로드 a 목록 s 검색 f 수정 u 삭제 d 종료 x
          +업로드(a)
            -파일명: sample.jpg
            -파일을 메모리에 로드(byte[]) ,파일명(fname) ,--> ChatMsg
            -서버에 전송 (upload ture,fname="sample.txt",fdata=fileData)
            -서버쪽에서 병행처리해줘야함

      4.파일 업로드 성공시마다 속성들을 파일에 저장한다
        +번호, 파일명, 작성자, 파일크기, 날짜, 설먕
        +FileInfo 클래스
        +List<FileInfo> 구조로 파일에 저장(직렬화)
        +파일명: list_fileinfo.ser
      5.if(cm.upload)
        { //파일 수신/ 서비스시스템에 저장 }
        else if(cm.list)
            list_fileinfo.ser을 로드하여 fileList 뱐수에 할당
    */


    public  static  void main(String[] args){

        try {
            ServerSocket ss =new ServerSocket(1234);

            while (true)
            {
                System.out.println("서버 대기중");
                Socket s= ss.accept();
                System.out.println("클라이언트 접속");
                new UserWorkThread(s).start();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

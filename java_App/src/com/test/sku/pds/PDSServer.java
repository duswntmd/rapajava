package com.test.sku.pds;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PDSServer {

    public  static  void main(String[] args){

        try {
            ServerSocket ss =new ServerSocket(1234);

            while (true)
            {
                System.out.println("서버 대기중");
                Socket s= ss.accept();
                System.out.println("클라이언트 접속");
                new ServerThread(s).start();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


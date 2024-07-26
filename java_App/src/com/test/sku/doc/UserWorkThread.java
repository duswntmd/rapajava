package com.test.sku.doc;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserWorkThread extends  Thread
{

    private  Socket s;



    public UserWorkThread(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {

        try {
            OutputStream out =s.getOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(out);

                Msg msg = new Msg();
                msg.menu = "업로드(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x)";
                oos.writeObject(msg);
                oos.flush();

                InputStream in = s.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);

            while (true)
            {
                Msg clientMsg = (Msg) oin.readObject();
                // 클라이언트의 메뉴 선택에 따른 처리

                if (clientMsg.upload)
                {
                    if (clientMsg.fileData.length > 0)
                    {
                        boolean saved = new FileIO().download(clientMsg.fileName, clientMsg.fileData);
                        if (saved)
                        {
                            Msg n = new Msg();
                            n.response = "파일 저장 성공";
                            oos.writeObject(n);
                            oos.flush();

                        }
                    }
                    
                }
                
                if (clientMsg.saveFiles) {
                    FileService fileService = new FileService();
                    boolean result = fileService.saveFile(clientMsg);

                    Msg n = new Msg();
                    if (result) {
                        n.response = "리스트 직렬화하여 파일에 저장 성공";
                    } else {
                        n.response = "리스트 직렬화하여 파일에 저장 실패";
                    }
                    oos.writeObject(n);
                    oos.flush();
                }
                
                if (clientMsg.showFiles) {
                    FileService fileService = new FileService();
                    String fileList = fileService.showFiles();

                    // 클라이언트에게 결과 전송
                    try {
                        Msg responseMsg = new Msg();
                        responseMsg.response = fileList;
                        oos.writeObject(responseMsg);
                        oos.flush();
                        System.out.println("리스트를 클라이언트에 전송했습니다.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("클라이언트로 데이터 전송 실패");
                    }
                }
                
                if (clientMsg.findFiles) {
                    FileService fileService = new FileService();
                    String foundFiles = fileService.findFiles(clientMsg.number);

                    // 클라이언트에게 검색 결과 전송
                    try {
                        Msg responseMsg = new Msg();
                        responseMsg.response = foundFiles;
                        oos.writeObject(responseMsg);
                        oos.flush();
                        System.out.println("검색 결과를 클라이언트에 전송했습니다.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("클라이언트로 데이터 전송 실패");
                    }
                }
                
                if (clientMsg.updateFiles) {
                    FileService fileService = new FileService();
                    String response = fileService.updateFile(clientMsg);

                    // 클라이언트에게 응답 전송
                    Msg n = new Msg();
                    n.response = response;
                    oos.writeObject(n);
                    oos.flush();
                }
                
                if (clientMsg.deleteFiles) {
                    FileService fileService = new FileService();
                    String response = fileService.deleteFile(clientMsg, oos);

                    // 클라이언트에게 응답 전송
                    Msg n = new Msg();
                    n.response = response;
                    oos.writeObject(n);
                    oos.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}


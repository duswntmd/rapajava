package com.test.sku.doc;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
                    List<FileInfo> list;

                    // 기존 리스트가 있으면 파일에서 읽어옴
                    File file = new File("C:/test/list_fileinfo.ser");
                    if (file.exists()) {
                        try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                            list = (List<FileInfo>) fileIn.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                            list = new ArrayList<>();
                        }
                    } else {
                        list = new ArrayList<>();
                    }

                    // 새로운 FileInfo 객체 추가
                    list.add(new FileInfo(clientMsg.fileName, clientMsg.who, clientMsg.content));

                    // 리스트 직렬화하여 파일에 저장
                    try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(file))) {
                        fileOut.writeObject(list);
                        fileOut.flush();
                        System.out.println("리스트 직렬화하여 파일에 저장 성공");

                        Msg n = new Msg();
                        n.response = "리스트 직렬화하여 파일에 저장 성공";
                        oos.writeObject(n);
                        oos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("리스트 직렬화하여 파일에 저장 실패");

                        Msg n = new Msg();
                        n.response = "리스트 직렬화하여 파일에 저장 실패";
                        oos.writeObject(n);
                        oos.flush();
                    }
                }
                
                if (clientMsg.showFiles) {
                	// 역직렬화된 리스트를 클라이언트에게 전송
                    File file = new File("C:/test/list_fileinfo.ser");
                    List<FileInfo> list = new ArrayList<>();

                    if (file.exists()) {
                        try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                            list = (List<FileInfo>) fileIn.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.err.println("파일 읽기 실패");
                        }
                    }

                    // 클라이언트에게 리스트 전송
                    try {
                        oos.writeObject(list);
                        oos.flush();
                        System.out.println("리스트를 클라이언트에 전송했습니다.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("클라이언트로 데이터 전송 실패");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}


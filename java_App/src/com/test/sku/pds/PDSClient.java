package com.test.sku.pds;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PDSClient {

    static Scanner kbd = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Socket s = new Socket("localhost", 1234);
            System.out.println("서버 접속");

            InputStream in = s.getInputStream();
            ObjectInputStream oin = new ObjectInputStream(in);
            PDSResponse menu = (PDSResponse) oin.readObject();

            OutputStream out = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);

            while (true) {
                System.out.println(menu.getResponse());
                String m = kbd.nextLine();

                PDSRequest request = new PDSRequest();
                request.setMenu(m);

                if (m.equals("u")) {
                    System.out.print("파일명:");
                    String fileName = kbd.nextLine();
                    System.out.print("작성자:");
                    String who = kbd.nextLine();
                    System.out.print("내용 입력:");
                    String content = kbd.nextLine();

                    if (fileName != null && !fileName.equals("")) {
                        byte[] fdata = new FileIO().load(fileName);
                        if (fdata != null) {
                            request.setFileName(fileName);
                            request.setFileData(fdata);
                        } else {
                            System.err.println("파일이 없습니다.");
                            continue;
                        }
                    }
                    request.setSaveFiles(true);
                    request.setUpload(true);
                    request.setWho(who);
                    request.setContent(content);

                    oos.writeObject(request);
                    oos.flush();

                    PDSResponse res = (PDSResponse) oin.readObject();
                    System.out.println(res.getResponse());
                } else if (m.equals("s")) {
                    request.setShowFiles(true);
                    oos.writeObject(request);
                    oos.flush();

                    try {
                        PDSResponse responseMsg = (PDSResponse) oin.readObject();
                        System.out.println(responseMsg.getResponse());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (m.equals("i")) {
                    request.setShowDetails(true);
                    oos.writeObject(request);
                    oos.flush();

                    try {
                        PDSResponse responseMsg = (PDSResponse) oin.readObject();
                        System.out.println(responseMsg.getResponse());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (m.equals("f")) {
                    System.out.print("검색할 파일 이름: ");
                    String fileName = kbd.nextLine();
                    request.setFindFiles(true);
                    request.setFileName(fileName);
                    oos.writeObject(request);
                    oos.flush();

                    // 서버로부터 검색 결과 수신
                    try {
                        PDSResponse responseMsg = (PDSResponse) oin.readObject();
                        System.out.println(responseMsg.getResponse());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (m.equals("m")) {
                    System.out.print("업데이트할 파일 번호 입력: ");
                    int number = Integer.parseInt(kbd.nextLine());

                    System.out.print("새 내용 입력 (현재 내용 유지 시 빈 칸): ");
                    String content = kbd.nextLine();
                    request.setUpdateFiles(true);
                    request.setNumber(number);
                    request.setContent(content.isEmpty() ? null : content);
                    oos.writeObject(request);
                    oos.flush();

                    // 서버로부터 업데이트 결과 메시지 수신
                    try {
                        PDSResponse responseMsg = (PDSResponse) oin.readObject();
                        System.out.println(responseMsg.getResponse());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (m.equals("d")) {
                    System.out.print("삭제할 파일 번호 입력: ");
                    int number = Integer.parseInt(kbd.nextLine());
                    request.setDeleteFiles(true);
                    request.setNumber(number);
                    oos.writeObject(request);
                    oos.flush();

                    // 서버로부터 삭제 결과 메시지 수신
                    try {
                        PDSResponse responseMsg = (PDSResponse) oin.readObject();
                        System.out.println(responseMsg.getResponse());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (m.equals("x")) {
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("클라이언트 종료");
    }

}

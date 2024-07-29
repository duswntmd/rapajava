package com.test.sku.pds;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ServerThread extends Thread {

    private Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            OutputStream out = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);

            PDSResponse response = new PDSResponse();
            response.setResponse("파일 업로드(u), 목록보기(s), 상세보기(i), 제목으로 검색(f), 수정(m), 삭제(d), 종료(x)");
            oos.writeObject(response);
            oos.flush();

            InputStream in = s.getInputStream();
            ObjectInputStream oin = new ObjectInputStream(in);

            List<PDSVO> list = loadPDSVOList();

            while (true) {
                PDSRequest clientMsg = (PDSRequest) oin.readObject();
                // 클라이언트의 메뉴 선택에 따른 처리

                if (clientMsg.isUpload()) {
                    if (clientMsg.getFileData().length > 0) {
                        boolean saved = new FileIO().download(clientMsg.getFileName(), clientMsg.getFileData());
                        if (saved) {
                            PDSResponse n = new PDSResponse();
                            n.setResponse("파일 저장 성공");
                            oos.writeObject(n);
                            oos.flush();
                        }
                    }
                }

                if (clientMsg.isSaveFiles()) {
                    // 빈 번호 찾기
                    int newNumber = 1;
                    Set<Integer> usedNumbers = list.stream()
                            .map(PDSVO::getNo)
                            .collect(Collectors.toSet());
                    while (usedNumbers.contains(newNumber)) {
                        newNumber++;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());

                    // 새로운 PDSVO 객체 추가
                    list.add(new PDSVO(newNumber, clientMsg.getFileName(), clientMsg.getWho(), clientMsg.getFileData(), date, clientMsg.getContent()));

                    // 리스트를 번호순으로 정렬
                    list.sort(Comparator.comparingInt(PDSVO::getNo));

                    // 리스트 직렬화하여 파일에 저장
                    savePDSVOList(list);

                    PDSResponse n = new PDSResponse();
                    n.setResponse("리스트 직렬화하여 파일에 저장 성공");
                    oos.writeObject(n);
                    oos.flush();
                }

                if (clientMsg.isShowFiles()) {
                    // 역직렬화된 리스트를 클라이언트에게 전송
                    StringBuilder result = new StringBuilder("서버에서 받은 파일 목록:\n");
                    for (PDSVO pdsvo : list) {
                        result.append(String.format("%d\t파일명:%s\t작성자:%s\t파일크기:%d[byte]\t날짜:%s\n",
                                pdsvo.getNo(), pdsvo.getFname(), pdsvo.getAuthor(), (pdsvo.getFlen() != null ? pdsvo.getFlen().length : 0), pdsvo.getDate()));
                    }
                    PDSResponse responseMsg = new PDSResponse();
                    responseMsg.setResponse(result.toString());
                    oos.writeObject(responseMsg);
                    oos.flush();
                    System.out.println("리스트를 클라이언트에 전송했습니다.");
                }

                if (clientMsg.isShowDetails()) {
                    // 역직렬화된 리스트를 클라이언트에게 전송
                    StringBuilder result = new StringBuilder("서버에서 받은 파일 상세 목록:\n");
                    for (PDSVO pdsvo : list) {
                        result.append(String.format("%d\t파일명:%s\t작성자:%s\t파일크기:%d[byte]\t날짜:%s\t내용:%s\n",
                                pdsvo.getNo(), pdsvo.getFname(), pdsvo.getAuthor(), (pdsvo.getFlen() != null ? pdsvo.getFlen().length : 0), pdsvo.getDate(), pdsvo.getDesc()));
                    }
                    PDSResponse responseMsg = new PDSResponse();
                    responseMsg.setResponse(result.toString());
                    oos.writeObject(responseMsg);
                    oos.flush();
                    System.out.println("상세 목록을 클라이언트에 전송했습니다.");
                }

                if (clientMsg.isFindFiles()) {
                    List<PDSVO> foundFiles = list.stream()
                            .filter(pdsvo -> pdsvo.getFname().equals(clientMsg.getFileName()))
                            .collect(Collectors.toList());
                    StringBuilder result = new StringBuilder("검색된 파일 목록:\n");
                    for (PDSVO pdsvo : foundFiles) {
                        result.append(String.format("%d\t파일명:%s\t작성자:%s\t파일크기:%d[byte]\t날짜:%s\t내용:%s\n",
                                pdsvo.getNo(), pdsvo.getFname(), pdsvo.getAuthor(), (pdsvo.getFlen() != null ? pdsvo.getFlen().length : 0), pdsvo.getDate(), pdsvo.getDesc()));
                    }
                    PDSResponse responseMsg = new PDSResponse();
                    responseMsg.setResponse(result.toString());
                    oos.writeObject(responseMsg);
                    oos.flush();
                    System.out.println("검색 결과를 클라이언트에 전송했습니다.");
                }

                if (clientMsg.isUpdateFiles()) {
                    boolean found = false;

                    // 번호로 파일 정보 업데이트
                    for (PDSVO pdsvo : list) {
                        if (pdsvo.getNo() == clientMsg.getNumber()) {
                            pdsvo.setDesc(clientMsg.getContent());
                            found = true;
                            break;
                        }
                    }

                    // 수정된 리스트를 파일에 저장
                    savePDSVOList(list);

                    PDSResponse n = new PDSResponse();
                    n.setResponse(found ? "파일 업데이트 성공" : "파일 번호를 찾을 수 없습니다");
                    oos.writeObject(n);
                    oos.flush();
                }

                if (clientMsg.isDeleteFiles()) {
                    boolean found = false;
                    String fileNameToDelete = null;

                    // 번호로 파일 정보 삭제
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getNo() == clientMsg.getNumber()) {
                            fileNameToDelete = list.get(i).getFname(); // 파일 이름 저장
                            list.remove(i);
                            found = true;
                            break;
                        }
                    }

                    // 수정된 리스트를 파일에 저장
                    savePDSVOList(list);

                    // 실제 파일 시스템에서 파일 삭제
                    if (fileNameToDelete != null) {
                        File fileToDelete = new File("C:/test/download/" + fileNameToDelete);
                        if (fileToDelete.exists() && fileToDelete.delete()) {
                            System.out.println(fileToDelete.getPath() + " 파일 삭제 성공");
                        } else {
                            System.err.println(fileToDelete.getPath() + " 파일 삭제 실패");
                        }
                    }

                    PDSResponse n = new PDSResponse();
                    n.setResponse(found ? "파일 삭제 성공" : "파일 번호를 찾을 수 없습니다");
                    oos.writeObject(n);
                    oos.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<PDSVO> loadPDSVOList() {
    	// 리스트 역직렬화
        File file = new File("C:/test/list_fileinfo.ser");
        if (file.exists()) {
            try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                return (List<PDSVO>) fileIn.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private void savePDSVOList(List<PDSVO> list) {
    	// 리스트 직렬화
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/test/list_fileinfo.ser"))) {
            fileOut.writeObject(list);
            fileOut.flush();
            System.out.println("리스트 직렬화하여 파일에 저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("리스트 직렬화하여 파일에 저장 실패");
        }
    }

}

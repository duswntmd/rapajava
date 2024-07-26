package com.test.sku.doc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DocServer {

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

//DocClient, DocServer
	/* 1. DocServer 
	 * 	  + "서버 대기중..."
	 *    + 무한 대기(accept())
	 *    + "클라이언트 접속됨"
	 *    + UserWorkThread.start();
	 * 2. DocClient
	 *    + 서버에 접속 new socket
	 *    + 클라이언트 종료
	 * 3. 업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x)
	 *    + 업로드(a)
	 *      - 파일명: sample.jpg
	 *      - 파일 메모리에 로드(byte[]), 파일명(fname) --> ChatMsg
	 *      - 서버에 전송 (upload=true, fname="sample.jpg", fdata=filedata)
	 * 4. 파일 업로드 성공시마다 속성들을 파일에 저장한다
	 *    + 번호, 파일명, 작성자, 날짜, 설명
	 *    + FileInfo 클래스
	 *    + List<FileInfo> 구조로 파일에 저장(직렬화)
	 *    + 파일명 : list_fileinfo.ser
	 * 5. if(cm.upload) {
	 * 		 //파일 수신/서버시스템에 저장
	 * 	  }else if(cm.list){
	 * 		 // list_fileinfo.ser을 로드허요 fileList 변수에 할당
	 * 	  }else if(cm.find){
	 * 		 // 클라이언트가 검색하려는 경우
	 *    }
	 * 6. 서버측 쓰레드 (UserWorkThread) 에서 메뉴를 클라이언트에게 보여주기
	 *    + cm.menu = "업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):";
	 *    + oos.writeObject(cm);
	 *    + oos.flush();
	 * 7. 이용자가 파일 업로드하는 경우
	 *    String m = kbd.nextLine().trim();
	 *    if(m.eqluals("a")) { //파일 업로드
	 *       파일명 : sample.jpg
	 *       설명   : 홍길동의 증명사진
	 *       작성자 : 홍길동
	 *       chatMsg cm = new ChatMsg();
	 *       cm.upload = true;
	 *       cm.fname = fio.load(sample.jpg);
	 *       cd.fdata = 파일 로드 후 생성된 byte[]
	 *       cm.author = 홍길동;
	 *       cm.desc = 홍길동의 증명사진
	 *       oos.writeobject(cm);
	 *       oos.flush();
	 *    }
	 * 8. 서버에 파일을 저장하는 절차
	 *   + 클라이언트가 전송한 파일을 서버측 파일 시스템에 저장한다
	 *     - FileOutputStream fout = new FileOutputStream("C:/test/upload/" + cm.fname);
	 *     - fout.write(cm.fdata);
	 *     - fout.close();
	 *   + 서버에 저장한 파일의 정보를 FileInfo 클래스의 인스턴스에 저장한다
	 *     - FileInfo fi = new FileInfo(); // 번호, 파일명, 작성자, 파일크기, 날짜, 설명
	 *     - fi.setFname(cm.fname);
	 *     - fi.setFsize(cm.fsize);
	 *     - fi.setFuid(cm.uid);
	 *     - fi.setFDate(cm.Date());
	 *     - fi.setFDesc(cm.desc);
	 *   + 직렬화된 List<FileInfo> 데이터를 역질렬화하여 List<FileInfo>의 참조를 구한다
	 *     - FileIO fio = new FileIO();
	 *     - List<FileInfo> list = (List<FileInfo>)fio.deserilize(); //역직렬화
	 *     - list.get(list.size()-1).getFno + 1;
	 *     - fi.setFNO(nextFno);
	 *   + List<FileInfo>의 참조를 사용하여 새로 추가된 파일의 정보를 리스트에 저장한다
	 *     - list.add(fi);
	 *   + List<fileInfo>가 직렬화되어 저장되는 파일의 이름은 list_fileinfo.ser으로 한다
	 *   + 내용이 변경된 list르 ㄹ다시 파일에 직렬화라여 저장한다
	 *     - FileIO fio = new FileIO();
	 *     - fio.serialize(list);
	 *   + 클라이언트에서 업로드 성공 메시지를 출력한다
	 *     - ChatMsg cm = new ChatMsg();
	 *     cm.msg = "업로드 성공"
	 *     - oos.writeObject(cm);
	 *     - oos.flush();
	 */

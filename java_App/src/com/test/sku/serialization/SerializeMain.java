package com.test.sku.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SerializeMain {

	public static void main(String[] args) {
		// 문서관리 공유 시스템
		// 문서를 업로드하여 다른 이용자가 목록을 보고 골라서 파일을 다운로드할 수 있다
		// 업로드, 다운로드, 검색, 수정, 삭제, 종료
		// Socket, ServerSocket
		// 한 문서에 포함되는 속성:번호, 제목, 파일명, 날짜, 작성자, 파일크기
		// List<Document>
		// 디렉토리에 포함된 파일의 리스트 추출하는 방법
//		listForFiles();
//		searchFile();
//		listSerialization();
//		deSerialization();
//		updateList();
		update02();
		
	} // end main
	
	private static void listForFiles() {
		String path = "C:/test";
		File f = new File(path);
//		String[] files = f.list();    // 파일명만 배열로
		File[] files = f.listFiles(); // File 오브젝트배열
		for(int i=0; i<files.length; i++) {
//			System.out.println(files[i]);
			if(files[i].isDirectory()) {
				System.out.println(files[i] + "\t\t->Dir");
			}else {
				System.out.println(files[i] + "\t\t->File");
			}
		}
	}
	
	private static void searchFile() {
		String key = "sample.txt";
		String path = "C:/test";
		
		File f = new File(path);
		File[] files = f.listFiles();
		for(int i=0; i<files.length; i++) {
			if(files[i].getName().equals(key)) {
				System.out.printf("%s\t %d", files[i].getName(), files[i].length());
			}
		}
	}
	
	private static void listSerialization() {
//		List<String> names = List.of("강호동", "이수근", "정청래", "Smith", "Laura", "홍길동");
		String path = "C:/test/list_names.ser";
		List<String> names = new ArrayList<String>();
		names.add("강호동");
		names.add("이수근");
		names.add("정청래");
		names.add("Smith");
		names.add("Laura");
		names.add("홍길동");
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/test/list_names.ser"));
			oos.writeObject(names);
			oos.close();
			System.out.println("리스트 직열화 파일에 저장");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일에 저장 실패");
		}
	}
	
	private static boolean serialize(List<String> list) {
//		List<String> names = List.of("강호동", "이수근", "정청래", "Smith", "Laura", "홍길동");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/test/list_names.ser"));
			oos.writeObject(list);
			oos.close();
			System.out.println("리스트 직열화 파일에 저장");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일에 저장 실패");
		}
		return false;
	}
	
	private static boolean serialize1(List<Emp> list) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/test/list_names.ser"));
			oos.writeObject(list);
			oos.close();
			System.out.println("리스트 직열화 파일에 저장");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일에 저장 실패");
		}
		return false;
	}
	
	private static List<String> deSerialization() {
		String path = "C:/test/list_names.ser";
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			List<String> list = (List<String>)ois.readObject();
			ois.close();
			System.out.println("\t역직렬화 후의 리스트 내용보기");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일 읽기 실패");
		}
		return null;
	}
	
	private static List<Emp> deSerialization1() {
		String path = "C:/test/list_names.ser";
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			List<Emp> list = (List<Emp>)ois.readObject();
			ois.close();
			System.out.println("\t역직렬화 후의 리스트 내용보기");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일 읽기 실패");
		}
		return null;
	}
	
	private static void updateList() {
		// Simth -> James 로 변경하여 파일에 저장하고 다시 역직렬화아여 변경된 내용 확인
		List<String> list = deSerialization();
		
		if(list.contains("Smith")) {
			int idx = list.indexOf("Smith");
			list.set(idx, "James");
			
			if(serialize(list)) {
				System.out.println("수정성공");
			}else {
				System.out.println("수정실패");				
			}
		}
		deSerialization();
	}

	private static void update02() {
		List<Emp> list = new ArrayList<>();
		Emp e1 = new Emp(11, "smith", 20, 3020);
		Emp e2 = new Emp(12, "james", 10, 3300);
		Emp e3 = new Emp(13, "scott", 30, 3800);
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//james의 급여(sal) 변경하기
		Emp key = new Emp(12);
		if(list.contains(key)) {
			int idx = list.indexOf(key);
			Emp found = list.get(idx);
			found.setSal(found.getSal()+200);
		}
		
		// scott의 부서를 50번으로 변경
		key = new Emp(13);
		if(list.contains(key)) {
			int idx = list.indexOf(key);
			Emp found = list.get(idx);
			found.setDeptno(50);
		}
		
		// emp_list.ser 파일에 직렬화해보세요
		if(serialize1(list)) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");				
		}
		deSerialization1();
		
	}

}
//File f = new File("C:/test/sample.jpg");
//if(f.exists()) {
//	boolean deleted = f.delete();
//	if(deleted) {
//		System.out.println("파일 삭제 성공");
//	}
//}

// DocClient, DocServer
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
 */

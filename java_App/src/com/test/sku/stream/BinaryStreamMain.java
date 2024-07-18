package com.test.sku.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.module.FindException;
import java.util.Scanner;

public class BinaryStreamMain {
	
	static Scanner kbd = new Scanner(System.in);

	public static void main(String[] args) {
		// Text, None-Text
		/* Character Stream : 문자를 다루는 스트림
		 *   + Reader, Writer
		 * Binary Stream(Byte Stream)
		 *   + InputStream, OutputStream
		 * 변환 스트림
		 *   + InputStreamReader : 바이너리 데이터를 문자 데이터로 변환
		 *   + OutputStreamWriter : 문자 데이터를 바이너리 데이터로 변환
		 *   
		 * 네트워크 통신
		 *   + 문자 메세지 --> 바이트 데이터 --> 문자 데이터
		 */
//		 binaryTest01();
//		 binaryTest02();
//		 binaryTest03();
//		 binaryTest04();
//		 binaryTest05();
//		 conversionTest01();
//		 conversionTest02();
		 conversionTest03();
	}
	
	private static void conversionTest03() {
		
		String fpath = "C:/test/data/conv.txt";
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fpath));
			PrintWriter pw = new PrintWriter(osw);
			while(true) {
				System.out.print("문장 입력: ");        
	            String line = kbd.nextLine().trim();
				if(line.equals("")) break;
				pw.println(line);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("메서드 종료");
	}
	
	private static void conversionTest02() {
		
		String fpath = "C:/test/data/members.txt";
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fpath));
			BufferedReader br = new BufferedReader(isr);
		
		
			String line = null;
			while(true) {
				line = br.readLine();
				if(line==null) break;
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void conversionTest01() {
		// 키보드에서 문자열을 받아서 파일에 저장할 떄 문자 스트림이 아닌 바이트 스트림을 사용한다.
		
			try {
				
				String fpath = "C:/test/data/conv.txt";
				PrintWriter pw = new PrintWriter(new FileOutputStream(fpath));
				// 반복하여 키보드에서 입력을 받고 그 데이터를 파일에 누적한다
				// 이용자가 그냥 엔터를 치면 입력 완료로 간주하고 그간 누적된 텍스트 파일을 읽어서
				// 화면에 표시한다.
				while(true) {
					System.out.print("문장 입력: ");        
		            String line = kbd.nextLine().trim();
					if(line.equals("")) break;
					pw.println(line);
				}
				pw.close();
				
				BufferedReader br = new BufferedReader(new FileReader(fpath));
				
				String line = null;
				while(true) {
					line = br.readLine();
					if(line==null) break;
					System.out.println(line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("메서드 종료");
	}
	
	private static void binaryTest05() {
        String imgPath = "C:/test/data/img/sample.jpg";
        String imgDest = "C:/test/data/img/sample_cpy3.jpg";
        // 이미지를 반복하여 읽어서 읽어서 메모리에 데이터를 누적한다
        // ByteArray
        try {
            
            FileInputStream fin = new FileInputStream(imgPath);
            FileOutputStream fout = new FileOutputStream(imgDest);
//            ByteArrayInputStream bin = new ByteArrayInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buf = new byte[256];
            
            while(true) {
            	int cnt = fin.read(buf);
            	if(cnt==-1) break;
            	bout.write(buf,0,cnt);
            }
            
            byte[] img = bout.toByteArray();
            fout.write(img);
            
            bout.close();
            fin.close();
            fout.close();

            System.out.println("이미지 복사 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void binaryTest04() {
        String imgPath = "C:/test/data/img/sample.jpg";
        String imgDest = "C:/test/data/img/sample_cpy2.jpg";

        try {
            
            FileInputStream fin = new FileInputStream(imgPath);
            FileOutputStream fout = new FileOutputStream(imgDest);
            
            byte[] buf = new byte[256];
            
            while(true) {
            	int cnt = fin.read(buf);
            	if(cnt==-1) break;
            	fout.write(buf,0,cnt);
            }
            
            fin.close();
            fout.close();

            System.out.println("이미지 복사 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void binaryTest03() {
        String imgPath = "C:/test/data/img/sample.jpg";

        try {
            
            FileInputStream fin = new FileInputStream(imgPath);
            byte[] imgData = fin.readAllBytes();
            fin.close();

            String imgDest = "C:/test/data/img/sample_cpy.jpg";
            FileOutputStream fout = new FileOutputStream(imgDest);
            fout.write(imgData);
            fout.close();

            System.out.println("이미지 복사 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void binaryTest02() {
		String imgPath = "C:/test/data/img/sample.jpg";
		// byte(1), short(2), int(4), long(8)
		try {
			
			FileInputStream fin = new FileInputStream(imgPath);
			byte[] imgData = fin.readAllBytes();
			System.out.print("이미지 읽기 완료, 이미지 파일크기:" + imgData.length);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void binaryTest01() {
		String imgPath = "C:/test/data/img/sample.jpg";
		// byte(1), short(2), int(4), long(8)
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			while(true) {
				int data = fin.read();
				if(data==-1) break;
				System.out.print(data+",");
			}
			System.out.println("이미지 읽기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

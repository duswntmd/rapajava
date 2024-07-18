package com.test.sku.err;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.test.sku.stream.User;


public class ExceptionMain {

	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) { //throws Exception 메인에서 만큼은 예외처리 해줘야함
		// 에러, 예외
		// Error :
		// Exception : Mild Error
		// 소프트웨어 에러(실행 중 에러)
		// 비정상 종료
//		error01();
//		error02();
//		error03();
//		error04();
//		try {
//			error05();
//		} catch (PasswordException e) {
//			System.err.println(e.getMessage());
//		}
		try {
			error06();
		} catch (UserLoginException ex) {
				System.err.println(ex.getMessage());	
		}
		
	}
	
	// users.txt 파일에 아이디, 암호를 5개 저장하고
			// 키보드에서 로그인할 떄 로그인에 실패할 경우 UserLoginExeption이 발생하도록
			// 하고 발생한 예외를 처리하여 이용자에게 적절한 메세지를 표시하여 다시 로그인
			// 할 수 있도록 반복 로그인할 수 있도록 작성해보세요.
	private static void error06() throws UserLoginException {
      
        while (true) {
            System.out.print("id pass: ");
            
            String uid = kbd.next().trim();
            String pwd = kbd.next().trim();
            kbd.nextLine();
            User user = new User(uid, pwd);

            List<User> list = getUsers();

            boolean ok = list.contains(user);
            if (ok) {
                System.out.println("로그인 성공");
                break;
            } else {
                try {
                    throw new UserLoginException("아이디와 비밀번호가 틀렸습니다.");
                } catch (UserLoginException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("메서드 종료");
    }
	
	private static List<User> getUsers() {
		String fpath = "C:/test/data/users.txt";
        List<User> list = new ArrayList<User>();
        try  {
	        BufferedReader br = new BufferedReader(new FileReader(fpath));
	        String line = null;
            while ((line = br.readLine()) != null) {
                    list.add(new User(line));
            }
            br.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	private static void error05() throws PasswordException {
		
		System.out.print("id pass");
		
		String uid = kbd.next();
		String pwd = kbd.nextLine();
		
		if(pwd.length()<5) {
			throw new PasswordException("암호는 5자 이상이어야 합니다.");
		}
		System.out.println("메서드 종료");
	}
	
	private static void error04() throws Exception {
		
		BufferedReader br = br = new BufferedReader(new FileReader("C:/test/data/abc.txt"));
		String line = null;
		while((line = br.readLine())!=null) {
				System.out.println(line);
		}
		br.close();
	}
	
	private static void error03() {
		
		// abc.txt 파일을 열고 그 안에 저장된 텍스트를 화면에 표시해보세요.	
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:/test/data/abc.txt"));
			String line = null;
			while((line = br.readLine())!=null) {
					System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("메서드 종료");
	}
	
	// 키보드에서 정수 a, b를 받아서 나눗셈하고 그 결과식을 화면에 표시한다
	// 결과식의 예시 : "4/3 = 1"
	private static void error02() {
		
		while(true) {
			System.out.print("정수 a b: ");
			
			try {
				int a = kbd.nextInt();
				int b = kbd.nextInt();    
				int c = a/b;
				String s = String.format("%d / %d = %d %n", a, b, c);
				System.out.println(s);		
				break;
			} catch(Exception ex) {
				if(ex instanceof ArithmeticException) {
					System.out.println("0으로 나누기 금지");
				}else if(ex instanceof InputMismatchException) {
					System.out.println("숫자만 입력해주세요");
				}	
			} finally {
				kbd.nextLine(); 
			}	
//			} catch (ArithmeticException ae) {
//				System.out.println("0으로 나누기 금지");		
//			} catch (InputMismatchException ime) {
//				System.out.println("숫자만 입력해주세요");
//			}
		}
		System.out.println("프로그램 종료");
		
	}
	
	private static void error01() {
		
		int a = 5;
		int b = 0;
		int c = 0;
		
		try {
			c = a/b;
			System.out.println("나눈 결과=" + c);
		} catch (ArithmeticException ae) {
//			System.err.println(ae.getMessage());
			System.out.println("0으로 수를 나눌 수는 없습니다.");		
			return;
		}
		finally { // 예외 발생과 무관하게 항상 실행되는 블럭
			System.out.println("finally 블럭");						
		}
		System.out.println("메서드 종료");
	}

}

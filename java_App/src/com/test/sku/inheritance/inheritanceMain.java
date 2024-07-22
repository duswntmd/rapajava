package com.test.sku.inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class inheritanceMain {

	static Scanner kbd = new Scanner(System.in);
	static List<Pet> animal = new ArrayList<>();
	static String menu = "추가(a) 목록(s): ";
	
	public static void main(String[] args) {
		// 상속(Inheritance), 구현(implements)
		// MyClass extends Thread { }
		// MyClass implements Runnable { }
		/* 쉽게 하위 클래스를 파생하여 사용할 수 있다
		 * 클래스간의 관계(Parent/child)를 설저할 수 있다
		 * IS-A Relationship ( A is a B )
		 * A extends B ( A는 B이다(o), B는 A이다(x))
		 * OOP 3대특징(Inheritance, Polymorphism, Encapsulation)
		 */
//		inheritanceTest01();
//		inheritanceTest02();
//		inheritanceTest03();
		inheritanceTest04();
//		inheritanceTest05();
		
//		boolean go = true;
//		while(go) {
//			String m = ShowMenu();
//			switch (m) {
//			case "x": go = false; break; 
//			case "a": add(); break; 
//			case "s": list(); break; 			
//			}
//		
//		}
//		System.out.println("프로그램 종료");
		
		// 상속성, 다형성, 은닉성
		// 다형성 : 오버라이드, 오버로드
		// 은닉성 ;
		
		/* Pet Store
		 * Cat, Dog, Snake, Hamster : pet
		 * Cat(String pattern, float age, int price, String species)
		 * Dog(float weight, float age, int price, String species)
		 * Snake(String pattern, float size, int price, String species)
		 * Hamster(float size, int price, String species)
		 * 
		 * Pet 클래스 정의해보세요
		 * Cat, Dog, Snake, Hamster 파생하여 생성
		 * 자식 클래스의 인스턴스를 생성하고 한개의 Collection에 저장
		 * Collection의 내용을 화면에 표시해보세요.
		 * 
		 * Pet 관리 시스템
		 * 추가(a) : cat(c), Dog(d), snake(s), Hamster(h)
		 * 목록(s) : 무늬 나이 가격 품종
		 * 			무늬 나이 가격 품종
		 */
	}
	
	private static void add() {
		System.out.println("cat(c), Dog(d), snake(s), Hamster(h): ");
		String af = kbd.nextLine().trim();
		
		while (true) {
		if("c".equals(af)) {
			System.out.print("가격 품종 무늬 나이: ");
			String input = kbd.nextLine();
            String[] token = input.split("\\s+");
            
            Cat c = new Cat(token);
            animal.add(c);
            System.out.println("\t\t 고양이 정보 추가 성공");
            break; 
		}
		if("d".equals(af)) {
			System.out.print("가격 품종 무게 나이: ");
			String input = kbd.nextLine();
            String[] token = input.split("\\s+");
            
            Dog d = new Dog(token);
            animal.add(d);
            System.out.println("\t\t 강아지 정보 추가 성공");
            break; 
		}
		if("s".equals(af)) {
			System.out.print("가격 품종 무늬 사이즈: ");
			String input = kbd.nextLine();
            String[] token = input.split("\\s+");
            
            Snake s = new Snake(token);
            animal.add(s);
            System.out.println("\t\t 뱀 정보 추가 성공");
            break; 
		}
		if("h".equals(af)) {
			System.out.print("가격 품종 사이즈: ");
			String input = kbd.nextLine();
            String[] token = input.split("\\s+");
            
            Hamster h = new Hamster(token);
            animal.add(h);
            System.out.println("\t\t 햄스터 정보 추가 성공");
            break; 
		}
		}
	}

	private static void list() {
		System.out.println("가격     품종     무늬     무게     사이즈   나이");
		for(int i=0; i<animal.size(); i++) {
			System.out.println(animal.get(i));
		}
		
	}

	private static String ShowMenu() {
		System.out.println();
		System.out.print(menu);
		String m = kbd.nextLine().trim();
		return m;
	}
	
//	private static void inheritanceTest05() {
//		List<Pet> animal = new ArrayList<>();
//		System.out.println("가격     품종     무늬     무게     사이즈   나이");
//		Cat cat = new Cat(10000,"빨간종", "검정줄무늬", 2.0F);
//		Dog dog = new Dog(20000,"검은종", 2.8F, 2.0F);
//		Snake snake = new Snake(30000,"푸른종", "가시무늬", 1.1F);
//		Hamster hamster = new Hamster(1000,"잡종", 0.7F);
//		animal.add(cat);
//		animal.add(dog);
//		animal.add(snake);
//		animal.add(hamster);
//		
//		for(int i=0; i<animal.size(); i++) {
//			System.out.println(animal.get(i));
//		}
//		
//	}

	private static void inheritanceTest04() {
		// 이용자가 마우스, 모니터 한꺼번에 구입한 경우
		List<Item> cart = new ArrayList<>();
		// Generics(메소드, 클래스)
		Mouse mouse = new Mouse("하이텍광마우스","Hitec", 15000, "2020-08-12", "무선");
		Monitor monitor = new Monitor("하이텍모니터","Hitec", 75000, "2020-08-12", 16.7F);
		
		cart.add(mouse);   //Item 형으로 형변환 발생(Up Casting) : 부모클래스에 선언된 변수/메소드만 사용가능
		cart.add(monitor); 
		
//		mouse.wireless
//		monitor.size
		
		for(Item item : cart) { //개선된 for루프
			if(item instanceof Mouse ) {
				Mouse ms = (Mouse) item;
				System.out.printf("%s \t %s %n", ms.name, ms.wireless);
			}else if(item instanceof Monitor) {
				Monitor mt = (Monitor) item;
				System.out.printf("%s \t %s %n", mt.name, mt.size);
			}
		}
		
		for(int i=0; i<cart.size(); i++) {
			System.out.println(cart.get(i));
		}
	}

	private static void inheritanceTest03() {
		Monitor monitor = new Monitor("하이텍모니터","Hitec", 75000, "2020-08-12", 16.7F);
		System.out.println(monitor); //mouse.toString()
	}

	private static void inheritanceTest02() {
		Mouse mouse = new Mouse("하이텍광마우스","Hitec", 15000, "2020-08-12", "무선");
		System.out.println(mouse); //mouse.toString()
	}

	private static void inheritanceTest01() {
		Item item = new Item();
		item.setName("Momory");
		item.setMade("한국 Digital");
		item.setpDate("2020-11-20");
		item.setPrice(52000);
		 
		System.out.println(item);
	}

}

package com.test.sku.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CollectionMain {

	public static void main(String[] args) {
		//Set, map
		/* List : 유순, 중복
		 * Set : 무순, 중복불허
		 * Map : key, value 쌍으로 저장하는 자료구조
		 */
		/*
		Set<String> set = new HashSet<>();
		set.add("Smith");
		set.add("James");
		set.add("Julie");
		set.add("Laura");
		set.add("James");
		
		System.out.println("원소의 갯수:" + set.size()); //4
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String value = it.next();
			System.out.println(value);			
		}
		
		// Collection은 오브젝트만 원소로 받는다
		// 그러므로 원칙적으로 기본형 데이터를 저장할 수 없다
		Set<Integer> iSet = new HashSet<>();
		Integer one = Integer.valueOf(1); // 기본형 데이터는 각 자료형의 Wrapper 오브젝트를 생성하여 컬렉션에 저장할 수 있다.
		
		iSet.add(one);
		
		iSet.add(2); // Auto-Boxing(기본형 데이터를 그대로 저장하는 것이 아니라 내부에서 Wapper 클래스를 사용하여 오브젝트를 생성하고 그 참조를 컬렉션에 저장한다.)
		
		iSet.add(Integer.valueOf(2));
		
		// 무작위 정수(1~20)를 중복되지 않게 10개를 추출하고자 한다
		Random rd = new Random();
		while (iSet.size() < 10) {  
            iSet.add(rd.nextInt(20)+1);
		}
		List<Integer> iList = new ArrayList<Integer>(iSet);
		Collections.sort(iList);//정렬해줌
		System.out.println(Arrays.toString(iList.toArray()));
			
		//중복되지 않도록 Employee 오브젝트를 저장하려고 한다.
		//Employee 오브젝트 2개를 생성할 때 사번을 동일하게 설정하여 Set에 저장해보세요
		Set<Employee> empSet = new HashSet<>();
		
		Employee emp1 = new Employee(11);
		Employee emp2 = new Employee(11);
		
		empSet.add(emp1);
		empSet.add(emp2);
		
//		List<Employee> empList = new ArrayList<Employee>(empSet);
		System.out.println("원소의 갯수:" + empSet.size()); //1
		*/
		
		mapTest();
		
	}
	
	private static void mapTest() {
		// map: key, value가 연결되어 쌍으로 저장되는 자료구조
		// key를 해싱하여 value가 저장되는 위치를 계산한다
		
		Map<String, String> map = new HashMap<>();
		map.put("smith", "010-2345-6455");
		map.put("james", "010-4684-4369");
		map.put("laura", "010-3689-3407");
		map.put("blake", "010-3854-3596");
		map.put("adam", "010-3454-8490");
		
		String phone = map.get("blake");
		System.out.println("blake의 전화:" + phone);
	}

}

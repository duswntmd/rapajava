package com.test.sku.pet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

	static String serPath = "C:/test/data/pet.ser";
	
	public static boolean addObject(PetVO pet) {
		List<PetVO> list = getList();
		list.add(pet);
		return overwrite(list);
	}
	
private static boolean overwrite(List<PetVO> list) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serPath));
			oos.writeObject(list);
			oos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static List<PetVO> getList() {
		
		try {
			File ser = new File(serPath);
			if(!ser.exists()) { //직렬화 파일이 없는 경우
				List<PetVO> list = new ArrayList<>();
				overwrite(list);
			}
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath));
			List<PetVO> list = (List<PetVO>)ois.readObject();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PetVO getfindByNo(int no) {
		List<PetVO> list = getList();
		PetVO key = new PetVO(no);
		if(list.contains(key)) {
			PetVO p = list.get(list.indexOf(key));
			return p;
		}
		return null;
	}

	public static PetVO getfindByTitle(int no) {
		return getfindByNo(no);
	}
	
public static boolean getupdate(PetVO p) {
		
		List<PetVO> list = getList();
		if(list.contains(p)) {
			PetVO found = list.get(list.indexOf(p));
			found.setWeight(p.getWeight());
			found.setPrice(p.getPrice());
			return overwrite(list);
		}
		return false;
	}

public static boolean getdelete(int no) {
	List<PetVO> list = getList();
	PetVO key = new PetVO(no);
	if(list.contains(key)) {
		list.remove(key);
		return overwrite(list);
	}
	return false;
}


}

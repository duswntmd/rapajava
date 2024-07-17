package com.test.sku.pet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.test.sku.textio.BoardVO;

public class FileIO {

	static String fpath = "C:/test/data/pet.txt";
	
	public static boolean sevePet(PetVO p) {
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			String line = String.format("%d|%s|%s|%s", 
					p.getNo(), p.getSpecies(), p.getWeight(), p.getPrice());
			pw.println(line);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<PetVO> list() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			List<PetVO> list = new ArrayList<>();
			while((line = br.readLine())!=null) {
				PetVO p = new PetVO(line);
				list.add(p);
			}
			br.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static PetVO findByNo(int no) {
		
		List<PetVO> list = list();
		PetVO key = new PetVO(no);
		if(list.contains(key)) {
			PetVO p = list.get(list.indexOf(key));
			return p;
		}
		return null;
	}

	public static PetVO findByTitle(int no) {
		return findByNo(no);
	}

	public static boolean update(PetVO p) {
		
		List<PetVO> list = list();
		if(list.contains(p)) {
			PetVO found = list.get(list.indexOf(p));
			found.setWeight(p.getWeight());
			found.setPrice(p.getPrice());
			return overwrite(list);
		}
		return false;
	}
	
	private static boolean overwrite(List<PetVO> modified) {
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath));
			for(int i=0;i<modified.size();i++) {
				PetVO p = modified.get(i);
				String line = String.format("%d|%s|%s|%s", 
						p.getNo(), p.getSpecies(), p.getWeight(), p.getPrice());
				pw.println(line);
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean delete(int no) {
		
		List<PetVO> list = list();
		PetVO key = new PetVO(no);
		if(list.contains(key)) {
			list.remove(key);
			return overwrite(list);
		}
		return false;
	}

}

package com.test.sku.textio;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataIO 
{
	static String fpath = "C:/test/data/board.txt";
	
	public static boolean saveBoard(BoardVO b) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(b.getRegDate());
			String line = String.format("%d|%s|%s|%s|%d|%s", 
					b.getNo(), b.getTitle(), b.getAuthor(),sDate, b.getHits(),b.getContents());
			pw.println(line);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<BoardVO> list() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<BoardVO> list = new ArrayList<>();
			while((line = br.readLine())!=null) {
				BoardVO b = new BoardVO(line);
				list.add(b);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static BoardVO findByNo(int no) {
		List<BoardVO> list = list();
		BoardVO key = new BoardVO(no);
		
		if(list.contains(key)) {
			BoardVO b = list.get(list.indexOf(key));
			increaseHit(b, list);
			return b;
		}
		return null;
	}
	
	public static BoardVO findByTitle(String title) {
		List<BoardVO> list = list();
		for(int i=0;i<list.size();i++) {
			BoardVO b = list.get(i);
			if(list.get(i).getTitle().contains(title)) {
				increaseHit(b, list);
				return b;
//				return list.get(i);
			}
		}
		return null;
	}
	
	private static boolean overwrite(List<BoardVO> modified) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<modified.size();i++) {
				BoardVO b = modified.get(i);
				String sDate = sdf.format(b.getRegDate());
				String line = String.format("%d|%s|%s|%s|%d|%s", 
						b.getNo(),b.getTitle(), b.getAuthor(),sDate,b.getHits(),b.getContents());
				pw.println(line);
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean update(BoardVO b) {
		List<BoardVO> list = list();
		if(list.contains(b)) {
			BoardVO found = list.get(list.indexOf(b));
			found.setTitle(b.getTitle());
			found.setContents(b.getContents());
			return overwrite(list);
		}
		return false;
	}

	public static boolean delete(int no) {
		List<BoardVO> list = list();
		BoardVO key = new BoardVO(no);
		if(list.contains(key)) {
			list.remove(key);
			return overwrite(list);
		}
		return false;
	}
	
	public static void increaseHit(BoardVO b, List<BoardVO> list) {
		b.setHits(b.getHits()+1);
		overwrite(list);
		
	}

}

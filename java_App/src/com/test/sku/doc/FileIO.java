package com.test.sku.doc;

import java.io.*;
import java.util.List;

public class FileIO {


	private static String srcPath = "C:/test/";
	private static String savePath = "C:/test/download/";



	public byte[] load(String fname) {
		File f = new File(srcPath + fname);
		if(!f.exists()) {
			System.err.println(f.getPath() + "파일이 없습니다");
			return null;
		}
		try {
			FileInputStream fin = new FileInputStream(f);
			byte[] buf = new byte[(int)f.length()];
			fin.read(buf);
			fin.close();
		return buf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean download(String fname, byte[] fdate) {
		try {
			FileOutputStream fout = new FileOutputStream(savePath + fname);
			
			fout.write(fdate);
			fout.close();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(String fname, byte[] newData) {
	    // 파일 경로를 생성합니다.
	    File file = new File(savePath + fname);

	    // 파일이 존재하는지 확인합니다.
	    if (file.exists()) {
	        try {
	            // 파일을 새 데이터로 덮어씌웁니다.
	            FileOutputStream fout = new FileOutputStream(file);
	            fout.write(newData);
	            fout.close();
	            System.out.println(file.getPath() + " 파일이 업데이트되었습니다.");
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(file.getPath() + " 파일 업데이트에 실패했습니다.");
	        }
	    } else {
	        System.err.println(file.getPath() + " 파일이 존재하지 않습니다.");
	    }

	    return false;
	}
	
	public boolean delete(String fname) {
	    // 파일 경로를 생성합니다.
	    File file = new File(savePath + fname);

	    // 파일이 존재하는지 확인합니다.
	    if (file.exists()) {
	        // 파일을 삭제합니다.
	        if (file.delete()) {
	            System.out.println(file.getPath() + " 파일이 삭제되었습니다.");
	            return true;
	        } else {
	            System.err.println(file.getPath() + " 파일 삭제에 실패했습니다.");
	        }
	    } else {
	        System.err.println(file.getPath() + " 파일이 존재하지 않습니다.");
	    }

	    return false;
	}

}

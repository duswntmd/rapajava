package com.test.sku.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
	
}

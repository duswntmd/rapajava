package com.test.sku.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
	
	private static String srcPath = "C:/test/";
	private static String savePath = "C:/test/download/";
	private static final String FILE_NAME = "list_fileinfo.ser";
	
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
	
	// 파일에 FileInfo 리스트를 저장하는 메서드
    public static void saveFileInfoList(List<FileInfo> fileInfoList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(fileInfoList);
            oos.flush();
            System.out.println("파일 정보가 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 FileInfo 리스트를 읽어오는 메서드
    @SuppressWarnings("unchecked")
    public static List<FileInfo> loadFileInfoList() {
        List<FileInfo> fileInfoList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            fileInfoList = (List<FileInfo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fileInfoList;
    }
	
}

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


    public boolean Serialization(List<FileInfo> file)
    {
        try {

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("/Users/jeongjaeho/Desktop/0722/Doc/Files/list_fileinfo.ser"));
            oos.writeObject(file);
            oos.flush();
            oos.close();
           return true;
        } catch (Exception e) {
            e.printStackTrace();

           return false;
        }


    }

    public static List<FileInfo> deserialization(){

        String path ="/Users/jeongjaeho/Desktop/0722/Doc/Files/files.ser";

        try{
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(path));
            List<FileInfo> list =(List<FileInfo>)ois.readObject();
            ois.close();

            /*
            System.out.println("\t역직렬화 후의 리스트 내용보기");
            for(FileInfo f :list){
                System.out.println(f);
            }
            */
            return list;
        }catch (Exception e){
            System.err.println("파일 읽기 실패");
            e.printStackTrace();
        }
            return null;

    }


}

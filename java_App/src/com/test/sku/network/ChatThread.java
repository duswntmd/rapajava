package com.test.sku.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.imageio.spi.ImageOutputStreamSpi;

public class ChatThread extends Thread{
	
	static Scanner kbd = new Scanner(System.in);
	
	private ObjectInputStream oin;
	private ObjectOutputStream oos;

	public ChatThread() {}
	
    public ChatThread(ObjectInputStream oin, ObjectOutputStream oos) {
        this.oin = oin;
        this.oos = oos;
        
        ChatMsg cm3 = new ChatMsg("서버", "클라이언트", "로그인 성공");
        try {
			oos.writeObject(cm3);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	@Override
	public void run() {
		try {
            while (true) {
                ChatMsg cm3 = (ChatMsg)oin.readObject();
                oos.writeObject(cm3);
    			oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
		System.err.println("ChatThread dead");
	}
}

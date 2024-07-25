package com.test.sku.network;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatThread extends Thread{
	
	static Scanner kbd = new Scanner(System.in);
	
	private String userid;
	private Socket s;
	private ObjectInputStream oin;
	private ObjectOutputStream oos;
    
	static Map<String, ObjectOutputStream> user = new HashMap<>();
	static Map<FileInfo, ObjectOutputStream> file = new HashMap<>();
	
	public ChatThread() {}
	 
    public ChatThread(String uid, Socket s, ObjectInputStream oin, ObjectOutputStream oos) {
    	this.userid = uid;
    	this.s = s;
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
    
    public ChatThread( Socket s, ObjectInputStream oin, ObjectOutputStream oos) {
    	this.s = s;
        this.oin = oin;
        this.oos = oos;
              
    }
	
	@Override
	public void run() {
		try {
            while (true) {
                ChatMsg cm = (ChatMsg)oin.readObject(); // 클라이언트가 종료하면 SocketException발생
                if(cm.isSecret) {
                	user.get(cm.to).writeObject(cm);
                	user.get(cm.to).flush();
                	continue;
                }
                if(cm.upload) {
                	user.get(cm.to).writeObject(cm);
                	user.get(cm.to).flush();
                	continue;
                }
                // 접속한 모든 이용자에게 메세지를 전달한다
                Set<String> idSet = ChatThread.user.keySet();
                Iterator <String> idIter = idSet.iterator();
                ObjectOutputStream userOut = null;
                String userid = null;
                while(idIter.hasNext()) {
                	userid = idIter.next();
                	userOut = user.get(userid);
                	userOut.writeObject(cm);
                	userOut.flush();
                }
            }
        } catch (Exception e) {
        	InetAddress ia = s.getInetAddress();
        	System.err.println(ia + "이용자 나감");
        	//user 맴에서 퇴장한 이용자의 정보를 삭제한다
        	user.remove(userid);
        } 
		System.err.println("ChatThread dead");
	}
}

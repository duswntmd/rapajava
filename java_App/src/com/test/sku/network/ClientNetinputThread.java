package com.test.sku.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

public class ClientNetinputThread extends Thread{
	
	private ObjectInputStream oin;
	
	public ClientNetinputThread(ObjectInputStream oin) {
    	
        this.oin = oin;
         
	}
	
	@Override
	public void run() {
		try {
			ChatMsg cm = null;
            while (true) {
            	cm = (ChatMsg)oin.readObject();
				System.out.println("["+cm.uid+"]" + cm.msg);
                }
        } catch (Exception e) {
        	e.printStackTrace();
        } 
		System.err.println("ClientNetinputThread dead");
	}
}

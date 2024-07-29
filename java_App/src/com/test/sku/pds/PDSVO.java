package com.test.sku.pds;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.xml.crypto.Data;

public class PDSVO implements Serializable {
	private static final long serialVersionUID = 2650061043323032753L;

    int no ;
    String fname;
    String author ;
    byte[] flen;
    String date;
    String desc;

    public PDSVO(int no, String fname, String author, byte[] flen,String date, String desc) {
    	this.no = no;
        this.fname = fname;
        this.author = author;
        this.flen = flen;
        this.date = date;
        this.desc = desc;
    }

    public String toString() {
        return String.format("%d\t파일명:%s\t작성자:%s\t파일크기:%d[byte]\t날짜:%s\t내용:%s",
                no, fname, author, (flen != null ? flen.length : 0), date, desc);
    }

    public String toStringWithoutDesc() {
        return String.format("%d\t파일명:%s\t작성자:%s\t파일크기:%d[byte]\t날짜:%s",
                no, fname, author, (flen != null ? flen.length : 0), date);
    }

    public PDSVO(){}

	public PDSVO(int no, String desc) {
		this.no = no;
		this.desc = desc;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public byte[] getFlen() {
		return flen;
	}

	public void setFlen(byte[] flen) {
		this.flen = flen;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	};

    
    
}

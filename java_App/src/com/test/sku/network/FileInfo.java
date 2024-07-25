package com.test.sku.network;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FileInfo implements Serializable{
	int no;
	String fname;
	String author;
	Date date;
	String explanation;
	boolean upload;
	byte[] fdata;
	
	public FileInfo() {
		super();
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(date, explanation, fname, no, author);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileInfo other = (FileInfo) obj;
		return Objects.equals(date, other.date) && Objects.equals(explanation, other.explanation)
				&& Objects.equals(fname, other.fname) && no == other.no && Objects.equals(author, other.author);
	}



	public FileInfo(int no, String ffname, String author, Date date, String explanation) {
		super();
		this.no = no;
		this.fname = ffname;
		this.author = author;
		this.date = date;
		this.explanation = explanation;
	}
	
	public FileInfo(int no, String ffname, String author, String explanation) {
		super();
		this.no = no;
		this.fname = ffname;
		this.author = author;
		this.explanation = explanation;
	}



	@Override
	public String toString() {
		return "FileInfo [no=" + no + ", ffname=" + fname + ", pp=" + author + ", date=" + date + ", explanation="
				+ explanation + "]";
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFfname() {
		return fname;
	}

	public void setFfname(String ffname) {
		this.fname = ffname;
	}

	public String getPp() {
		return author;
	}

	public void setPp(String pp) {
		this.author = pp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
}

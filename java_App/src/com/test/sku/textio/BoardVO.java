package com.test.sku.textio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO 
{
	private int no;
	private String title;
	private String author;
	private Date regDate;
	private int hits;
	private String contents;
	
	public BoardVO() {}

	public BoardVO(int no) {
		setNo(no);
	}
	
	public BoardVO(String line) 
	{
		String[] token = line.split("\\|");
		setNo(Integer.parseInt(token[0]));
		setTitle(token[1]);
		setAuthor(token[2]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			setRegDate(sdf.parse(token[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setHits(Integer.parseInt(token[4]));
		setContents(token[5]);
	}

	@Override
	public boolean equals(Object obj) {
		BoardVO other = (BoardVO) obj;
		return this.getNo()==other.getNo();
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sdf.format(regDate);
		String s = String.format("%d\t%-22s\t%s\t%s\t%d", 
									no,title,author,sdate, hits);
		return s;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}

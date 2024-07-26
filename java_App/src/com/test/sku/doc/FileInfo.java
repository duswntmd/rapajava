package com.test.sku.doc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.xml.crypto.Data;

public class FileInfo implements Serializable {

    int number ;
    String fileName;
    byte[] fileData;
    String who ;
    String content;
    String writeDate;

    public FileInfo(int number, String fileName, byte[] fileData, String who, String content, String writeDate) {
    	this.number = number;
        this.fileName = fileName;
        this.fileData = fileData;
        this.who = who;
        this.content = content;
        this.writeDate = writeDate;
    }

    @Override
    public String toString() {
        return String.format("%d\t파일명:%s\t파일크기:%d[byte]\t작성자:%s\t내용:%s\t날짜:%s",
        		number,
                fileName,
                (fileData != null ? fileData.length : 0),
                who,
                content,
                writeDate);
    }

    public void FileIO(){};


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

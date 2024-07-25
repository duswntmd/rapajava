package com.test.sku.doc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class FileInfo implements Serializable {

    int number ;

    String fileName;

    byte[] fileData;

    String who ;

    String writeDate;

    String content;

    public FileInfo(String fileName, String who, String content) {
        this.fileName = fileName;
        this.who = who;
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", who='" + who + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public FileInfo(int i, String fileName, byte[] fileData, String who, String number, String content) {

        setNumber(i);
        setFileName(fileName);
        setFileData(fileData);
        setWho(who);
        setWriteDate(number);
        setContent(content);

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

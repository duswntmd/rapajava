package com.test.sku.pds;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PDSRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    boolean deleteFiles;
    boolean updateFiles;
    boolean findFiles;
    boolean showDetails;
    boolean showFiles;
    boolean saveFiles;
    boolean upload;
    boolean delete;
    int number;
    byte[] fileData;
    String menu;
    String fileName;
    String who;
    String content;
    Date writeDate;

    public PDSRequest(){}
    
    // Getter and Setter methods
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

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

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public boolean isDeleteFiles() {
		return deleteFiles;
	}

	public void setDeleteFiles(boolean deleteFiles) {
		this.deleteFiles = deleteFiles;
	}

	public boolean isUpdateFiles() {
		return updateFiles;
	}

	public void setUpdateFiles(boolean updateFiles) {
		this.updateFiles = updateFiles;
	}

	public boolean isFindFiles() {
		return findFiles;
	}

	public void setFindFiles(boolean findFiles) {
		this.findFiles = findFiles;
	}

	public boolean isShowDetails() {
		return showDetails;
	}

	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}

	public boolean isShowFiles() {
		return showFiles;
	}

	public void setShowFiles(boolean showFiles) {
		this.showFiles = showFiles;
	}

	public boolean isSaveFiles() {
		return saveFiles;
	}

	public void setSaveFiles(boolean saveFiles) {
		this.saveFiles = saveFiles;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

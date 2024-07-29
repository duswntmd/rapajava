package com.test.sku.pds;

import java.io.Serializable;
import java.util.List;

public class PDSResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    List<PDSVO> flist;
    String response;

    public PDSResponse(){}
    
    // Getter and Setter methods
    public List<PDSVO> getFlist() {
        return flist;
    }

    public void setFlist(List<PDSVO> flist) {
        this.flist = flist;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

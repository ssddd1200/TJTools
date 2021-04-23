package com.yjs.entity.PO;

public class ReturnVO<T> {

    private String appcode;
    private String databuffer;
    private T resultlist;

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public String getDatabuffer() {
        return databuffer;
    }

    public void setDatabuffer(String databuffer) {
        this.databuffer = databuffer;
    }

    public T getResultlist() {
        return resultlist;
    }

    public void setResultlist(T resultlist) {
        this.resultlist = resultlist;
    }
}

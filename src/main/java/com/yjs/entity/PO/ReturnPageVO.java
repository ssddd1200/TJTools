package com.yjs.entity.PO;

public class ReturnPageVO<T> {

    private T resultlist;
    private String appcode;
    private String databuffer;
    private Long page;
    private Long records;
    private Long total;

    public T getResultlist() {
        return resultlist;
    }

    public void setResultlist(T resultlist) {
        this.resultlist = resultlist;
    }

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

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

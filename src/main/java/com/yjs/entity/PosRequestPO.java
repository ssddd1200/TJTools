package com.yjs.entity;

public class PosRequestPO {

    private String printName;
    private String database;
    private PosWholeTemplate template;

    public PosRequestPO() {
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public PosWholeTemplate getTemplate() {
        return template;
    }

    public void setTemplate(PosWholeTemplate template) {
        this.template = template;
    }

    public String getDatabase() {
        return database;
    }
}

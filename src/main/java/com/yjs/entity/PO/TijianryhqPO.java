package com.yjs.entity.PO;

import com.yjs.annotation.FieldSortAnnotation;

public class TijianryhqPO {

    // 参保地行政规划
    @FieldSortAnnotation(order = 1)
    private String canbaodxz;
    // 是否有社会保障卡
    @FieldSortAnnotation(order = 2)
    private String shehuibzk;
    // 社会保障卡信息
    @FieldSortAnnotation(order = 3)
    private String shehuikxx;
    // 预留1
    @FieldSortAnnotation(order = 4)
    private String yuliu1;
    // 预留2
    @FieldSortAnnotation(order = 5)
    private String yuliu2;
    // 社会保障卡卡号
    @FieldSortAnnotation(order = 6)
    private String shebubzkh;
    // 社会保障卡的识别码
    @FieldSortAnnotation(order = 7)
    private String shehuibzksbm;
    // 就医地行政区划代码
    @FieldSortAnnotation(order = 8)
    private String jiuyixzqh;

    public String buildObjectByte(){
        String buildStr = "$$"+this.canbaodxz+"~"+this.shehuibzk+"~"+this.shehuikxx+"~"+this.yuliu1+"~"+this.yuliu2
                +"~"+this.shebubzkh+"~"+this.shehuibzksbm+"~"+this.jiuyixzqh+"$$";
        return buildStr;
    }

    public String getCanbaodxz() {
        return canbaodxz;
    }

    public void setCanbaodxz(String canbaodxz) {
        this.canbaodxz = canbaodxz;
    }

    public String getShehuibzk() {
        return shehuibzk;
    }

    public void setShehuibzk(String shehuibzk) {
        this.shehuibzk = shehuibzk;
    }

    public String getShehuikxx() {
        return shehuikxx;
    }

    public void setShehuikxx(String shehuikxx) {
        this.shehuikxx = shehuikxx;
    }

    public String getYuliu1() {
        return yuliu1;
    }

    public void setYuliu1(String yuliu1) {
        this.yuliu1 = yuliu1;
    }

    public String getYuliu2() {
        return yuliu2;
    }

    public void setYuliu2(String yuliu2) {
        this.yuliu2 = yuliu2;
    }

    public String getShebubzkh() {
        return shebubzkh;
    }

    public void setShebubzkh(String shebubzkh) {
        this.shebubzkh = shebubzkh;
    }

    public String getShehuibzksbm() {
        return shehuibzksbm;
    }

    public void setShehuibzksbm(String shehuibzksbm) {
        this.shehuibzksbm = shehuibzksbm;
    }

    public String getJiuyixzqh() {
        return jiuyixzqh;
    }

    public void setJiuyixzqh(String jiuyixzqh) {
        this.jiuyixzqh = jiuyixzqh;
    }

}

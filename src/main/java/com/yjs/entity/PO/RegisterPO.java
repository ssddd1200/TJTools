package com.yjs.entity.PO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class RegisterPO {

    @NotEmpty(message = "统筹区代码不能为空")
    @Length(max = 6, message = "tongchouqbh过长")
    private String tongchouqbh;
    private String shebaok;
    private String shebaokxx;
    private String yuliu1;
    private String yuliu2;
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 20, message = "xingming过长")
    private String xingming;
    @NotEmpty(message = "身份证不能为空")
    @Length(max = 18, message = "shenfenzh过长")
    private String shenfenzh;
    private String neibugh;
    @NotEmpty(message = "联系电话不能为空")
    @Length(max = 20, message = "lianxidh过长")
    private String lianxidh;
    @NotEmpty(message = "申请日期不能为空")
    @Length(max = 10, message = "shenqingrq过长")
    private String shenqingrq;
    private String qitasm;

    public String getTongchouqbh() {
        return tongchouqbh;
    }

    public void setTongchouqbh(String tongchouqbh) {
        this.tongchouqbh = tongchouqbh;
    }

    public String getShebaok() {
        return shebaok;
    }

    public void setShebaok(String shebaok) {
        this.shebaok = shebaok;
    }

    public String getShebaokxx() {
        return shebaokxx;
    }

    public void setShebaokxx(String shebaokxx) {
        this.shebaokxx = shebaokxx;
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

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getShenfenzh() {
        return shenfenzh;
    }

    public void setShenfenzh(String shenfenzh) {
        this.shenfenzh = shenfenzh;
    }

    public String getNeibugh() {
        return neibugh;
    }

    public void setNeibugh(String neibugh) {
        this.neibugh = neibugh;
    }

    public String getLianxidh() {
        return lianxidh;
    }

    public void setLianxidh(String lianxidh) {
        this.lianxidh = lianxidh;
    }

    public String getShenqingrq() {
        return shenqingrq;
    }

    public void setShenqingrq(String shenqingrq) {
        this.shenqingrq = shenqingrq;
    }

    public String getQitasm() {
        return qitasm;
    }

    public void setQitasm(String qitasm) {
        this.qitasm = qitasm;
    }
}

package com.yjs.entity.PO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class LogOffPO {

    // 统筹区代码
    @NotEmpty(message = "统筹区代码不为空")
    @Length(max = 6, message = "字段tongchouqdm过长")
    private String tongchouqdm;
    // 是否有社会保障卡
    private String shebaok;
    // 社会保障卡信息
    private String shebaokxx;
    // 预留1
    private String yuliu1;
    // 预留2
    private String yuliu2;
    // 操作员账号
    @NotEmpty(message = "操作员账号不能为空")
    @Length(max = 20, message = "字段caozuoyzh过长")
    private String caozuoyzh;
    // 注销原因
    @NotEmpty(message = "注销原因不能为空")
    @Length(max = 100, message = "字段zhuxiaoyy过长")
    private String zhuxiaoyy;
    // 注销人
    @NotEmpty(message = "注销人不能为空")
    @Length(max = 20, message = "字段zhuxiaor过长")
    private String zhuxiaor;
    // 注销时间
    @NotEmpty(message = "注销时间不能为空")
    @Length(max = 10, message = "注销时间")
    private String zhuxiaosj;

    public String getTongchouqdm() {
        return tongchouqdm;
    }

    public void setTongchouqdm(String tongchouqdm) {
        this.tongchouqdm = tongchouqdm;
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

    public String getCaozuoyzh() {
        return caozuoyzh;
    }

    public void setCaozuoyzh(String caozuoyzh) {
        this.caozuoyzh = caozuoyzh;
    }

    public String getZhuxiaoyy() {
        return zhuxiaoyy;
    }

    public void setZhuxiaoyy(String zhuxiaoyy) {
        this.zhuxiaoyy = zhuxiaoyy;
    }

    public String getZhuxiaor() {
        return zhuxiaor;
    }

    public void setZhuxiaor(String zhuxiaor) {
        this.zhuxiaor = zhuxiaor;
    }

    public String getZhuxiaosj() {
        return zhuxiaosj;
    }

    public void setZhuxiaosj(String zhuxiaosj) {
        this.zhuxiaosj = zhuxiaosj;
    }
}

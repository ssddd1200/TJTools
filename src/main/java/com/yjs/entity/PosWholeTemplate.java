package com.yjs.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class PosWholeTemplate {

    @JSONField(ordinal = 0)
    private int dx;
    @JSONField(ordinal = 1)
    private int dy;
    @JSONField(ordinal = 2)
    private int width;
    @JSONField(ordinal = 3)
    private int height;
    @JSONField(ordinal = 4)
    private List<PosSingleTemplate> singles;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public List<PosSingleTemplate> getSingles() {
        return singles;
    }

    public void setSingles(List<PosSingleTemplate> singles) {
        this.singles = singles;
    }
}

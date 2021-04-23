package com.yjs.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class PosSingleTemplate {

    @JSONField(ordinal = 0)
    private String type;
    @JSONField(ordinal = 1)
    private String align;
    @JSONField(ordinal = 2)
    private int width;
    @JSONField(ordinal = 3)
    private int height;
    @JSONField(ordinal = 4)
    private int x;
    @JSONField(ordinal = 5)
    private int y;
    @JSONField(ordinal = 6)
    private int bold;
    @JSONField(ordinal = 7)
    private String fontName;
    @JSONField(ordinal = 8)
    private int fontSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBold() {
        return bold;
    }

    public void setBold(int bold) {
        this.bold = bold;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}

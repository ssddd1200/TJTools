package com.yjs.panel;

import javax.swing.*;
import java.awt.*;

public class TipPanel extends JDialog {

    private static Dimension dim;
    private static Insets insets;
    private int x;
    private int y;
    private int width;
    private int height;

    public TipPanel(int width, int height){

        this.width = width;
        this.height = height;
        this.dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.insets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        this.x = (int) (dim.getWidth() - width - 3);
        this.y = (int) (dim.getHeight() - insets.bottom - 1);

        init();
    }

    private void init(){
        this.setSize(width, height);
        this.setLocation(x, y);
        this.setBackground(Color.BLACK);
    }

    public void run(){
        for (int i = 0; i <= height; i+=5) {
            this.setLocation(x, y - i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Close();
    }

    public void Close(){
        x = this.getX();
        y = this.getY();
        int yBottom = (int) (dim.getHeight() - insets.bottom);
        for (int i = 0; i <= yBottom - i; i+=5) {

            setLocation(x, y + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dispose();
    }
}

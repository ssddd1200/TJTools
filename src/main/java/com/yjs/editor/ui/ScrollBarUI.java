package com.yjs.editor.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * 自定义滚动条
 */
public class ScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        // 按钮
        thumbColor = Color.gray;

        thumbHighlightColor = Color.BLUE;

        thumbDarkShadowColor = Color.BLACK;

        thumbLightShadowColor = Color.yellow;

        // 滑条
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL){
            trackColor = Color.black;

            setThumbBounds(0, 0, 3, 10);
        }
        if (scrollbar.getOrientation() == JScrollBar.HORIZONTAL){
            trackColor = Color.black;

            setThumbBounds(0, 0, 10, 3);
        }

    }

    /**
     * 滚动条宽度
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {

        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL){
            c.setPreferredSize(new Dimension(6, 0));
        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL){
            c.setPreferredSize(new Dimension(0, 6));
        }
        return super.getPreferredSize(c);
    }

    /**
     * 重绘滑块的滑动区域背景
     */
    @Override
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;

        // 背景绘画画笔
        GradientPaint gp = null;

        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL){
            gp = new GradientPaint(0, 0, Color.decode("#007C8E"), trackBounds.width, 0, Color.decode("#007C8E"));
        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL){
            gp = new GradientPaint(0, 0, Color.decode("#007C8E"),0, trackBounds.height,Color.decode("#007C8E"));
        }

        g2.setPaint(gp);

        // 填充 Track
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        // Track边框
        g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1);

        if(trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT){
            this.paintDecreaseHighlight(g);
        }
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT){
            this.paintIncreaseHighlight(g);
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // 滚动条拖动
        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(Color.decode("#00D1D9"));
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(rh);
        // 透明度
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        //圆角
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL){
            g2.fillRoundRect(0,0,40,thumbBounds.height - 1,5,5);
        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL){
            g2.fillRoundRect(0,0,thumbBounds.width - 1,40,5,5);
        }
    }

    // 滚动条上方按钮
    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        return button;
    }

    // 滚动条下方按钮
    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton jButton = new JButton();
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorder(null);
        return jButton;
    }


}

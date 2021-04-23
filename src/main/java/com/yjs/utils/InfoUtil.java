package com.yjs.utils;

import com.yjs.panel.TipPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoUtil {

    //提示框
    private TipPanel tp;
    private JPanel headPan;
    private JPanel bodyPan;
    private JPanel footPan;
    private JScrollPane scroll;
    // 栏目标题
    private JLabel headTitle;
    // 关闭按钮
    private JLabel close;
    // 消息主体
    private JTextArea infoArea;
    // foot按钮
    private JLabel label;

    private String titleStr;
    private String textStr;

    private void init(){
        // 创建一个300*180的提示框
        tp = new TipPanel(300, 180);
        headPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        bodyPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        footPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        headTitle = new JLabel(titleStr);
        close = new JLabel(" x");
        infoArea = new JTextArea(textStr);
        scroll = new JScrollPane(infoArea);
        label = new JLabel("浙江云计算技术有限公司®");

        // 设置提示框颜色、边框、宽度
        tp.getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.white));
        headTitle.setPreferredSize(new Dimension(260, 26));
        headTitle.setVerticalTextPosition(JLabel.CENTER);
        headTitle.setHorizontalTextPosition(JLabel.CENTER);
        headTitle.setFont(new Font("宋体", Font.PLAIN, 14));
        headTitle.setForeground(new Color(255, 255, 255));

        close.setPreferredSize(new Dimension(30, 20));
        close.setFont(new Font("宋体", Font.BOLD, 16));
        close.setVerticalTextPosition(JLabel.CENTER);
        close.setHorizontalTextPosition(JLabel.CENTER);
        close.setCursor(new Cursor(12));
        close.setForeground(new Color(255,255,255));
        close.setToolTipText("关闭");

        infoArea.setEditable(false);
        infoArea.setPreferredSize(new Dimension(200, 100));
        infoArea.setForeground(Color.BLACK);
        infoArea.setFont(new Font("宋体", Font.PLAIN,13));
        infoArea.setBackground(new Color(255, 255, 255));
        infoArea.setLineWrap(true);
        infoArea.setMargin(new Insets(2, 0, 0, 0));

        scroll.setPreferredSize(new Dimension(260, 100));
        scroll.setBorder(null);
        scroll.setBackground(Color.black);
        tp.setBackground(Color.white);

        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(300,20));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 20));
        label.setFont(new Font("宋体",Font.PLAIN, 10));


        headPan.add(headTitle);
        headPan.add(close);

        bodyPan.add(emptyLabel);
        bodyPan.add(infoArea);

        footPan.add(label);

        headPan.setBackground(new Color(39, 154, 177));
        bodyPan.setBackground(Color.white);
        footPan.setBackground(Color.white);

        tp.add(headPan, BorderLayout.NORTH);
        tp.add(bodyPan, BorderLayout.CENTER);
        tp.add(footPan, BorderLayout.SOUTH);
    }

    public void handle(){
        // 右上角关闭事件
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tp.Close();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                close.setBorder(BorderFactory.createLineBorder(Color.white));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.setBorder(null);
            }
        });
    }

    public void show(String title, String info) {
        this.titleStr = " "+title;
        this.textStr = info;
        init();
        handle();
        tp.setAlwaysOnTop(true);
        tp.setUndecorated(true);
        tp.setResizable(false);
        tp.setVisible(true);
        tp.run();
    }
}

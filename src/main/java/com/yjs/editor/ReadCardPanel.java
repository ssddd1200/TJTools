package com.yjs.editor;

import com.alibaba.fastjson.JSON;
import com.yjs.dllinter.ICCInter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadCardPanel extends JFrame {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextArea area;

    public ReadCardPanel(){

        button1 = new JButton("读接触卡");
        button2 = new JButton("读非接触卡");
        button3 = new JButton("读卡社保信息");

        area = new JTextArea();

        setTitle("读卡测试功能");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBackground(Color.gray);

        // 获取屏幕大小
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screanWidth = (int) screanSize.getWidth();
        int screanHeight = (int) screanSize.getHeight();
        setBounds((screanWidth - 1000)/2, (screanHeight - 600) / 2,1000,600);

        // 布局 BorderLayout
        setLayout(new BorderLayout());

        area.setBackground(Color.white);

        button1.setFont(new Font("宋体", Font.PLAIN, 14));
        button1.setBorder(null);
        button1.setPreferredSize(new Dimension(100,35));
        button1.setBackground(new Color(39, 154, 177));
        button1.setForeground(Color.white);
        button1.setPreferredSize(new Dimension(120,30));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                byte[] resData = new byte[1024];
                int code = -100;
                code = ICCInter.IC_ReadCardInfo(resData);
                Map<String, String> retMap = new LinkedHashMap<>();
                retMap.put("code", String.valueOf(code));
                retMap.put("result", new String(resData).trim());
                area.setText(JSON.toJSONString(retMap, true));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
        });
        button2.setFont(new Font("宋体", Font.PLAIN, 14));
        button2.setBorder(null);
        button2.setPreferredSize(new Dimension(100,35));
        button2.setBackground(new Color(39, 154, 177));
        button2.setForeground(Color.white);
        button2.setPreferredSize(new Dimension(120,30));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                byte[] resData = new byte[1024];
                int code = -100;
                code = ICCInter.IC_RF_ReadCardInfo(resData);

                Map<String, String> retMap = new LinkedHashMap<>();
                retMap.put("code", String.valueOf(code));
                retMap.put("result", new String(resData).trim());
                area.setText(JSON.toJSONString(retMap, true));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
        });
        button3.setFont(new Font("宋体", Font.PLAIN, 14));
        button3.setBorder(null);
        button3.setPreferredSize(new Dimension(100,35));
        button3.setBackground(new Color(39, 154, 177));
        button3.setForeground(Color.white);
        button3.setPreferredSize(new Dimension(120,30));
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                byte[] resData = new byte[1024];
                int code = -100;
                code = ICCInter.IC_ReadFlag(resData);
                Map<String, String> retMap = new LinkedHashMap<>();
                retMap.put("code", String.valueOf(code));
                retMap.put("result", new String(resData).trim());
                area.setText(JSON.toJSONString(retMap, true));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
        });

        Panel panel = new Panel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.setPreferredSize(new Dimension(160, 50));
        panel.setBounds(20,0, 160, 50);
        // 组件之间的距离
        FlowLayout f = (FlowLayout) panel.getLayout();
        f.setVgap(15);

        add(panel, BorderLayout.EAST);
        add(area, BorderLayout.CENTER);
        //禁用窗口缩放
        setResizable(false);
        setVisible(true);
    }
}

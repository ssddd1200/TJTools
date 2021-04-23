package com.yjs.editor.panel;

import com.alibaba.fastjson.JSONObject;
import com.yjs.editor.PosEditor;
import com.yjs.editor.ui.ScrollBarUI;
import com.yjs.entity.PosWholeTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 模板转化界面
 */
public class TempPanel extends JPanel {

    private JLabel label;
    private JScrollPane scroll;
    private JTextArea jsonArea;
    private JButton sure;
    // 主界面
    private PosEditor father;

    public TempPanel(PosEditor pos){
        father = pos;
        label = new JLabel("POS打印模板字符串", JLabel.CENTER);
        jsonArea = new JTextArea();
        scroll = new JScrollPane();
        sure = new JButton("生成模板");

        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.white);
        setLayout(null);

        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setOpaque(true);
        label.setBackground(new Color(39, 154, 177));
        label.setForeground(Color.white);

        jsonArea.setFont(new Font("宋体", Font.PLAIN, 14));
        jsonArea.setLineWrap(true);

        sure.setFont(new Font("宋体",Font.PLAIN, 14));
        sure.setBorder(null);
        sure.setBackground(new Color(39, 154, 177));
        sure.setForeground(Color.white);
        sure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PosWholeTemplate whole = JSONObject.parseObject(jsonArea.getText(), PosWholeTemplate.class);
                father.buildCompont(whole);
            }
        });

        scroll.setPreferredSize(new Dimension(280, 280));
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        scroll.setViewportView(jsonArea);
        scroll.getVerticalScrollBar().setUI(new ScrollBarUI());

        label.setBounds(0,0, 300, 30);
        scroll.setBounds(10, 40, 280, 540);
        sure.setBounds(200, 590, 80, 30);
        add(label);
        add(scroll);
        add(sure);
    }

    public void setJson(PosWholeTemplate template){
        // 第二个参数是表示是否美化json串
        String data = JSONObject.toJSONString(template,true);
        this.jsonArea.setText(data);

        setVisible(true);
    }
}

package com.yjs.editor;

import com.sun.jna.ptr.IntByReference;
import com.yjs.dllinter.TERMB;
import com.yjs.entity.IDCardInfo;
import com.yjs.utils.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class ReadIDCardPanel extends JFrame {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel txtLab;
    private JTextArea resultArea;

    private JLabel label;
    private JTextField field;
    private JLabel label2;
    private JTextField field2;
    private JLabel label3;
    private JTextField field3;
    private JLabel label4;
    private JTextField field4;
    private JLabel label5;
    private JTextField field5;
    private JLabel label6;
    private JTextField field6;
    private JLabel label7;
    private JTextField field7;
    private JLabel label8;
    private JTextField field8;
    private JLabel imgLabel;

    private IDCardInfo info;

    public ReadIDCardPanel(){

        Font myFont = new Font("宋体", Font.PLAIN, 14);

        button1 = new JButton("连接");
        button2 = new JButton("关闭");
        button3 = new JButton("读卡");
        txtLab = new JLabel("操作结果");
        resultArea = new JTextArea();

        label = new JLabel("姓名");
        field = new JTextField();
        label2 = new JLabel("性别");
        field2 = new JTextField();
        label3 = new JLabel("民族");
        field3 = new JTextField();
        label4 = new JLabel("出生日期");
        field4 = new JTextField();
        label5 = new JLabel("住址");
        field5 = new JTextField();
        label6 = new JLabel("身份证号");
        field6 = new JTextField();
        label7 = new JLabel("发证机构");
        field7 = new JTextField();
        label8 = new JLabel("有效期");
        field8 = new JTextField();
        imgLabel = new JLabel();

        info = new IDCardInfo();


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

        button1.setFont(myFont);
        button1.setBorder(null);
        button1.setPreferredSize(new Dimension(100,35));
        button1.setBackground(new Color(39, 154, 177));
        button1.setForeground(Color.white);
        button1.setPreferredSize(new Dimension(120,30));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int code = TERMB.CVR_InitComm(1001);
                if (code == 1){
                    resultArea.setText("读卡设备连接成功");
                }else if(code == 2){
                    resultArea.setText("端口打开失败");
                }else if(code == -1){
                    resultArea.setText("未知错误");
                }else if(code == -2){
                    resultArea.setText("动态库加载失败");
                }
            }
        });

        button2.setFont(myFont);
        button2.setBorder(null);
        button2.setPreferredSize(new Dimension(100,35));
        button2.setBackground(new Color(39, 154, 177));
        button2.setForeground(Color.white);
        button2.setPreferredSize(new Dimension(120,30));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int code = TERMB.CVR_CloseComm();
                if (code == 1){
                    resultArea.setText("读卡设备关闭成功");
                }else if (code == 0){
                    resultArea.setText("端口号不合法");
                }else if (code == -1){
                    resultArea.setText("端口已经关闭");
                }else if (code == -2){
                    resultArea.setText("动态库加载失败");
                }
            }
        });

        button3.setFont(myFont);
        button3.setBorder(null);
        button3.setPreferredSize(new Dimension(100,35));
        button3.setBackground(new Color(39, 154, 177));
        button3.setForeground(Color.white);
        button3.setPreferredSize(new Dimension(120,30));
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int code = TERMB.CVR_Authenticate();
                System.out.println(code);
                if (code == 1){
                    resultArea.setText("卡片认证成功");
                    int code1 = TERMB.CVR_Read_Content(1);
                    if (code1 == 1){
                        info = FileUtil.buildIDCard();
                        field.setText(info.getXingming());
                        field2.setText(info.getXingbie());
                        field3.setText(info.getMinzu());
                        field4.setText(info.getChushengrq());
                        field5.setText(info.getZhuzhi());
                        field6.setText(info.getShenfenzh());
                        field7.setText(info.getFakajg());
                        field8.setText(info.getYouxiaoq());
                        imgLabel.setIcon(new ImageIcon(info.getImagestr()));
                    }else if (code1 == 0){
                        resultArea.setText("错误，读身份证失败");
                    }else if (code == 4){
                        resultArea.setText("错误，身份证读卡器未连接");
                    }else if (code == 99) {
                        resultArea.setText("动态库未加载");
                    }
                }else if (code == 2){
                    resultArea.setText("寻卡失败");
                }else if (code == 3){
                    resultArea.setText("选卡失败");
                }else if (code == 4){
                    resultArea.setText("未连接读卡器");
                }else if (code == 0){
                    resultArea.setText("动态库未加载");
                }
            }
        });

        txtLab.setFont(myFont);
        txtLab.setPreferredSize(new Dimension(150, 20));

        resultArea.setFont(new Font("宋体", Font.PLAIN, 16));
        resultArea.setPreferredSize(new Dimension(150,340));
        resultArea.setBackground(new Color(255,255,255));
        resultArea.setLineWrap(true);

        Panel panel = new Panel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(txtLab);
        panel.add(resultArea);
        panel.setPreferredSize(new Dimension(160, 50));
        panel.setBounds(20,0, 160, 50);
        // 组件之间的距离
        FlowLayout f = (FlowLayout) panel.getLayout();
        f.setVgap(15);

        label.setFont(myFont);
        label.setBounds(40, 30, 80, 30);
        field.setFont(myFont);
        field.setBounds(120, 30, 240, 30);
        label2.setFont(myFont);
        label2.setBounds(40, 70, 80, 30);
        field2.setFont(myFont);
        field2.setBounds(120, 70, 90, 30);
        label3.setFont(myFont);
        label3.setBounds(220, 70, 60, 30);
        field3.setFont(myFont);
        field3.setBounds(270, 70, 90, 30);
        label4.setFont(myFont);
        label4.setBounds(40, 110, 80, 30);
        field4.setFont(myFont);
        field4.setBounds(120, 110, 240, 30);
        label5.setFont(myFont);
        label5.setBounds(40, 150, 80, 30);
        field5.setFont(myFont);
        field5.setBounds(120, 150, 240, 30);
        label6.setFont(myFont);
        label6.setBounds(40, 190, 80, 30);
        field6.setFont(myFont);
        field6.setBounds(120, 190, 240, 30);
        label7.setFont(myFont);
        label7.setBounds(40, 230, 80, 30);
        field7.setFont(myFont);
        field7.setBounds(120, 230, 240, 30);
        label8.setFont(myFont);
        label8.setBounds(40, 270, 80, 30);
        field8.setFont(myFont);
        field8.setBounds(120, 270, 240, 30);
        imgLabel.setBackground(new Color(255,255,255));
        imgLabel.setBounds(440, 30, 120,140);
        imgLabel.setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));


        Panel panel2 = new Panel();
        panel2.add(label);
        panel2.add(field);
        panel2.add(label2);
        panel2.add(field2);
        panel2.add(label3);
        panel2.add(field3);
        panel2.add(label4);
        panel2.add(field4);
        panel2.add(label5);
        panel2.add(field5);
        panel2.add(label6);
        panel2.add(field6);
        panel2.add(label7);
        panel2.add(field7);
        panel2.add(label8);
        panel2.add(field8);
        panel2.add(imgLabel);
        panel2.setPreferredSize(new Dimension(840, 500));
        panel2.setLayout(null);

        add(panel, BorderLayout.EAST);
        add(panel2, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                TERMB.CVR_CloseComm();
            }
        });
        //禁用窗口缩放
        setResizable(false);
        setVisible(true);
    }
}

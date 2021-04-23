package com.yjs.editor.dialog;

import com.yjs.entity.PosWholeTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 编辑区域创建弹窗
 */
public class CreateDialog extends JDialog {

    public boolean isSure = false;

    private JPanel bodyPan;
    private JPanel footPan;

    public CreateDialog(JFrame frame,int width, int height, PosWholeTemplate template){
        super(frame, "创建页面信息", true);
        bodyPan = new JPanel();
        footPan = new JPanel();

        JLabel label1 = new JLabel("纸张宽", JLabel.CENTER);
        JTextField text1 = new JTextField();
        JLabel desc1 = new JLabel("(仅支持数字，长度为4，单位:px)", JLabel.LEFT);
        JLabel label2 = new JLabel("纸张高", JLabel.CENTER);
        JTextField text2 = new JTextField();
        JLabel desc2 = new JLabel("(仅支持数字，长度为4，单位:px)", JLabel.LEFT);
        JLabel label3 = new JLabel("左偏移量", JLabel.CENTER);
        JTextField text3 = new JTextField();
        JLabel desc3 = new JLabel("(仅支持数字，长度为2)", JLabel.LEFT);
        JLabel label4 = new JLabel("上偏移量", JLabel.CENTER);
        JTextField text4 = new JTextField();
        JLabel desc4 = new JLabel("(仅支持数字，长度为2)", JLabel.LEFT);
        JButton sure = new JButton("确定");

        setTitle("创建页面信息");
        setSize(width, height);
        // 获取屏幕大小
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screanWidth = (int) screanSize.getWidth();
        int screanHeight = (int) screanSize.getHeight();
        setLocation((screanWidth - width) / 2, (screanHeight - height) / 2);

        label1.setFont(new Font("宋体", Font.PLAIN, 14));

        text1.setFont(new Font("宋体", Font.PLAIN, 14));
        text1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        text1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text1.getText().length() == 4){
                    e.consume();
                }
            }
        });
        desc1.setFont(new Font("宋体",Font.PLAIN,10));
        desc1.setForeground(Color.red);

        label2.setFont(new Font("宋体", Font.PLAIN, 14));

        text2.setFont(new Font("宋体", Font.PLAIN, 14));
        text2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        text2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text2.getText().length() == 4){
                    e.consume();
                }
            }
        });
        desc2.setFont(new Font("宋体",Font.PLAIN,10));
        desc2.setForeground(Color.red);

        label3.setFont(new Font("宋体", Font.PLAIN, 14));

        text3.setFont(new Font("宋体", Font.PLAIN, 14));
        text3.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        text3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text3.getText().length() == 2){
                    e.consume();
                }
            }
        });
        desc3.setFont(new Font("宋体",Font.PLAIN,10));
        desc3.setForeground(Color.red);

        label4.setFont(new Font("宋体", Font.PLAIN, 14));

        text4.setFont(new Font("宋体", Font.PLAIN, 14));
        text4.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        text4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text4.getText().length() == 2){
                    e.consume();
                }
            }
        });
        desc4.setFont(new Font("宋体",Font.PLAIN,10));
        desc4.setForeground(Color.red);

        sure.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        sure.setBackground(new Color(39, 154, 177));
        sure.setForeground(Color.white);
        sure.setFont(new Font("宋体", Font.PLAIN, 16));
        sure.setCursor(new Cursor(12));
        sure.setPreferredSize(new Dimension(80, 30));

        sure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 页面宽空值判断
                if (text1.getText().isEmpty()){
                    text1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
                }
                // 页面高空值判断
                if (text2.getText().isEmpty()){
                    text2.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
                }
                template.setWidth(new Integer(text1.getText()));
                template.setHeight(new Integer(text2.getText()));
                template.setDx(new Integer(text3.getText().isEmpty()?"0":text3.getText()));
                template.setDy(new Integer(text4.getText().isEmpty()?"0":text4.getText()));
                isSure = true;

                dispose();
            }
        });

        // 设置布局为空
        bodyPan.setLayout(null);
        label1.setBounds(10,10, 100, 40);
        text1.setBounds(110,10, 100, 30);
        desc1.setBounds(210,10,200,30);
        label2.setBounds(10,60, 100, 40);
        text2.setBounds(110,60, 100, 30);
        desc2.setBounds(210,60,200,30);
        label3.setBounds(10,110, 100, 40);
        text3.setBounds(110,110, 100, 30);
        desc3.setBounds(210,110,150,30);
        label4.setBounds(10,160, 100, 40);
        text4.setBounds(110,160, 100, 30);
        desc4.setBounds(210,160,150,30);

        bodyPan.add(label1);
        bodyPan.add(text1);
        bodyPan.add(desc1);
        bodyPan.add(label2);
        bodyPan.add(text2);
        bodyPan.add(desc2);
        bodyPan.add(label3);
        bodyPan.add(text3);
        bodyPan.add(desc3);
        bodyPan.add(label4);
        bodyPan.add(text4);
        bodyPan.add(desc4);

        footPan.add(sure);

        add(bodyPan, BorderLayout.CENTER);
        add(footPan, BorderLayout.SOUTH);

        // 窗体可见
        setVisible(true);
        // 不允许窗体缩放
        setResizable(false);
    }

    /**
     * 窗体关闭事件
     * @param e 窗体控件
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            isSure = false;
        }
        super.processWindowEvent(e);

    }
}

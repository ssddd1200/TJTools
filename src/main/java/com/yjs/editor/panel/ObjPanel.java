package com.yjs.editor.panel;

import com.yjs.editor.PosEditor;
import com.yjs.entity.PosSingleTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 对象选择以及对象属性展示
 */
public class ObjPanel extends JPanel {
    // 主界面对象
    private PosEditor father;
    // 已有对象选择器
    private JList<String> list;
    // 界面分隔
    private JSplitPane splitPane;
    // 上方界面
    private JPanel topPanel;
    // 下方界面
    private JPanel bottomPanel;
    // 下方界面组件
    private JLabel bottomTit;
    private JLabel label2;
    private JComboBox<String> box;
    private JLabel label3;
    private JTextField text1;
    private JLabel label4;
    private JTextField text2;
    private JLabel label5;
    private JTextField text3;
    private JLabel label6;
    private JTextField text4;
    private JLabel label7;
    private JComboBox<String> box2;
    private JLabel label8;
    private JTextField text5;
    private JLabel label9;
    private JTextField text6;
    private JButton sure;
    // 组件ID
    private String compontId;
    // 控制参数
    private boolean isCreated;

    public ObjPanel(PosEditor pos){
        father = pos;
        compontId = null;
        isCreated = false;
        setBackground(Color.white);

        topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white);
        // 分隔方向
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        // 设置上下块参数
        splitPane.setLeftComponent(topPanel);
        splitPane.setRightComponent(bottomPanel);
        // 拖动分隔条是重绘组件
        splitPane.setContinuousLayout(true);
        // 设置分隔条位置
        splitPane.setDividerLocation(240);
        splitPane.setPreferredSize(new Dimension(200, 700));
        // 列表
        list = new JList<>();
        JLabel label = new JLabel(" 项目对象");
        bottomTit = new JLabel(" 对象参数");

        PosSingleTemplate singleTemp = new PosSingleTemplate();

        label.setFont(new Font("宋体",Font.PLAIN, 16));
        label.setOpaque(true);
        label.setBackground(new Color(39, 154, 177));
        label.setForeground(Color.white);

        bottomTit.setFont(new Font("宋体", Font.PLAIN,16));
        bottomTit.setOpaque(true);
        bottomTit.setBackground(new Color(39, 154, 177));
        bottomTit.setForeground(Color.white);

        // 设置list选择参数
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 设置选择项
        list.setListData(new String[]{" 字符串"," 条形码128"," 条形码2"});
        list.setFont(new Font("宋体",Font.PLAIN, 14));
        // list每一项的高度
        list.setFixedCellHeight(30);
        // 选中项改变事件
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isCreated){
                    // 清理下方界面的组件
                    clearBottomPanel();
                    // list选中的序号
                    int indices = list.getSelectedIndex();
                    switch (indices){
                        case 0:
                            singleTemp.setType("text");
                            break;
                        case 1:
                            singleTemp.setType("code128");
                            break;
                        case 2:
                            singleTemp.setType("codebar");
                            break;
                    }
                    createBottom("", singleTemp);
                }
            }
        });

        topPanel.setLayout(null);
        label.setBounds(0, 0, 200, 30);
        list.setBounds(0,30, 200, 150);
        topPanel.add(label);
        topPanel.add(list);

        add(splitPane);
    }

    // 创建组件新增页面
    public void createBottom(String compentID,PosSingleTemplate singleTemp){
        if (compentID.isEmpty()){
            // 组件ID为空时，随机生成数
            compontId = String.valueOf((int) (Math.random()*100));
            System.out.println("组件ID" + compontId);
        }else{
            compontId = compentID;
        }
        // bottom位置组件
        label2 = new JLabel("对齐方式", JLabel.CENTER);
        box = new JComboBox(new String[]{"left", "center", "right"});
        label3 = new JLabel("宽", JLabel.CENTER);
        text1 = new JTextField();
        label4 = new JLabel("高", JLabel.CENTER);
        text2 = new JTextField();
        label5 = new JLabel("X", JLabel.CENTER);
        text3 = new JTextField();
        label6 = new JLabel("Y", JLabel.CENTER);
        text4 = new JTextField();
        label7 = new JLabel("加粗", JLabel.CENTER);
        box2 = new JComboBox(new String[]{"不加粗","加粗"});
        label8 = new JLabel("字体", JLabel.CENTER);
        text5 = new JTextField();
        label9 = new JLabel("字号", JLabel.CENTER);
        text6 = new JTextField();

        sure = new JButton("确定");

        bottomPanel.setLayout(null);
        label2.setFont(new Font("宋体",Font.PLAIN, 14));
        box.setBackground(Color.white);
        box.setFont(new Font("宋体",Font.PLAIN, 14));

        label3.setFont(new Font("宋体",Font.PLAIN,14));
        text1.setFont(new Font("宋体",Font.PLAIN,14));
        text1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text1.getText().length() == 4){
                    e.consume();
                }
            }
        });

        label4.setFont(new Font("宋体",Font.PLAIN,14));
        text2.setFont(new Font("宋体",Font.PLAIN,14));
        text2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text2.getText().length() == 4){
                    e.consume();
                }
            }
        });

        label5.setFont(new Font("宋体",Font.PLAIN, 14));
        text3.setFont(new Font("宋体",Font.PLAIN,14));
        text3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text3.getText().length() == 4){
                    e.consume();
                }
            }
        });

        label6.setFont(new Font("宋体",Font.PLAIN, 14));
        text4.setFont(new Font("宋体",Font.PLAIN,14));
        text4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text4.getText().length() == 4){
                    e.consume();
                }
            }
        });

        label7.setFont(new Font("宋体",Font.PLAIN, 14));
        box2.setBackground(Color.white);
        box2.setFont(new Font("宋体",Font.PLAIN, 14));

        label8.setFont(new Font("宋体",Font.PLAIN, 14));
        text5.setFont(new Font("宋体",Font.PLAIN,14));

        label9.setFont(new Font("宋体",Font.PLAIN, 14));
        text6.setFont(new Font("宋体",Font.PLAIN,14));
        text6.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' || e.getKeyCode() == 8 || text6.getText().length() == 2){
                    e.consume();
                }
            }
        });

        sure.setFont(new Font("宋体",Font.PLAIN, 14));
        sure.setBackground(new Color(39, 154, 177));
        sure.setForeground(Color.white);
        sure.setBorder(BorderFactory.createLineBorder(Color.white,1));
        sure.setCursor(new Cursor(12));
        sure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PosSingleTemplate s = new PosSingleTemplate();
                // 宽、高、X、Y都不能为空
                if (text1.getText().isEmpty()){
                    text1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                if (text2.getText().isEmpty()){
                    text2.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                if (text3.getText().isEmpty()){
                    text3.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                if (text4.getText().isEmpty()){
                    text4.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    return;
                }else{
                    text4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                s.setType(singleTemp.getType());
                s.setAlign(box.getSelectedItem().toString());
                s.setWidth(Integer.valueOf(text1.getText()));
                s.setHeight(Integer.valueOf(text2.getText()));
                s.setX(Integer.valueOf(text3.getText()));
                s.setY(Integer.valueOf(text4.getText()));
                s.setBold(box2.getSelectedIndex());
                s.setFontName(text5.getText());
                s.setFontSize(Integer.valueOf(text6.getText().isEmpty()?"0":text6.getText()));
                father.addSingleCompent(compontId, s);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                sure.setBorder(null);
            }
        });

        bottomTit.setBounds(0,0, 200, 30);
        label2.setBounds(0,40, 90,30);
        box.setBounds(90,40, 100,30);
        label3.setBounds(0,75, 90,30);
        text1.setBounds(90,75, 100,30);
        label4.setBounds(0,110, 90,30);
        text2.setBounds(90,110, 100,30);
        label5.setBounds(0,145, 90,30);
        text3.setBounds(90,145, 100,30);
        label6.setBounds(0,180, 90,30);
        text4.setBounds(90,180, 100,30);
        label7.setBounds(0,215, 90,30);
        box2.setBounds(90,215, 100,30);
        label8.setBounds(0,250, 90,30);
        text5.setBounds(90,250, 100,30);
        label9.setBounds(0,285, 90,30);
        text6.setBounds(90,285, 100,30);

        sure.setBounds(110,320,80,30);

        bottomPanel.add(bottomTit);
        bottomPanel.add(label2);
        bottomPanel.add(box);
        bottomPanel.add(label3);
        bottomPanel.add(text1);
        bottomPanel.add(label4);
        bottomPanel.add(text2);
        bottomPanel.add(label5);
        bottomPanel.add(text3);
        bottomPanel.add(label6);
        bottomPanel.add(text4);
        bottomPanel.add(label7);
        bottomPanel.add(box2);
        bottomPanel.add(label8);
        bottomPanel.add(text5);
        bottomPanel.add(label9);
        bottomPanel.add(text6);
        bottomPanel.add(sure);

        SwingUtilities.updateComponentTreeUI(ObjPanel.this);
        // 当传入对象不为空时，对所有参数赋值
        if (singleTemp.getAlign() != null){
            ListModel<String> m1 = box.getModel();
            for(int i = 0; i < m1.getSize();i++){
                if (m1.getElementAt(i).equals(singleTemp.getAlign())){
                    box.setSelectedIndex(i);
                    break;
                }
            }
            text1.setText(String.valueOf(singleTemp.getWidth()));
            text2.setText(String.valueOf(singleTemp.getHeight()));
            text3.setText(String.valueOf(singleTemp.getX()));
            text4.setText(String.valueOf(singleTemp.getY()));
            box2.setSelectedIndex(singleTemp.getBold());
            text5.setText(singleTemp.getFontName());
            text6.setText(String.valueOf(singleTemp.getFontSize()));
        }
    }

    /**
     * 清理下方界面中的所有组件
     */
    public void clearBottomPanel(){
        label2 = null;
        box = null;
        label3 = null;
        text1 = null;
        label4 = null;
        text2 = null;
        label5 = null;
        text3 = null;
        label6 = null;
        text4 = null;
        label7 = null;
        box2 = null;
        label8 = null;
        text5 = null;
        label9 = null;
        text6 = null;
        sure = null;
        bottomPanel.removeAll();
    }

    /**
     * 编辑区域是否创建
     */
    public void editCreated(){
        isCreated = true;
    }
}

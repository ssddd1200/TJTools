package com.yjs.editor.panel;

import com.yjs.editor.PosEditor;
import com.yjs.entity.PosSingleTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 模板编辑区域
 */
public class EditPanel extends JPanel {
    // 图片路径
    private final Path imgPath;
    // 主界面对象
    private PosEditor father;
    // 动态生成的页面区
    private JPanel showPanel;
    // 页面区中所有元素
    private Map<String, JPanel> editPanels;
    // 当前点击时间
    private long clicktime;

    public EditPanel(PosEditor pos){
        father = pos;
        editPanels = new LinkedHashMap<>();
        clicktime = 0;
        this.imgPath = Paths.get("./images").toAbsolutePath().normalize();

        setBackground(Color.GRAY);
        setLayout(null);
    }

    /**
     * 创建页面区域
     * @param width
     * @param height
     */
    public void create(int width, int height){

        showPanel = new JPanel();
        showPanel.setBounds(20, 10, width, height);
        showPanel.setBackground(Color.WHITE);
        showPanel.setLayout(null);

        add(showPanel);

        //更新页面上的组件树，以达到刷新
        SwingUtilities.updateComponentTreeUI(EditPanel.this);
    }

    /**
     * 删除动态生成的页面区域
     */
    public void removeEditPanel(){
        if (showPanel != null) {
            remove(showPanel);
            showPanel = null;
            editPanels.clear();
        }
    }

    /**
     * 向动态区域添加新增组件
     * @param compentID 组件ID
     * @param singleTemplate 组件对象
     */
    public void addCompent(String compentID, PosSingleTemplate singleTemplate){
        if (showPanel != null){
            if (editPanels.containsKey(compentID)){
                // 是否存在相同ID, 相同则删除组件
                showPanel.remove(editPanels.get(compentID));
            }
            JPanel panel = new JPanel();
            if (singleTemplate.getType().equals("text")){
                FlowLayout f = null;
                switch (singleTemplate.getAlign()){
                    case "left":
                        f = new FlowLayout(FlowLayout.LEFT);
                        break;
                    case "center":
                        f = new FlowLayout(FlowLayout.CENTER);
                        break;
                    case "right":
                        f = new FlowLayout(FlowLayout.RIGHT);
                        break;
                }
                JLabel label = new JLabel("test");
                label.setFont(new Font(singleTemplate.getFontName(),singleTemplate.getBold() == 0 ? Font.PLAIN: Font.BOLD, singleTemplate.getFontSize()));
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.CENTER);
                panel.setLayout(f);
                panel.add(label);
            }else if (singleTemplate.getType().equals("code128")){
                JLabel img1 = new JLabel(new ImageIcon(imgPath.resolve("code.jpg").toString()));
                img1.setPreferredSize(new Dimension(singleTemplate.getWidth(), singleTemplate.getHeight()));
                panel.add(img1);
            }else if (singleTemplate.getType().equals("codebar")){
                JLabel img2 = new JLabel(new ImageIcon(imgPath.resolve("bar.jpg").toString()));
                img2.setPreferredSize(new Dimension(singleTemplate.getWidth(), singleTemplate.getHeight()));
                panel.add(img2);
            }

            panel.setBounds(singleTemplate.getX(), singleTemplate.getY(), singleTemplate.getWidth(), singleTemplate.getHeight());
            // 点击事件
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    panel.setBorder(BorderFactory.createRaisedBevelBorder());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    panel.setBorder(null);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (CheckClickTime()){
                        // 双击删除
                        System.out.println("double click");
                        showPanel.remove(editPanels.get(compentID));
                        editPanels.remove(compentID);
                        father.removeCompont(compentID);
                        SwingUtilities.updateComponentTreeUI(EditPanel.this);
                    }else{
                        // 单击，显示参数
                        father.showCompentInfo(compentID);
                    }

                }
            });

            showPanel.add(panel);

            editPanels.put(compentID, panel);

            SwingUtilities.updateComponentTreeUI(EditPanel.this);
        }

    }

    /**
     * 双击判断
     * @return
     */
    private boolean CheckClickTime(){
        long nowtime = new Date().getTime();
        if ((nowtime - clicktime) < 300 ){
            clicktime = nowtime;
            return true;
        }
        clicktime = nowtime;
        return false;
    }
}

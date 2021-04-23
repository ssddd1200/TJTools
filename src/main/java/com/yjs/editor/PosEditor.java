package com.yjs.editor;

import com.yjs.editor.dialog.CreateDialog;
import com.yjs.editor.panel.EditPanel;
import com.yjs.editor.panel.ObjPanel;
import com.yjs.editor.panel.TempPanel;
import com.yjs.entity.PosSingleTemplate;
import com.yjs.entity.PosWholeTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 模板生成界面
 */
public class PosEditor extends JFrame {
    // 模板对象
    private PosWholeTemplate template;
    // 组件对象map
    private Map<String, PosSingleTemplate> stMap;

    private ObjPanel objPanel;

    private TempPanel tempPanel;

    private EditPanel editPanel;
    // 是否创建编辑页面
    private boolean isCreateEditPanel;

    public PosEditor(){
        template = new PosWholeTemplate();
        stMap = new LinkedHashMap<>();
        isCreateEditPanel = false;
        // 控件选择
        objPanel = new ObjPanel(this);

        //模板JSon
        tempPanel = new TempPanel(this);

        // 编辑区域
        editPanel = new EditPanel(this);
        setTitle("POS机打印模板编辑");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBackground(Color.gray);

        // 获取屏幕大小
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screanWidth = (int) screanSize.getWidth();
        int screanHeight = (int) screanSize.getHeight();
        setBounds((screanWidth - 1400)/2, (screanHeight - 700) / 2,1400,700);

        // 布局 BorderLayout
        setLayout(new BorderLayout());

        // 菜单 north
        JMenuBar mb = new JMenuBar();
        String menuStr[] = {"新建", "退出"};
        JMenu menu[] = new JMenu[menuStr.length];
        for (int i = 0; i <menuStr.length; i++){
            menu[i] = new JMenu(menuStr[i]);
            menu[i].setFont(new Font("宋体",Font.PLAIN, 12));
            mb.add(menu[i]);
        }
        mb.setPreferredSize(new Dimension(800,35));
        //新建
        menu[0].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isCreateEditPanel){
                    CreateDialog dialog = new CreateDialog(PosEditor.this,400, 300, template);
                    if (dialog.isSure){
                        tempPanel.setJson(template);
                        editPanel.create(template.getWidth(), template.getHeight());
                        menu[0].setEnabled(false);
                        objPanel.editCreated();
                        isCreateEditPanel = true;
                    }
                }
            }
        });
        // 退出
        menu[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        add(mb, BorderLayout.NORTH);
        add(objPanel, BorderLayout.WEST);
        add(editPanel, BorderLayout.CENTER);
        add(tempPanel, BorderLayout.EAST);

        setVisible(true);
    }

    /**
     * 新建组件
     * @param compentID
     * @param single
     */
    public void addSingleCompent(String compentID, PosSingleTemplate single){
        stMap.put(compentID, single);
        template.setSingles(new ArrayList<PosSingleTemplate>(stMap.values()));
        editPanel.addCompent(compentID, single);
        tempPanel.setJson(template);
    }

    /**
     * 组件显示信息
     * @param compentID
     */
    public void showCompentInfo(String compentID){
        objPanel.clearBottomPanel();
        objPanel.createBottom(compentID, stMap.get(compentID));
    }

    /**
     * 删除组件
     * @param compentID
     */
    public void removeCompont(String compentID){
        stMap.remove(compentID);
        template.setSingles(new ArrayList<PosSingleTemplate>(stMap.values()));
        tempPanel.setJson(template);
    }

    /**
     * json生成模板
     * @param tem
     */
    public void buildCompont(PosWholeTemplate tem){
        isCreateEditPanel = true;
        objPanel.editCreated();

        template = tem;
        stMap.clear();
        editPanel.removeEditPanel();
        editPanel.create(tem.getWidth(), tem.getHeight());
        for (int i = 0;i < tem.getSingles().size();i++){
            String compontId = String.valueOf((int) (Math.random()*100));
            stMap.put(compontId, tem.getSingles().get(i));
            editPanel.addCompent(compontId, tem.getSingles().get(i));
        }

        objPanel.clearBottomPanel();
    }

}

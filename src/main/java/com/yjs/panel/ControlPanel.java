package com.yjs.panel;

import com.yjs.dllinter.BargaingApplyV3;
import com.yjs.editor.PosEditor;
import com.yjs.editor.ReadCardPanel;
import com.yjs.editor.ReadIDCardPanel;
import com.yjs.utils.INIFileUtil;
import net.sf.image4j.codec.ico.ICODecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ControlPanel extends JFrame {

    public ControlPanel(){
    }

    public void drawTray() throws AWTException {
        //判断当前系统支不支持系统托盘功能
        if(SystemTray.isSupported()){
            // 系统托盘图标
//            URL imageUrl = this.getClass().getResource("/images/printer.png");
//            ImageIcon icon = new ImageIcon(imageUrl);
            List<BufferedImage> images = null;
            ImageIcon icon = null;
            try {
                InputStream is = this.getClass().getResource("/images/print.ico").openStream();
                images = ICODecoder.read(is);
                if (images != null){
                    icon = new ImageIcon(images.get(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 创建弹出式菜单
            PopupMenu pop = new PopupMenu();

            MenuItem menuItem = new MenuItem("退出");
            menuItem.addActionListener(new ActionListener() {
                // 单击时退出系统 并关闭资源
                @Override
                public void actionPerformed(ActionEvent e) {
                    byte[] data1 = new byte[1024];
                    byte[] retMsg = new byte[1024];

                    BargaingApplyV3.INSTANCE.f_UserBargaingClose(data1, retMsg, INIFileUtil.yibaozh);

                    System.exit(0);
                }
            });

            MenuItem menuItem1 = new MenuItem("模板编辑");
            menuItem1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PosEditor();
                }
            });

            MenuItem menuItem2 = new MenuItem("社保读卡");
            menuItem2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new ReadCardPanel();
                }
            });

            MenuItem menuItem3 = new MenuItem("身份证信息");
            menuItem3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new ReadIDCardPanel();
                }
            });

            pop.add(menuItem3);
            pop.add(menuItem2);
            pop.add(menuItem1);
            pop.add(menuItem);
            TrayIcon tray = new TrayIcon(icon.getImage(),"打印调用服务", pop);
            //自动缩放
            tray.setImageAutoSize(true);
            SystemTray systemTray = SystemTray.getSystemTray();
            systemTray.add(tray);
        }
    }
}

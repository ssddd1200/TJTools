package com.yjs.listener;

import com.yjs.panel.ControlPanel;
import com.yjs.utils.INIFileUtil;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.awt.*;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        // 在程序开始启动时，加载面板
        ControlPanel controlPanel = new ControlPanel();
        try {
            controlPanel.drawTray();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // 读取配置文件信息
        INIFileUtil.readFileInfo();
    }
}

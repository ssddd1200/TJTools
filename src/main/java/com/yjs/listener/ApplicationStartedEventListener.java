package com.yjs.listener;

import com.yjs.dllinter.BargaingApplyV3;
import com.yjs.utils.INIFileUtil;
import com.yjs.utils.InfoUtil;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        InfoUtil i = new InfoUtil();

        // 启动时初始化医保资源
        byte[] data1 = new byte[1024];
        byte[] retMsg = new byte[1024];

        int code = BargaingApplyV3.INSTANCE.f_UserBargaingInit(data1, retMsg, INIFileUtil.yibaozh);
        if (code < 0){
            i.show("错误信息", new String(retMsg).trim());
        }else{
            i.show("提示消息","本地Web服务启动成功");
            System.out.println("启动成功");
        }
    }
}

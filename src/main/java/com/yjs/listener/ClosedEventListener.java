package com.yjs.listener;

import com.yjs.dllinter.BargaingApplyV3;
import com.yjs.utils.INIFileUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        byte[] data1 = new byte[1024];
        byte[] retMsg = new byte[1024];

        BargaingApplyV3.INSTANCE.f_UserBargaingClose(data1, retMsg, INIFileUtil.yibaozh);
    }
}

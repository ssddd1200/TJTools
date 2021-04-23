package com.yjs.dllinter;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BargaingApplyV3 extends Library {

    BargaingApplyV3 INSTANCE = Native.load(System.getProperty("java.library.path")+"\\ybjy\\bargaingapplyv3_01059", BargaingApplyV3.class);

    //初始化交易
    int f_UserBargaingInit(byte[] Data1, byte[] retMsg, String Data2);

    //交易接受
    int f_UserBargaingApply(int Code, double No, String Data, byte[] retMsg, String Data2);

    //关闭交易
    int f_UserBargaingClose(byte[] Data1, byte[] retMag, String Data2);
}

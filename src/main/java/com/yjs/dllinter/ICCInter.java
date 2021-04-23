package com.yjs.dllinter;

import com.sun.jna.Native;

public class ICCInter {

    static {
        Native.register(System.getProperty("java.library.path")+"\\rc\\ICCInter_ZS_SB");
    }

    //读接触式卡信息
    public static native int IC_ReadCardInfo(byte[] OutData);

    //读非接触式卡信息
    public static native int IC_RF_ReadCardInfo(byte[] OutData);

    //读接触式卡社保卡通标志
    public static native int IC_ReadFlag(byte[] OutData);

    //读身份证信息
    public static native int IC_ReadIdCard(byte[] OutData);
}

package com.yjs.dllinter;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public class TERMB {

    static {
        Native.register(System.getProperty("java.library.path")+"\\sfz\\Termb");
    }

    // 初始化连接
    public static native int CVR_InitComm(int Port);

    // 卡认证 跟 CVR_FindCard + CVR_SelectCard 效果一样
    public static native int CVR_Authenticate();

    // 读卡操作 生成的消息在用户临时目录下%temp%下
    public static native int CVR_Read_Content(int active);

    // 读卡信息，含指纹 生成的消息在当前运行目录下 ，没有参数
    public static native int CVR_Read_FPContent();

    // 关闭连接
    public static native int CVR_CloseComm();

    // 找卡
    public static native int CVR_FindCard();

    // 选卡
    public static native int CVR_SelectCard();

    // 读取身份证信息
    // 姓名
    public static native int GetPeopleName(String strTmp, IntByReference strLen);
    public static native int GetCertType(byte[] strTmp, int[] strLen);

    // 性别
    public static native int GetPeopleSex(byte[] strTmp, int strLen);

}

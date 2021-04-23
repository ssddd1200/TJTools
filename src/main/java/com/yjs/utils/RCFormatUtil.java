package com.yjs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class RCFormatUtil {

    private static final Logger log = LoggerFactory.getLogger(RCFormatUtil.class);

    public static Map formatData(byte[] data, String... keys){
        Map<String, String> retMap = new LinkedHashMap<>();

        String retData = new String(data).trim();
        String[] d = retData.split("~");
        if (d.length != keys.length){
            log.info("数据与对象长度不符");
        }
        log.info("卡信息:"+retData);
        retMap.put("outMsg", retData);
        for (int i = 0;i<keys.length;i++){
            if (!"outMsg".equals(keys[i])){
                retMap.put(keys[i], d[i]);
            }
        }
        return retMap;
    }
}

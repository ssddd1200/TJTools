package com.yjs.controller;

import com.alibaba.fastjson.JSON;
import com.yjs.annotation.FieldSortAnnotation;
import com.yjs.dllinter.BargaingApplyV3;
import com.yjs.entity.PO.*;
import com.yjs.utils.INIFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping(value = "/yibaofw")
public class YibaofwController {

    Logger log = LoggerFactory.getLogger(YibaofwController.class);

    @Autowired
    public YibaofwController(){
//
//        Runtime.getRuntime().loadLibrary("rc/dcrf32");
//        Runtime.getRuntime().loadLibrary("rc/mt_32");
//        Runtime.getRuntime().loadLibrary("rc/libghttp");
//        Runtime.getRuntime().loadLibrary("rc/ssse32");

        Runtime.getRuntime().loadLibrary("ybjy/iccinter01059");
//        Runtime.getRuntime().loadLibrary("rc/HttpInter_ZJZS_SB");
//        Runtime.getRuntime().loadLibrary("rc/ICCDevInter_ZS_SB");
        Runtime.getRuntime().loadLibrary("ybjy/iccinter_zs_sb");

//        Runtime.getRuntime().loadLibrary("rc/SSCardDriver");
    }

    // 80号交易获取体检人员信息
    @PostMapping(value = "/getYibaory", produces = {"application/json;charset=UTF-8"})
    public String getYibaory(@RequestBody TijianryhqPO tijianryhqPO){

        Map<String, String> resMap = new LinkedHashMap<>();
        String data1 = "$$"+tijianryhqPO.getCanbaodxz()+"~"+tijianryhqPO.getShehuibzk()+"~"+tijianryhqPO.getShehuikxx()
                +"~"+tijianryhqPO.getYuliu1()+"~"+tijianryhqPO.getYuliu2()+"~"+tijianryhqPO.getShebubzkh()+"~"
                +tijianryhqPO.getShehuibzksbm()+"~"+tijianryhqPO.getJiuyixzqh()+"$$";
        byte[] retMsg = new byte[4096];

        int code = BargaingApplyV3.INSTANCE.f_UserBargaingApply(80, 0.0, data1, retMsg, INIFileUtil.yibaozh);
        resMap.put("code", String.valueOf(code));
        String resultStr = new String(retMsg).trim();
        resMap.put("outmsg", new String(retMsg).trim());
        String[] d = resultStr.split("~");
        if (code >= 0){
            resMap.put("errmsg", d[1]);
            resMap.put("kahao", d[2]);
            resMap.put("tongchouq", d[3]);
            resMap.put("xingming", d[4]);
            resMap.put("xingbie", d[5]);
            resMap.put("shehuibzh", d[6]);
            resMap.put("tijiandah", d[7]);
            resMap.put("danweimc", d[8]);
            resMap.put("tijiannf", d[9]);
            resMap.put("tijiankssj", d[10]);
            resMap.put("tijianjssj", d[11]);
            resMap.put("jiatingzz", d[12]);
            resMap.put("lianxidh", d[13]);
            resMap.put("xianzhong", d[14]);

        }else{
            resMap.put("errmsg", d[1]);
        }
        return JSON.toJSONString(resMap);
    }
    // 81号交易体检请求
    @PostMapping(value = "/getYibaojyqq", produces = {"application/json;charset=UTF-8"})
    public String getYibaojyqq(@RequestBody TijianjyqqPO tijianjyqqPO){

        Map<String, String> resMap = new LinkedHashMap<>();
        String data1 = "$$"+tijianjyqqPO.getCanbaodxz()+"~"+tijianjyqqPO.getShehuibzk()+"~"+tijianjyqqPO.getShehuikxx()
                +"~"+tijianjyqqPO.getYuliu1()+"~"+tijianjyqqPO.getYuliu2()+"~"+tijianjyqqPO.getShebubzkh()
                +"~"+tijianjyqqPO.getShehuibzksbm()+"~"+tijianjyqqPO.getJiuyixzqh()+"~"+tijianjyqqPO.getCaozuoy()+"$$";
        byte[] retMsg = new byte[1024];
        System.out.println(data1);

        int code = BargaingApplyV3.INSTANCE.f_UserBargaingApply(81, 0.0, data1, retMsg, INIFileUtil.yibaozh);
        resMap.put("code", String.valueOf(code));
        String resultStr = new String(retMsg).trim();
        resMap.put("outmsg", new String(retMsg).trim());
        String[] d = resultStr.split("~");
        if (code >= 0){
            resMap.put("errmsg", d[1]);
            resMap.put("tijianlsh", d[2]);
            resMap.put("tijianjysj", d[3]);
        }else{
            resMap.put("errmsg", d[1]);
        }

        return JSON.toJSONString(resMap);
    }

    // 82号交易体检确认
    @PostMapping(value = "/getYibaojyqr", produces = {"application/json;charset=UTF-8"})
    public String getYibaojyqr(@RequestBody TijianjyqrPO tijianjyqrPO){

        Map<String, String> resMap = new LinkedHashMap<>();
        String data1 = "$$"+tijianjyqrPO.getCanbaodxz()+"~"+tijianjyqrPO.getShehuibzk()+"~"+tijianjyqrPO.getShehuikxx()
                +"~"+tijianjyqrPO.getYuliu1()+"~"+tijianjyqrPO.getYuliu2()+"~"+tijianjyqrPO.getShebubzkh()
                +"~"+tijianjyqrPO.getShehuibzksbm()+"~"+tijianjyqrPO.getJiuyixzqh()+"~"+tijianjyqrPO.getTijianqrlx()
                +"~"+tijianjyqrPO.getTijianbh()+"$$";
        byte[] retMsg = new byte[1024];

        int code = BargaingApplyV3.INSTANCE.f_UserBargaingApply(82, 0.0, data1, retMsg, INIFileUtil.yibaozh);
        resMap.put("code", String.valueOf(code));
        String resultStr = new String(retMsg).trim();
        resMap.put("outmsg", new String(retMsg).trim());
        String[] d = resultStr.split("~");
        if (code >= 0){
            resMap.put("errmsg", d[1]);
            resMap.put("tijianqrsj", d[2]);
        }else{
            resMap.put("errmsg", d[1]);
        }

        return JSON.toJSONString(resMap);
    }
    // 83号交易体检作废
    @PostMapping(value = "/getYibaojyzf", produces = {"application/json;charset=UTF-8"})
    public String getYibaojyzf(@RequestBody TijianjyzfPO tijianjyzfPO){

        Map<String, String> resMap = new LinkedHashMap<>();
        String data1 = "$$"+tijianjyzfPO.getCanbaodxz()+"~"+tijianjyzfPO.getShehuibzk()+"~"+tijianjyzfPO.getShehuikxx()
                +"~"+tijianjyzfPO.getYuliu1()+"~"+tijianjyzfPO.getYuliu2()+"~"+tijianjyzfPO.getShebubzkh()
                +"~"+tijianjyzfPO.getShehuibzksbm()+"~"+tijianjyzfPO.getJiuyixzqh()+"~"+tijianjyzfPO.getJiaoyilsh()+"$$";
        byte[] retMsg = new byte[1024];

        int code = BargaingApplyV3.INSTANCE.f_UserBargaingApply(83, 0.0, data1, retMsg, INIFileUtil.yibaozh);
        resMap.put("code", String.valueOf(code));
        String resultStr = new String(retMsg).trim();
        resMap.put("outmsg", new String(retMsg).trim());
        String[] d = resultStr.split("~");
        if (code >= 0){
            resMap.put("errmsg", d[1]);
            resMap.put("kahao", d[2]);
            resMap.put("tongchouq", d[3]);
            resMap.put("xingming", d[4]);
            resMap.put("xingbie", d[5]);
            resMap.put("shehuibzh", d[6]);
            resMap.put("tijiandah", d[7]);
            resMap.put("danweimc", d[8]);
            resMap.put("tijiannf", d[9]);
            resMap.put("zuofeisj", d[10]);

        }else{
            resMap.put("errmsg", d[1]);
        }

        return JSON.toJSONString(resMap);
    }

    private <T> String buildInputStr(Class<T> clz, T o){
        StringBuffer sb = new StringBuffer();
        sb.append("$$");
        Field[] fs = clz.getDeclaredFields();
        List<Field> fList = getOrderFields(fs);
        for (int i = 0; i < fList.size();i++){
            fList.get(i).setAccessible(true);
            try {
                sb.append(fList.get(i).get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i != fList.size() - 1){
                sb.append("~");
            }
        }
        sb.append("$$");
        System.out.println(sb);
        return sb.toString();
    }

    private List<Field> getOrderFields(Field[] fields){
        List<Field> fieldList = new ArrayList<>();

        for (Field f:fields){
            if (f.getAnnotation(FieldSortAnnotation.class) != null){
                fieldList.add(f);
            }
        }

        fieldList.sort(
                Comparator.comparingInt(m -> m.getAnnotation(FieldSortAnnotation.class).order()
                ));

        return fieldList;
    }
}

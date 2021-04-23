package com.yjs.controller;

import com.yjs.dllinter.TERMB;
import com.yjs.entity.IDCardInfo;
import com.yjs.utils.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/device")
public class ReadIDCardController {

    public ReadIDCardController(){

        System.loadLibrary("sfz/sdtapi");
        System.loadLibrary("sfz/WltRS");
    }

    @GetMapping(value = "/getIDCard", produces = {"application/json;charset=UTF-8"})
    public Map getIDCard(){
        Map<String, Object> resultMap = new LinkedHashMap<>();
        // 读卡器初始化
        int initCode = TERMB.CVR_InitComm(1001);
        if (initCode == 1){
            // 初始化成功后操作
            int authCode = TERMB.CVR_Authenticate();
            if (authCode == 1){
                int readCode = TERMB.CVR_Read_Content(1);
                if (readCode == 1){
                    IDCardInfo info = FileUtil.buildIDCard();
                    resultMap.put("code", "0");
                    resultMap.put("outMsg", info);
                }else if (readCode == 0){
                    resultMap.put("code", "-1");
                    resultMap.put("retMsg", "错误，读身份证失败");
                }else if (readCode == 4){
                    resultMap.put("code", "-1");
                    resultMap.put("retMsg", "错误，身份证读卡器未连接");
                }else if (readCode == 99) {
                    resultMap.put("code", "-1");
                    resultMap.put("retMsg", "动态库未加载");
                }
            }else if (authCode == 2){
                resultMap.put("code", "-1");
                resultMap.put("retMsg", "寻卡失败");
            }else if (authCode == 3){
                resultMap.put("code", "-1");
                resultMap.put("retMsg", "选卡失败");
            }else if (authCode == 4){
                resultMap.put("code", "-1");
                resultMap.put("retMsg", "未连接读卡器");
            }else if (authCode == 0){
                resultMap.put("code", "-1");
                resultMap.put("retMsg", "动态库未加载");
            }
        }else if(initCode == 2){
            resultMap.put("code", "-1");
            resultMap.put("retMsg", "端口打开失败");
        }else if(initCode == -1){
            resultMap.put("code", "-1");
            resultMap.put("retMsg", "未知错误");
        }else if(initCode == -2){
            resultMap.put("code", "-1");
            resultMap.put("retMsg", "动态库加载失败");
        }

        TERMB.CVR_CloseComm();

        return resultMap;
    }
}

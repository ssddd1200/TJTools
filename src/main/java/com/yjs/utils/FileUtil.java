package com.yjs.utils;

import com.yjs.entity.IDCardInfo;
import sun.misc.BASE64Encoder;

import java.io.*;

public class FileUtil {

    public static String getTempFilePath(){
        return System.getProperty("java.io.tmpdir");
    }

    public static String getIDCardTXTPath(){
        return System.getProperty("java.io.tmpdir")+"chinaidcard\\wz.txt";
    }

    public static String getIDCardImgPath(String type){
        if("bmp".equals(type) || "jpg".equals(type)){
            return System.getProperty("java.io.tmpdir")+"chinaidcard\\xp."+type;
        }else{
            return System.getProperty("java.io.tmpdir")+"chinaidcard\\zp.bmp";
        }
    }

    public static String getImageStr(String imgPath){
        InputStream inputStream = null;
        byte[] b = null;
        try {
            inputStream = new FileInputStream(imgPath);
            b = new byte[inputStream.available()];
            inputStream.read(b);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);
    }

    public static IDCardInfo buildIDCard(){
        IDCardInfo info = new IDCardInfo();
        File file = new File(getIDCardTXTPath());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            info.setXingming(reader.readLine());
            info.setXingbie(reader.readLine());
            info.setMinzu(reader.readLine());
            info.setChushengrq(reader.readLine());
            info.setZhuzhi(reader.readLine());
            info.setShenfenzh(reader.readLine());
            info.setFakajg(reader.readLine());
            info.setYouxiaoq(reader.readLine());
            info.setImagestr(getIDCardImgPath("jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
}

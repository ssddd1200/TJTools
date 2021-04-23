package com.yjs.utils;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;

public class INIFileUtil {

    public static String yibaozh;

    public static void readFileInfo(){
        File iniFile = new File("conf/config.ini");
        Ini ini = new Ini();
        try {
            ini.load(iniFile);
            Profile.Section section = ini.get("shebaozh");
            yibaozh = section.get("value");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

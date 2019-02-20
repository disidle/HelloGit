package com.sanss.k8s_springboot.utils;

import com.sanss.k8s_springboot.module.VersionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

@Slf4j
public class Util {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static void initVersionInfos(VersionInfo current, VersionInfo all,String versionStr) {
//        String versionStr = getVersionInfo("_version.info");
        if (versionStr == null || versionStr == "") {return; }

        String[] versions = versionStr.split("\r\n# ");
        String[] curInfo = versions[0].split("\r\n");
        String[] vsInfo = curInfo[0].replace("#", "").split("  ");
        if (all != null) {
            try {
                all.version = vsInfo[0];
                all.releasedate = vsInfo[1];
                all.author = vsInfo[2];
                all.content = versionStr;
            } catch (Exception e) {
                log.error("Exception:", e);
            }
        }

        if (current != null) {
            try {
                current.version = vsInfo[0];
                current.releasedate = vsInfo[1];
                current.author = vsInfo[2];
                current.content = versions[0];
            } catch (Exception e) {
                log.error("Exception:", e);
            }
        }

    }

    public static String getVersionInfo(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*StringBuffer stringBuffer = new StringBuffer();

        try {

            InputStream inputStream = Util.class.getResourceAsStream(fileName);
            System.out.println(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return stringBuffer.toString();
    }
}

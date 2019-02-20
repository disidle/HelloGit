package com.sanss.k8s_springboot.frontend.util;

import com.sanss.k8s_springboot.module.VersionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.UUID;

/**
 * @Author : YANGXY
 * @Description :
 * @Date : Create in 14:35 2019/2/16
 * @Modified By :
 */
@Slf4j
public class VersionUtil {

    public static String getVersionInfo(String fileName) {
        /*StringBuffer stringBuffer = new StringBuffer();
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
        }*/


        StringBuffer stringBuffer = new StringBuffer();

        try {

            InputStream inputStream = VersionUtil.class.getResourceAsStream(fileName);
            System.out.println(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}

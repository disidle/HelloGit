package com.sanss.k8s_springboot.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UtilTest {

    private String fileName;

    @Test
    public void getUUID1() {
        System.out.println(Util.getUUID());
    }

    @Before
    public void initParam(){
        fileName = "test";
    }

    @Test
    public void getVersionInfo() {
//        assertEquals("",Util.getVersionInfo(fileName));
    }


    @Test
    public void initVersionInfos() {
    }
}
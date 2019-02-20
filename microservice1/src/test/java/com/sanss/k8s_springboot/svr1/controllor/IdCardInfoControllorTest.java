package com.sanss.k8s_springboot.svr1.controllor;

import com.sanss.k8s_springboot.module.HealthCheckInfo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdCardInfoControllorTest {


    @Test
    public void helthCheck() {
        IdCardInfoControllor icc = new IdCardInfoControllor();
        HealthCheckInfo hci = icc.helthCheck();
        Assert.assertNotNull(hci);
    }
}
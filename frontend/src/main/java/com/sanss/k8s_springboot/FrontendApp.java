package com.sanss.k8s_springboot;

import com.sanss.k8s_springboot.frontend.util.VersionUtil;
import com.sanss.k8s_springboot.module.BaseInfoModule;
import com.sanss.k8s_springboot.module.VersionInfo;
import com.sanss.k8s_springboot.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
public class FrontendApp {
    public final static String instanceId = Util.getUUID();
    public final static AtomicLong serviceCount = new AtomicLong(0);
    public final static String serviceName = BaseInfoModule.SERVICE_NAME_FRONTEND;

    public final static VersionInfo versionCurrent = new VersionInfo();
    public final static VersionInfo versionAll = new VersionInfo();

    public static String SERVICE1_ADDR = "";
    public static String SERVICE2_ADDR = "";
    public static String SERVICE3_ADDR = "";

    public static String IDCARD_SERVICE_URL = "http://k8s-service-svr1.ns-demo.svc.cluster.local:18001/idcard?cardno=${IDCARD}";
    public static String CONTACT_SERVICE_URL = "http://k8s-service-svr2.ns-demo.svc.cluster.local:18002/contact?cardno=${IDCARD}";
    public static String HISTORY_SERVICE_URL = "http://k8s-service-svr3.ns-demo.svc.cluster.local:18003/callhistroy?phoneno=${PHONENO}";


    public static void main( String[] args ) {
        log.info("[{}][{}] service start", serviceName, instanceId);
//        SERVICE1_ADDR = System.getProperty("service1.addr");
//        SERVICE2_ADDR = System.getProperty("service2.addr");
//        SERVICE3_ADDR = System.getProperty("service3.addr");
//
//        IDCARD_SERVICE_URL = IDCARD_SERVICE_URL.replace("{service1.addr}", SERVICE1_ADDR);
//        CONTACT_SERVICE_URL = CONTACT_SERVICE_URL.replace("{service2.addr}", SERVICE2_ADDR);
//        HISTORY_SERVICE_URL = HISTORY_SERVICE_URL.replace("{service3.addr}", SERVICE3_ADDR);

        log.info(IDCARD_SERVICE_URL);
        log.info(CONTACT_SERVICE_URL);
        log.info(HISTORY_SERVICE_URL);

        versionCurrent.instanceId = instanceId;
        versionCurrent.serviceName = BaseInfoModule.SERVICE_NAME_FRONTEND;
        versionCurrent.serviceCounter = serviceCount.get();

        log.info("test git");
        log.info("test git 1");

        versionAll.instanceId = instanceId;
        versionAll.serviceName = BaseInfoModule.SERVICE_NAME_FRONTEND;
        versionAll.serviceCounter = serviceCount.get();
//
//
//        String versionStr = VersionUtil.getVersionInfo("/_version.info");
//        Util.initVersionInfos(versionCurrent, versionAll, versionStr);
        SpringApplication.run(FrontendApp.class);
    }
}

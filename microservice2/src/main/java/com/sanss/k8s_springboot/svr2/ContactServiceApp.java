package com.sanss.k8s_springboot.svr2;

import com.sanss.k8s_springboot.module.BaseInfoModule;
import com.sanss.k8s_springboot.module.VersionInfo;
import com.sanss.k8s_springboot.svr2.util.VersionUtil;
import com.sanss.k8s_springboot.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ContactInfoResp world!
 *
 */
@Slf4j
@SpringBootApplication
public class ContactServiceApp {
    public final static String instanceId = Util.getUUID();
    public final static String serviceName = BaseInfoModule.SERVICE_NAME_CONTACT;
    public final static AtomicLong serviceCount = new AtomicLong(0);

    public final static VersionInfo versionCurrent = new VersionInfo();
    public final static VersionInfo versionAll = new VersionInfo();
    public static void main( String[] args ) {
        log.info("[{}][{}] service start", serviceName, instanceId);

        versionCurrent.instanceId = instanceId;
        versionCurrent.serviceName = BaseInfoModule.SERVICE_NAME_CONTACT;
        versionCurrent.serviceCounter = serviceCount.get();

        versionAll.instanceId = instanceId;
        versionAll.serviceName = BaseInfoModule.SERVICE_NAME_CONTACT;
        versionCurrent.serviceCounter = serviceCount.get();

//        String versionStr = VersionUtil.getVersionInfo("/_version.info");
//        Util.initVersionInfos(versionCurrent, versionAll, versionStr);
        SpringApplication.run(ContactServiceApp.class);
    }
}

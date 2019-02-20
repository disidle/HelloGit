package com.sanss.k8s_springboot.svr1;

import com.sanss.k8s_springboot.module.BaseInfoModule;
import com.sanss.k8s_springboot.module.VersionInfo;
import com.sanss.k8s_springboot.svr1.util.VersionUtil;
import com.sanss.k8s_springboot.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdcardInfoResp world!
 *
 */
@Slf4j
@SpringBootApplication
public class IdcardServiceApp {
    public final static String instanceId = com.sanss.k8s_springboot.utils.Util.getUUID();
    public final static AtomicLong serviceCount = new AtomicLong(0);
    public final static String serviceName = BaseInfoModule.SERVICE_NAME_IDCARD;

    public final static VersionInfo versionCurrent = new VersionInfo();
    public final static VersionInfo versionAll = new VersionInfo();

    public static void main( String[] args ) {
        log.info("[{}][{}] service start", serviceName, instanceId);

        versionCurrent.instanceId = instanceId;
        versionCurrent.serviceName = BaseInfoModule.SERVICE_NAME_IDCARD;
        versionCurrent.serviceCounter = serviceCount.get();

        versionAll.instanceId = instanceId;
        versionAll.serviceName = BaseInfoModule.SERVICE_NAME_IDCARD;
        versionCurrent.serviceCounter = serviceCount.get();


//        String versionStr = VersionUtil.getVersionInfo("/_version.info");
//        Util.initVersionInfos(versionCurrent, versionAll, versionStr);

        SpringApplication.run(IdcardServiceApp.class);
    }
}

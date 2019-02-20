package com.sanss.k8s_springboot.svr1.controllor;


import com.sanss.k8s_springboot.svr1.IdcardServiceApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;
import com.sanss.k8s_springboot.module.*;

@Slf4j
@RestController
public class IdCardInfoControllor {
    private static final String template = "IdcardInfoResp, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/idcard", method = RequestMethod.GET)
    public IdcardInfoResp idcardService(
            @RequestParam(value="cardno", defaultValue="310xxx1995xxxx") String cardno) {
        long count = IdcardServiceApp.serviceCount.incrementAndGet();
        log.info("idcard[{}] [{}] micro service:{}",
                IdcardServiceApp.instanceId, count, cardno);

        IdcardInfoResp card = new IdcardInfoResp();
        card.setIdCardNum(cardno);
        card.setAddress("身份证地址");
        card.setName("姓名");
        card.setValidFrom("2000.1.1");
        card.setValidTo("2099.12.31");
        card.setSex("男");


        card.setNation("汉");

        card.serviceCounter = count;
        card.instanceId = IdcardServiceApp.instanceId;
        card.serviceName = BaseInfoModule.SERVICE_NAME_IDCARD;
        return card;
    }

    @RequestMapping(value="/_status/healthz", method = RequestMethod.GET)
    public HealthCheckInfo helthCheck(){
        HealthCheckInfo info = new HealthCheckInfo();
        info.instanceId = IdcardServiceApp.instanceId;
        info.setStatus("OK");
        info.serviceCounter = IdcardServiceApp.serviceCount.get();
        info.serviceName = BaseInfoModule.SERVICE_NAME_IDCARD;
        log.info("idcard service[{}] health check,servicecount:[{}], status:[{}]",
                IdcardServiceApp.instanceId, info.serviceCounter, info.getStatus());
        return info;
    }

    @RequestMapping(value="/_version/current", method = RequestMethod.GET)
    public VersionInfo versionInfoCurrent(){
        return IdcardServiceApp.versionCurrent;
    }

    @RequestMapping(value="/_version/all", method = RequestMethod.GET)
    public VersionInfo versionInfoAll(){
        return IdcardServiceApp.versionAll;
    }


}

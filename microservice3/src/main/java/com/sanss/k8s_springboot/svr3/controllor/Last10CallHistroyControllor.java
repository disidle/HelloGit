package com.sanss.k8s_springboot.svr3.controllor;


import com.sanss.k8s_springboot.svr3.HistoryServiceApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.sanss.k8s_springboot.module.*;

@Slf4j
@RestController
public class Last10CallHistroyControllor {
    //private static final String template = "Last10CallHistroyModule, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping(value="/callhistroy", method = RequestMethod.GET)
    public CallHistoryResp historyService(
            @RequestParam(value="phonenum", defaultValue="13900001111") String phoneno) {
        long count = HistoryServiceApp.serviceCount.incrementAndGet();
        log.info("callhistory[{}] [{}] micro service:{}",
                HistoryServiceApp.instanceId, count, phoneno);

        CallHistoryResp resp = new CallHistoryResp();
        resp.instanceId = HistoryServiceApp.instanceId;
        resp.serviceCounter = count;
        resp.serviceName = HistoryServiceApp.serviceName;

        if(count%3 == 1) {
            //占用cpu
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                float j = i / 3;
                log.trace("{}", j);
            }
        }
        resp.histories = createHistory(phoneno, 5);

        return resp;
    }

    private List<CallHistroyModule> createHistory(String phoneno, int count){
        ArrayList<CallHistroyModule> histories = new ArrayList<>();
        for(int i=0;i<count;i++) {
            CallHistroyModule history = new CallHistroyModule();

            history.setDatetime("呼叫时间");
            history.setDuration(String.format("%d s", (int)(Math.random()*100)));
            if(Math.random()*2>0.5) {
                history.setCalledParty(phoneno);
                history.setCallingParty("主叫号码");
            } else {
                history.setCallingParty(phoneno);
                history.setCalledParty("被叫号码");
            }
            histories.add(history);
        }

//        history.instanceId = HistoryServiceApp.instanceId;
//        history.serviceCounter = count;
//        history.serviceName = BaseInfoModule.SERVICE_NAME_CALLHISTORY;
        return histories;
    }

    @RequestMapping(value="/_status/healthz", method = RequestMethod.GET)
    public HealthCheckInfo helthCheck(){
        HealthCheckInfo info = new HealthCheckInfo();
        info.instanceId = HistoryServiceApp.instanceId;
        info.serviceName = BaseInfoModule.SERVICE_NAME_CALLHISTORY;
        info.serviceCounter = HistoryServiceApp.serviceCount.get();
        info.setStatus("OK");

        log.info("history service[{}] health check,servicecount:[{}], status:[{}]",
                info.instanceId,
                info.serviceCounter,
                info.getStatus());
        return info;
    }


    @RequestMapping(value="/_version/current", method = RequestMethod.GET)
    public VersionInfo versionInfoCurrent(){
        return HistoryServiceApp.versionCurrent;
    }

    @RequestMapping(value="/_version/all", method = RequestMethod.GET)
    public VersionInfo versionInfoAll(){
        return HistoryServiceApp.versionAll;
    }
}

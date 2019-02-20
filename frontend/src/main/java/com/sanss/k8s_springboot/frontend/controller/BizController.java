package com.sanss.k8s_springboot.frontend.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanss.k8s_springboot.FrontendApp;
import com.sanss.k8s_springboot.module.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
class BizController {
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected ObjectMapper mapper;



    @RequestMapping(value="/frontend", method = RequestMethod.GET)
    public BizinfoResp bizService(@RequestParam(value="cardno", defaultValue="310xxx1995xxxx") String cardno) {
        long count = FrontendApp.serviceCount.incrementAndGet();
        log.info("[{}][{}] [{}] micro service: {}",
                FrontendApp.serviceName, FrontendApp.instanceId, count, cardno);

        BizinfoResp bizinfo = new BizinfoResp();
        bizinfo.instanceId = FrontendApp.instanceId;
        bizinfo.serviceCounter = FrontendApp.serviceCount.get();
        bizinfo.serviceName = FrontendApp.serviceName;

        //1、通过getForObject()调用
        IdcardInfoResp idcard = this.restTemplate.getForObject(
                FrontendApp.IDCARD_SERVICE_URL.replace("${IDCARD}", cardno), IdcardInfoResp.class);
        bizinfo.username = idcard.getName();
        bizinfo.idcardno = cardno;

        ContactInfoResp contact = this.restTemplate.getForObject(
                FrontendApp.CONTACT_SERVICE_URL.replace("${IDCARD}", bizinfo.idcardno), ContactInfoResp.class);
        bizinfo.phoneno = contact.getPhoneno();


        //List<CallHistroyModule> histories = this.restTemplate.get
        //Object obj = restTemplate.getfor
//        List<CallHistroyModule> list = this.restTemplate.getForObject(
//                HISTORY_SERVICE_URL.replace("${PHONENO}", bizinfo.phoneno), ArrayList.class);
//        if(list!=null){
//            bizinfo.history = list.get(0);
//        }


//        String resp = restTemplate.getForObject(
//                FrontendApp.HISTORY_SERVICE_URL.replace("${PHONENO}", bizinfo.phoneno),
//                String.class);
//        JavaType javaType = mapper.getTypeFactory()
//                .constructParametricType(List.class, CallHistroyModule.class);
//        try {
//            List<CallHistroyModule> list = mapper.readValue(resp, javaType);
//            if(list!=null){
//                bizinfo.history = list.get(0);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        log.info(resp);

        CallHistoryResp history = restTemplate.getForObject(
                FrontendApp.HISTORY_SERVICE_URL.replace("${PHONENO}", bizinfo.phoneno),
                CallHistoryResp.class);
        bizinfo.history = history.histories.get(0);

        return bizinfo;
    }

    @RequestMapping(value="/_status/healthz", method = RequestMethod.GET)
    public HealthCheckInfo helthCheck(){
        HealthCheckInfo info = new HealthCheckInfo();
        info.instanceId = FrontendApp.instanceId;
        info.serviceName = FrontendApp.serviceName;
        info.serviceCounter = FrontendApp.serviceCount.get();

        info.setStatus("OK");
        log.info("[{}]:[{}] health check,servicecount:[{}], status:[{}]",
                FrontendApp.serviceName,
                FrontendApp.instanceId,
                info.serviceCounter,
                info.getStatus());
        return info;
    }

    @RequestMapping(value="/_version/current", method = RequestMethod.GET)
    public VersionInfo versionInfoCurrent(){
        return FrontendApp.versionCurrent;
    }

    @RequestMapping(value="/_version/all", method = RequestMethod.GET)
    public VersionInfo versionInfoAll(){
        return FrontendApp.versionAll;
    }
}

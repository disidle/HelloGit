package com.sanss.k8s_springboot.svr2.controllor;


import com.sanss.k8s_springboot.svr2.ContactServiceApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;
import com.sanss.k8s_springboot.module.*;

@Slf4j
@RestController
public class ContactInfoControllor {
    //private static final String template = "ContactInfoResp, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public ContactInfoResp contactService(@RequestParam(value="cardno", defaultValue="310xxx1995xxxxYYYY") String cardno) {
        long count = ContactServiceApp.serviceCount.incrementAndGet();
        log.info("contact[{}] [{}] info micro service:{}", ContactServiceApp.instanceId, count, cardno);

        ContactInfoResp contact = new ContactInfoResp();
        contact.setIdCardNo(cardno);
        contact.setEmail("11@22.com");
        contact.setName("姓名");
        contact.setPhoneno("13900001111");
        contact.setFamilyAddr("福山路380号");
        contact.instanceId = ContactServiceApp.instanceId;
        contact.serviceCounter = count;
        contact.serviceName = BaseInfoModule.SERVICE_NAME_CONTACT;
        return contact;
    }

    @RequestMapping(value="/_status/healthz", method = RequestMethod.GET)
    public HealthCheckInfo helthCheck(){
        HealthCheckInfo info = new HealthCheckInfo();
        info.instanceId = ContactServiceApp.instanceId;
        info.setStatus("OK");
        info.serviceCounter = ContactServiceApp.serviceCount.get();
        info.serviceName = BaseInfoModule.SERVICE_NAME_CONTACT;

        log.info("contact service[{}] health check,servicecount:[{}], status:[{}]",
                ContactServiceApp.instanceId, info.serviceCounter, info.getStatus());
        return info;
    }


    @RequestMapping(value="/_version/current", method = RequestMethod.GET)
    public VersionInfo versionInfoCurrent(){
        return ContactServiceApp.versionCurrent;
    }

    @RequestMapping(value="/_version/all", method = RequestMethod.GET)
    public VersionInfo versionInfoAll(){
        return ContactServiceApp.versionAll;
    }
}

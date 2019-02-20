package com.sanss.k8s_springboot.module;

import lombok.Data;

@Data
public class IdcardInfoResp extends BaseInfoModule {
    private String idCardNum;
    private String name;
    private String sex;
    private String validFrom;
    private String validTo;
    private String address;

    private String nation;
}

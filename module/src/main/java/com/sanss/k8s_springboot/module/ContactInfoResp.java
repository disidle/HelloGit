package com.sanss.k8s_springboot.module;

import lombok.Data;

@Data
public class ContactInfoResp extends BaseInfoModule {
    private String idCardNo;
    private String name;
    private String phoneno;
    private String familyAddr;
    private String email;
}

package com.sanss.k8s_springboot.module;

public abstract class BaseInfoModule {

    public static final String SERVICE_NAME_FRONTEND = "K8S-DEMO-FRONTEND";
    public static final String SERVICE_NAME_IDCARD = "K8S-DEMO-IDCARD";
    public static final String SERVICE_NAME_CONTACT = "K8S-DEMO-CONTACT";
    public static final String SERVICE_NAME_CALLHISTORY = "K8S-DEMO-CALLHISTORY";


    public String instanceId = "";
    public long serviceCounter = 0;
    public String serviceName = "";
}

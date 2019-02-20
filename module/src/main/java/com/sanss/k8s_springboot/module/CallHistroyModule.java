package com.sanss.k8s_springboot.module;

import lombok.Data;

@Data
public class CallHistroyModule {
    private String datetime;
    private String callingParty;
    private String calledParty;
    private String duration;
}

package com.sanss.k8s_springboot.module;

import lombok.Data;

@Data
public class HealthCheckInfo extends BaseInfoModule {
    private String status;
}

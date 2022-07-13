package com.springcloud.learn.myLoadBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance getServiceInstance(List<ServiceInstance> instanceList);
}

package com.springcloud.learn.myLoadBalancer;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//自定义负载均衡器，模拟Ribbon的轮询算法，要加组件注解被框架注入
@Component
public class MyLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //用final修饰方法，防止被重写
    public final int getAndIncrement(){
        int current;
        int next;

        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while(!this.atomicInteger.compareAndSet(current,next));  //这里判断，如果CAS方法判断预期值和当前值一样，则进行替换，并放回true，则!true为false,终端循环，执行下面的语句，返回next
        System.out.println("第几次访问，次数next:" + next);
        return next;
    }


    @Override
    //1, ServiceInstance就是代表服务实例
    public ServiceInstance getServiceInstance(List<ServiceInstance> instanceList) {
        int index = getAndIncrement() % instanceList.size();  //取余数，作为服务的下标，进行多个服务的轮询访问
        return instanceList.get(index);
    }
}

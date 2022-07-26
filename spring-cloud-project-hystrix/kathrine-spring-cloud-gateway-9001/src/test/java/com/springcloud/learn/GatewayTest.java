package com.springcloud.learn;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;


@SpringBootTest
public class GatewayTest {

    @Test
    public void getTimeZone(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("now==>" + zonedDateTime);

    }
}

package com.springcloud.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1，微服务模块过多以后，当需要修改配置时，尤其是相同的配置时，就得每个模块每个模块的修改，太浪费时间，
 * 所以，设置了一个配置中心(服务端)，在其内统一设置配置文件信息，其它模块(服务端)只需从配置中心获取配置信息即可，以后只需修改配置中心即可；
 * 2，访问本接口：http://localhost:6611/config-client/getConfigInfo后，因为bootstrap.yml配置文件里链接了总配置中心模块6600
 * 因此会获取总配置中心的配置信息，经过此方式实现了统一配置的目的；
 * 3，由于总配置中心模块6600是直连的git，所以运维工程师改变git上的配置文件信息时，6600模块无需重启，直接刷新http://localhost:6600/master/config-dev.yml
 * 即可获取最新配置信息，但是客户端模块6611没有直连，刷新没用，必须重启才管用。
 *   解决方案：在Controller加@RefreshScope注解，同时需要运维工程师请求6611，执行：curl -X POST "http://localhost:6611/actuator/refresh",
 *   这样做当服务中模块较多时就会太耗费人力，不方便，优化方案：使用RabbitMQ+Bus消息总线，见下章。
 *
 *   注意：由于git修改配置文件后总配置中心刷新不起作用，因此客户端刷新也不管用，原因待查。
 *   原因：application.yml文件里的git地址uri写错了，写成spring-cloud的了，而修改的是spring-cloud-config仓库
 * 4，前面的支付模块，消费者模块相对于总配置中心来说就是客户端的角色；
 * */

@RefreshScope   //加此注解，表示当运维工程师请求时，则会自动同步配置中心的配置信息
@Controller
@RequestMapping(value = "/config-client")
public class ConfigClientController6611 {

    /**
     * (1)bootstrap.yml文件里没有info这个属性，推测是系统默认自带的；
     * 这里会获取从6600总配置中心取得的配置数据
     * (2)这就相当于从配置文件中获取信息了，其它数据源管理依赖如Druid，MySQL Connector等也是可以获取到，只不过这个配置文件不在项目里，在git仓库上
     * 项目启动时，需要用到配置文件信息的上述依赖会先去bootstrap.yml里找，然后bootstrap.yml的配置信息会告诉它们去哪获取配置信息。
     * */
    @Value("${config.info}")
    String configInfo;


    @RequestMapping(value = "/getConfigInfo",method = {RequestMethod.GET})
    @ResponseBody
    public String getConfigInfo(){
        return configInfo;
    }


}

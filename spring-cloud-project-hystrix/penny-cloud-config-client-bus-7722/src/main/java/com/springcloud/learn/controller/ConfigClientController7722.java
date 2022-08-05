package com.springcloud.learn.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope
@RequestMapping(value = "/config-client-bus7722")
public class ConfigClientController7722 {


    /**
     * bootstrap.yml文件里没有info这个属性，推测是系统默认自带的；
     * 这里会获取从7700总配置中心取得的配置数据
     * */
    @Value("${config.info}")
    String configInfo;


    @RequestMapping(value = "/getConfigInfo",method = {RequestMethod.GET})
    @ResponseBody
    public String getConfigInfo(){
        return configInfo;
    }

}

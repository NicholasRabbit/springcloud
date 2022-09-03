package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentFeignService;
import javafx.animation.PauseTransition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/sentinel/consumer")
public class FlowLimitController {

    @Value("${config.info}")
    private String configInfo;
    @Autowired
    private PaymentFeignService paymentFeignService;

    /**一，测试Sentinel的流控规则:QPS熔断策略
     * 1，即限制每秒的请求数，例设置阈值为1，则表示每秒只允许一个线程过来，多的话则会全部挡在接口之外。
     * 2，注意重启服务以后，Sentinel之前对接口设置的策略都不管用了，要在Sentinel界面重新设置。
     * */
    @GetMapping("/get")
    @ResponseBody
    public CommonResult<PaymentUser> get(PaymentUser paymentUser) {
        return new CommonResult<PaymentUser>(100, "configInfo:" + configInfo);
    }


    /**二，测试Sentinel的流控规则:线程数
     * 1，限制每次访问接口的线程数，例，设置为1，则表示允许接口只放进来一个线程进行业务处理等操作，
     *   其他的线程再访问则直接阻挡并页面显示提示：Blocked by Sentinel (flow limiting)
     * */
    @GetMapping("/testThread")
    @ResponseBody
    public CommonResult<PaymentUser> testThread(PaymentUser paymentUser) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<PaymentUser>(100, "testThread, configInfo:" + configInfo);
    }

    /**
     * 三，流控模式：关联，可设置多个流控效果，默认是失败
     * Sentinel以阈值类型QPS为例，线程数的关联功能同理。
     * 步骤，
     * 1，在Sentinel流控界面的“/sentinel/consumer/insertOrder”接口设置QPS规则为每秒1次，关联资源设置为：/sentinel/consumer/pay。
     *    而在Sentinel界面的/sentinel/consumer/pay接口无需任何设置。
     * 2，用postman模拟并发访问/sentinel/consumer/pay接口，200ms一次，持续100次，在这期间访问insertOrder接口则会被Sentinel拦截并报错，
     *    因为insertOrder设置了流控规则，并关联了pay接口，而pay接口的访问量超过了每秒1次，导致insertOrder失败。
     *    俗称，我感冒你吃药。
     * */
    @PostMapping("/pay")   //支付接口
    @ResponseBody
    public CommonResult<PaymentUser> pay(PaymentUser paymentUser){  //不加@RequestBody注解，postman使用form-data的格式传数据
        System.out.println("paymentUser==>" + paymentUser);
        return new CommonResult<PaymentUser>(100, "pay==>configInfo:" + configInfo);
    }

    @PostMapping("/insertOrder")  //新增订单接口
    @ResponseBody
    public CommonResult<PaymentUser> insertOrder(PaymentUser paymentUser){
        System.out.println("paymentUser==>" + paymentUser);
        return new CommonResult<PaymentUser>(100, "insertOrder==>configInfo:" + configInfo);
    }

    /**
     * 四，流控模式：链路
     *
     *
     * */
    @GetMapping("getById/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable Long id){
        return paymentFeignService.getById(id);
    }






}

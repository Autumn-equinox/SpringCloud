package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/test")
    public String test(){
        System.out.println(10/0);
        return "带有熔断机制的服务提供者";
    }

    @RequestMapping("/test02")
    public String test02(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "带有熔断机制的服务提供者延迟操作";
    }
    @RequestMapping("/test03")
    public String test03(){
        System.out.println(10/0);
        return "带有熔断机制的服务提供者test03";
    }
    @RequestMapping("/test04")
    public String test04(){
        System.out.println(10/0);
        return "带有熔断机制的服务提供者test04";
    }
}

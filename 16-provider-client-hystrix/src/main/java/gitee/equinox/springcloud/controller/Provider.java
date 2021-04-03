package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Provider {
    @RequestMapping("/test01")
    public String test01(){
        System.out.println(10/0);//服务端异常
        return "带有熔断机制的服务提供者";
    }
}

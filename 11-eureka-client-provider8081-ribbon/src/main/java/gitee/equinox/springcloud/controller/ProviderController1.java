package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController1 {
    @RequestMapping(value = "/test")
    public  String test(){
        return "服务提供者8081";
    }
}

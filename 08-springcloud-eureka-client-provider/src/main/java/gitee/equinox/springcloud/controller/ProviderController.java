package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @RequestMapping(value = "/test")
    public String test(){
        return "Eureka集群服务提供者！";
    }
}

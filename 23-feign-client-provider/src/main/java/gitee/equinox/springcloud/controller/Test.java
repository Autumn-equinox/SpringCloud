package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping(value = "/test")
    public String test(){
        return "使用feign服务提供者";
    }
}

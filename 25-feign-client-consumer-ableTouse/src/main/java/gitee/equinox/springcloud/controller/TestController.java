package gitee.equinox.springcloud.controller;

import gitee.equinox.springcloud.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/test")
    public String test(){
        String result = testService.test();
        return "使用了Feign的服务消费者---------->"+result;
    }
}

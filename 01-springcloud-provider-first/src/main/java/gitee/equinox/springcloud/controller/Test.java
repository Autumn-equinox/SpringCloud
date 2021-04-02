package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作为SpringCloud的服务提供者，控制器要求返回一个Rest风格的数据，因此我们这里直接使用
 * RestController，返回一个Json的数据
 */
@RestController
public class Test {
    @RequestMapping(value = "/test")
    public String test(){
        return "第一个SpringCloud的服务提供者";
    }
}

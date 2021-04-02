package gitee.equinox.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Test {
    //注入一个Rest风格的请求模板对象
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/test")
    public String test(){

        ResponseEntity<String> result=  restTemplate.getForEntity("http://04-springcloud-eureka-client-provider/test",String.class);
        String body=result.getBody();
        return "使用了Eureka的服务消费者-----"+body;
    }
}

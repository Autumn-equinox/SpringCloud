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
        String url = "http://28-eureka-client-provider-zuul/test";
        String body = restTemplate.getForObject(url,String.class);
        return "没有经过网关zuul的服务消费者-----"+body;
    }

    @RequestMapping("/test01")
    public String test01(){
        String url = "http://26-eureka-client-zuul-gateway/api-zuul/test";
        String body = restTemplate.getForObject(url,String.class);
        return "经过网关zuul的服务消费者-----"+body;
    }
}

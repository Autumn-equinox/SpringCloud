package gitee.equinox.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Consumer {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test01")
    public String test(){
        ResponseEntity<String> result=  restTemplate.getForEntity("http://16-provider/test01",String.class);
        String body=result.getBody();
        return "带有Hystrix的服务消费者-----"+body;
    }
}

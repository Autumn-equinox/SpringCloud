package gitee.equinox.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test")
    public String test() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://08-springcloud-eureka-client-provider/test", String.class);
        String body = result.getBody();
        return "Eureka集群服务消费者--------->" + body;
    }
}

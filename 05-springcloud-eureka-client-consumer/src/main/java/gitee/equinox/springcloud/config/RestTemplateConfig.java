package gitee.equinox.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /*
     * 在Spring的应用上下文中定义一个Rest模板对象
     * @LoadBalanced 注解用于标记当前的RestTemplate使用Ribbon的负载均衡访问服务的提供者
     * 当使用了Eureka注册中心后必须使用这个注解否则无法正常获取服务，默认情况Ribbon的负载均衡
     * 策略是轮询的
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

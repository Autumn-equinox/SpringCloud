package gitee.equinox.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /**
     * 在Spring的应用上下文中定义一个Rest模板对象
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

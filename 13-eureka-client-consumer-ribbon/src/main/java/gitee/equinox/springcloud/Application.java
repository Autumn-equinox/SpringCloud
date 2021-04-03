package gitee.equinox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
/*@RibbonClient(name = "SPRINGCLOUD-DEPT",configuration = MyRule.class)*/
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

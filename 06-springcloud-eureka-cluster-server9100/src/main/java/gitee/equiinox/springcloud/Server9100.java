package gitee.equiinox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Server9100 {

    public static void main(String[] args) {
        SpringApplication.run(Server9100.class, args);
    }

}

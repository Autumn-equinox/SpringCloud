package gitee.equinox.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 作为SpringCloud的服务消费者，我们可以返回任何数据，这里我们使用了RestController仅仅只是为了测试方便并
 * 不是唯一标准
 */
@RestController
public class Test {
    //使用配置类，注入一个Rest风格的请求模板对象
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test")
    public String test() {

        /*
         * RestTemplate 是一个基于Http协议的一个工具对象
         * 我们可以利用这个对象，以Http协议发送请求到指定的某个Web服务器中
         * 在SpringCloud中可以利用这个对象来访问服务提供者
         * 这个对象可以new 也可以交给Spring来创建（建议交给Spring）
         */
        //RestTemplate restTemplate=new RestTemplate();
        /*
         * getForEntity 方法 是一个get方法提交请求，访问web服务器中的某个请求对应着另外一个工程中的
         * @GetMapping或@RequestMapping
         * 参数 1 需要访问的具体请求路径
         * 参数 2 本次请求以后服务器返回的数据类型
         * 参数 3 为可变长度的Object类型数据，表示本次请求中的url参数数据
         * 注意：由于SpringCloud返回的数据全部是Rest风格的数据因此，所有数据类型全部都是String类型的Json数据格式
         * 可以根据具体的数据格式来指定返回类型，交给Spring完成数据的封装
         *
         * 返回值：ResponseEntity对象，这个对象封装本次请求后的响应实体
         * 这个对象中我们可以获取本次请求的状态码 或头文件信息，以及具体响应数据
         */
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8081/test", String.class);
        System.out.println(result.getStatusCode());//获取响应编码例如 200 或 404等等
        System.out.println(result.getHeaders());//获取响应头文件信息
        String body = result.getBody();//获取具体的响应数据具体类型取决getForEntity方法的参数2
        return "第一个SpringCloud的服务消费者-----" + body;
    }
}

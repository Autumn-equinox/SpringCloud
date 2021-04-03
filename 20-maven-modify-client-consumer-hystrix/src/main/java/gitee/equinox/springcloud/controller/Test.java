package gitee.equinox.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Test {
    //注入一个Rest风格的请求模板对象
    @Resource
    private RestTemplate restTemplate;

    /**
     *@HystrixCommand 注解的作用是标记当前控制器方法启用Hystrix的熔断机制，当我们调用远程服务时如果远程服务出现了异常或
     * 超时（指定时间内没有返回响应）就会自动进行熔断
     *
     * 属性
     *   fallbackMethod  用于指定一个本地方法的名称，当服务熔断以后就会调用这个方法来替代服务提供者的响应信息
     */
    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/test")
    public String test(){
        ResponseEntity<String> result=  restTemplate.getForEntity("http://19-provider/test",String.class);
        String body=result.getBody();
        return "带有Hystrix的服务消费者-----"+body;
    }

    /**
     * 属性
     * commandProperties用于设置熔断器的一些属性
     *    @HystrixProperty 注解用于指定一个熔断的属性
     *      execution.isolation.thread.timeoutInMilliseconds 表示熔断的超时时间 如果服务在指定时间内没有返回则表示超时需要熔断
     *                                                       默认为1000毫秒
     *      value用于指定属性的值 1500 表示1.5秒配合timeoutInMilliseconds属性名用于自定义熔断器的超时时间
     *
     */
    @HystrixCommand(fallbackMethod = "error",commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")}
    )
    @RequestMapping("/test02")
    public String test02(){
        ResponseEntity<String> result=  restTemplate.getForEntity("http://19-provider/test02",String.class);
        String body=result.getBody();
        return "带有Hystrix的服务消费者-----"+body;
    }

    /**
     * 属性
     * ignoreExceptions 用于指定熔断时忽略某些异常，如果程序出现了指定的异常以后将不进行熔断而是将错误直接抛出
     * 抛给服务的调用者，这个调用者可能是用户也可能是其他的服务
     *
     * 注意：如果需要忽略服务提供者抛出的异常那么我们需要过滤掉HttpServerErrorException.InternalServerError.class
     *      当我们访问服务提供者时如果服务提供者出现异常那么Spring就会抛出这个异常信息
     */
    @HystrixCommand(fallbackMethod = "error",commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")},
            ignoreExceptions = {HttpServerErrorException.InternalServerError.class}
    )
    @RequestMapping("/test03")
    public String test03(){
//        String str=null;
//        System.out.println(str.length());
        ResponseEntity<String> result=  restTemplate.getForEntity("http://19-provider/test03",String.class);
        String body=result.getBody();
        return "带有Hystrix的服务消费者test03-----"+body;
    }

//    @RequestMapping("/test04")
//    public String test04(){
//        String url="http://06-eureka-client-hystrix-provider/test03";
//
//        MyHystrixCommand command=new MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),restTemplate,url);
//        //执行请求并获取响应结果
//        //execute方法后台会自动调用run方法访问服务的提供者，如果服务提供者抛出异常则自动调用getFallback方法来返回响应数据
//        String body= (String) command.execute();
//        return "带有Hystrix的服务消费者test04-----"+body;
//    }




    /**
     * 服务的降级方法，当我们的某个控制器方法被熔断以后就不能正常返回给用户一个响应结果，因此
     * 在服务被熔断后会自动调用这个服务的降级方法，来为用户返回一个本地的响应信息，这个信息
     * 虽然不是真正的处理结果，但是至少我们可以返回一个友好提示避免用户看见错误内容或无限等待
     *
     * 参数 1 Throwable 只要被监控的控制器方法出现异常，就会被自动熔断无论是控制本身还是远程服务出现异常
     *        参数Throwable就会被Spring注入一个异常对象
     *
     * 注意：如果是服务提供者抛出异常那么Spring会注入一个HttpServerErrorException$InternalServerError 异常对象
     *       获取的异常并不是我们服务提供者实际出现的异常类型
     *
     */
    public  String  error(Throwable throwable){
        System.out.println(throwable.getClass());
        System.out.println(throwable.getMessage());
        return "服务被熔断了"+throwable.getMessage();
    }
}

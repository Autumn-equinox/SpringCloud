package gitee.equinox.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @FeignClient 用于标记当前接口是一个Feign的声明式服务接口
 * Spring会为这个接口生成动态代理对象
 * 属性
 *   name 用于指定注册中心中的某个服务的名字
 *   fallback 指定一个自定义的异常熔断器类，当使用声明式接口中的方法调用远程服务时
 *            如果出现了异常则自动执行fallback所指定的这个类中的对应的方法来执行服务的降级
 *  fallbackFactory 指定一个自定义的异常熔断器类，这个熔断器类可以获取异常信息
 */
@FeignClient(name = "23-feign-client-provider")
public interface TestService {
    /*
     * 定义抽象方法，并使用RequestMapping标记这个方法用于访问服务提供者
     * 其中RequestMapping的参数 /test 应该与当前接口所标记的服务名中的服务中的
     * 某个请求路径相同
     * 返回值为 服务提供者返回的具体数据内容
     */
    @RequestMapping(value = "/test")
    String test();
}

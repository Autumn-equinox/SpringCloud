package gitee.equinox.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义网关过滤器类并继承过滤父类
 */
@Component
public class MyFilter extends ZuulFilter {
    //当前方法的返回值用于决定当前过滤器的类型（执行时机）
    public String filterType() {
        //返回pre表示当前过滤器为前置过滤器，需要在执行转发（访问服务提供者）前执行，通常用于做身份认证
        return "pre";
    }

    //过滤器的序号，如果有多个同类型的过滤那么根据这个返回值大小决定执行的先后顺序
    //返回值越大执行的优先级越大，例如 序号为0和序号为1的2个过滤器那么就会先执行序号为0的
    public int filterOrder() {
        return 0;
    }

    //当前过滤器是否启动，返回true表示启用当前过滤器 false则表示不启用
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体执行方法
    //注意：返回值目前版本没有特殊作用，因此可以写null
    public Object run() throws ZuulException {
        System.out.println(10/0);
        //获取当前请求上下文对象
        RequestContext context= RequestContext.getCurrentContext();
        //获取用户请求对象
        HttpServletRequest request=context.getRequest();
        //获取请求中的请求参数 token （身份令牌用于验证请求身份是否合法）
        String token=request.getParameter("token");

        //验证令牌有效性，进入if表示请求没有身份令牌或令牌错误
        //注意：实际工作时这里应该到数据库中去验证令牌的合法性
        if(token==null||!"123".equals(token)){
            //设定false表示请求不继续执行（不转发给服务提供者）
            context.setSendZuulResponse(false);
            //设置响应编码为401表示权限不足也可设置500或其他编码
            context.setResponseStatusCode(401);
            //设置响应类型以及编码格式
            context.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            //设置响应内容
            context.setResponseBody("非法请求");
        }else{
            System.out.println("请求合法继续执行请求准备进入服务或下一个过滤器");
        }
        return null;
    }
}

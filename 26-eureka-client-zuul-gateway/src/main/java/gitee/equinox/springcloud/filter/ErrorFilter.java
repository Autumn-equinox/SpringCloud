package gitee.equinox.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //异常过滤器，当前其他过滤器出现异常后自动执行当前过滤器，但是必须要先禁用默认异常过滤器
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;//关闭------什么也不显示--异常
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context= RequestContext.getCurrentContext();
        //获取异常对象
        ZuulException exception= (ZuulException) context.getThrowable();
        HttpServletResponse response=context.getResponse();

        response.setStatus(exception.nStatusCode);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=null;
        try {
            out= response.getWriter();
            out.println("出现异常了code:"+exception.nStatusCode+"   message:"+exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out!=null){
                out.close();
            }
        }

        return null;
    }
}

package gitee.equinox.springcloud.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {
    //当项目中的某些过滤器出现异常以后，则会自定执行这个控制器中的这个方法来获取处理异常的请求地址
    //然后自动执行这个请求的显示错误信息
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public @ResponseBody String error(){
        ZuulException exception= (ZuulException) RequestContext.getCurrentContext().getThrowable();

        /*
         * 使用全局异常页面，这里可以直接转向到一个视图页面显示错误信息，也可以直接使用Json来显示错误信息
         * 但是使用全局异常错误页面必须要启动默认的异常过滤器，因此全局异常页面和自定义异常过滤有冲突二选一即可
         */
        return "全局错误页面Code："+exception.nStatusCode+"  --- message:"+exception.getMessage();
    }
}

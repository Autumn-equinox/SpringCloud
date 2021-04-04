package gitee.equinox.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
    @RequestMapping("/api/local")
    public Object test(){
        return "网关工程自己的控制器方法";
    }
}

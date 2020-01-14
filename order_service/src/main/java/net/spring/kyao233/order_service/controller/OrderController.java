package net.spring.kyao233.order_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.spring.kyao233.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author K.Yao
 * @date 2019/12/29 19:07
 *
 * http://localhost:8100/api/v1/order/save?user_id=1&product_id=1
 * http://localhost:8100/api/v1/order/save/ribbon?user_id=1&product_id=1
 * http://localhost:8100/api/v1/order/save/feign?user_id=1&product_id=1
 * need to set eureka.instance.hostname to localhost
 * in order to replace computer name with localhost
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @GetMapping("/save")
    public Object save(@RequestParam("user_id") int userId,
                       @RequestParam("product_id") int productId) {
        return orderService.save(userId, productId);
    }


    @GetMapping("/save/ribbon")
    public Object saveByRibbon(@RequestParam("user_id") int userId,
                               @RequestParam("product_id") int productId) {
        return orderService.saveByRibbon(userId, productId);
    }

    @GetMapping("/save/feign")
    @HystrixCommand(fallbackMethod = "saveByFeignFallBack")
    public Object saveByFeign(@RequestParam("user_id") int userId,
                               @RequestParam("product_id") int productId) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        ret.put("data", orderService.saveByFeign(userId, productId));
        return ret;
    }

    private Object saveByFeignFallBack(int userId, int productId) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", "too many people online, please again later");
        ret.put("userId", userId);
        ret.put("productId", productId);
        return ret;
    }

}

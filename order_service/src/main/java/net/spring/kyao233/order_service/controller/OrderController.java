package net.spring.kyao233.order_service.controller;

import net.spring.kyao233.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author K.Yao
 * @date 2019/12/29 19:07
 *
 * http://localhost:8100/api/v1/order/save?user_id=1&product_id=1
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


}

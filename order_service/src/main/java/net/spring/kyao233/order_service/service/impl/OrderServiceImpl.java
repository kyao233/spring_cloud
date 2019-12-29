package net.spring.kyao233.order_service.service.impl;

import net.spring.kyao233.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author K.Yao
 * @date 2019/12/29 19:33
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object save(int userId, int productId) {
        Map<String, Object> map = restTemplate.getForObject(
                "http://product-service/api/v1/product/find?id=" + productId, Map.class);
        System.out.println(map);
        return map;
    }
}

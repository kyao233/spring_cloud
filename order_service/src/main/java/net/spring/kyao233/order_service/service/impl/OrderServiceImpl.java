package net.spring.kyao233.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import net.spring.kyao233.order_service.domain.ProductOrder;
import net.spring.kyao233.order_service.service.OrderService;
import net.spring.kyao233.order_service.service.client.ProductFeignClient;
import net.spring.kyao233.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author K.Yao
 * @date 2019/12/29 19:33
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Object save(int userId, int productId) {
        Map<String, Object> map = restTemplate.getForObject(
                "http://product-service/api/v1/product/find?id=" + productId, Map.class);


        System.out.println(map);
        return map;
    }

    @Override
    public Object saveByRibbon(int userId, int productId) {
        ServiceInstance instance = loadBalancerClient.choose("product-service");
        String url = String.format("http://%s:%s/api/v1/product/find?id=", instance.getHost(), instance.getPort());
        RestTemplate myRestTemplate = new RestTemplate();
        Map<String, Object> map = myRestTemplate.getForObject(url + productId, Map.class);
        map.put("source", "by calling ribbon API");
        System.out.println(map);
        return map;
    }


    @Autowired
    public ProductFeignClient productFeignClient;

    @Override
    public Object saveByFeign(int userId, int productId) {
        String productDetails = productFeignClient.productDetails(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(productDetails);
        Map<String, String> strMap = new HashMap<>();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString() + "from Feign");
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }


}

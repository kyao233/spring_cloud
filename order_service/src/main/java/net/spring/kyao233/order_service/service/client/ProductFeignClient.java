package net.spring.kyao233.order_service.service.client;

import net.spring.kyao233.order_service.fallback.ProductFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author K.Yao
 * @date 2020/1/2 21:50
 */
@FeignClient(serviceId = "product-service",
        path = "/api/v1/product",
        fallback = ProductFeignClientFallBack.class)
public interface ProductFeignClient {

    @GetMapping("/find")
    public String productDetails(@RequestParam("id") int id);

}

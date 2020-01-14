package net.spring.kyao233.order_service.fallback;

import net.spring.kyao233.order_service.service.client.ProductFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author K.Yao
 * @date 2020/1/14 21:45
 */
@Component
public class ProductFeignClientFallBack implements ProductFeignClient {

    @Override
    public String productDetails(int id) {
        System.out.println("Product Details is not available for " + id);
        return null;
    }

}

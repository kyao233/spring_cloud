package net.xdclass.order_service.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", path = "/api/v1/product")
public interface ProductClient {

    @GetMapping("/find")
    String findById(@RequestParam("id") int id);

}

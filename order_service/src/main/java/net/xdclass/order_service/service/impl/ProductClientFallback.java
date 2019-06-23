package net.xdclass.order_service.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient{

    @Override
    public String findById(int id) {
        log.info("product service is down.");
        return null;
    }
}

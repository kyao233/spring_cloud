package net.spring.kyao233.product_service.controller;

import net.spring.kyao233.product_service.domain.Product;
import net.spring.kyao233.product_service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8090/api/v1/product/list
 * @author K.Yao
 * @date 2019/12/29 11:53
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Object listProduct() {
        return productService.listProduct();
    }


    @GetMapping("/find")
    public Object productDetails(@RequestParam("id") int id) {
        Product product = productService.findById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setName(product.getName() + "  " + port);
        return result;
    }

}

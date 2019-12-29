package net.spring.kyao233.product_service.service;

import net.spring.kyao233.product_service.domain.Product;

import java.util.List;

/**
 * @author K.Yao
 * @date 2019/12/29 12:06
 */
public interface ProductService {

    public List<Product> listProduct();

    public Product findById(int id);


}

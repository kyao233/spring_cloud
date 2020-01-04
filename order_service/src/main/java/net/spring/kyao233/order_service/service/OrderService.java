package net.spring.kyao233.order_service.service;

/**
 * @author K.Yao
 * @date 2019/12/29 19:32
 */
public interface OrderService {

    public Object save(int userId, int productId);

    public Object saveByRibbon(int userId, int productId);

    public Object saveByFeign(int userId, int productId);


}

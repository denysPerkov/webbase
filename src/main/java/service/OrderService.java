package service;

import repository.order.Order;
import repository.order.OrderInfo;


public interface OrderService {

    boolean insertFullOrder(final Order order, final OrderInfo orderInfo);

}

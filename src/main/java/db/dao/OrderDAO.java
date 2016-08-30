package db.dao;

import repository.order.Order;
import repository.order.OrderInfo;

import java.sql.Connection;

/**
 * Created by Denys_Perkov on 6/10/2016.
 */
public interface OrderDAO {

    boolean insertOrder(Connection con, Order order);

    boolean insertOrderInfo(Connection con, OrderInfo orderInfo);


}

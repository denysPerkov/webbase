package service.impl;

import db.dao.DAOFactory;
import db.dao.OrderDAO;
import db.transaction.Transaction;
import db.transaction.TransactionManager;
import repository.order.Order;
import repository.order.OrderInfo;
import service.OrderService;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService {

    private TransactionManager transactionManager;
    private DAOFactory daoFactory;
    private OrderDAO orderDAO;

    public OrderServiceImpl(TransactionManager transactionManager, DAOFactory daoFactory, OrderDAO orderDAO) {
        this.transactionManager = transactionManager;
        this.daoFactory = daoFactory;
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean insertFullOrder(final Order order, final OrderInfo orderInfo) {
        final boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                return orderDAO.insertOrder(con, order) && orderDAO.insertOrderInfo(con, orderInfo);
            }
        });

        return result;
    }
}

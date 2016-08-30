package db.dao.impl;

import db.dao.OrderDAO;
import repository.order.Order;
import repository.order.OrderInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean insertOrder(Connection con, Order order) {
        PreparedStatement pStmt = null;

        String insertTableSQL = "INSERT INTO `webpro`.`order`"
                + "(status, detailStatus, idUser, date, address, deliveryType, requisites) VALUES"
                + "(?,?,?,?  ,?,?,?)";

        try {
            pStmt = con.prepareStatement(insertTableSQL);

            pStmt.setString(1, order.getStatus().toString());
            pStmt.setString(2, order.getDetailStatus());
            pStmt.setInt(3, order.getIdUser());
            pStmt.setString(4, String.valueOf(order.getDate()));
            pStmt.setString(5, order.getAddress());
            pStmt.setString(6, order.getDeliveryType());
            pStmt.setString(7, order.getRequisites());

            pStmt.execute();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOrderInfo(Connection con, OrderInfo orderInfo) {
        PreparedStatement pStmt = null;

        String insertTableSQL = "INSERT INTO `webpro`.`orderInfo`"
                + "(idOrder, idProduct, cost, countOfproduct) VALUES"
                + "(LAST_INSERT_ID(),?,?,?)";

        try {
            pStmt = con.prepareStatement(insertTableSQL);

            pStmt.setInt(1, orderInfo.getIdProduct());
            pStmt.setDouble(2, orderInfo.getCost());
            pStmt.setInt(3, orderInfo.getCountOfProduct());
            pStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

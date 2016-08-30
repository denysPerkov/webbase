package repository.order;

import java.sql.Date;
import java.util.List;

public class Order {

    private int idOrder;
    private int idUser;
    private OrderStatus status;
    private String detailStatus;
    private Date date;
    private String address;
    private String deliveryType;
    private String requisites;


    public Order(){}

    public Order(int idUser, String address, String deliveryType, String requisites) {
        this.idUser = idUser;
        this.status = OrderStatus.ACCEPT;
        this.date = new Date(new java.util.Date().getTime());
        this.address = address;
        this.deliveryType = deliveryType;
        this.requisites = requisites;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }
}

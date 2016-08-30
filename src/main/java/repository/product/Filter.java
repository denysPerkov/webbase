package repository.product;

import util.Getter;

public class Filter {

    private String name;
    private String []category;
    private String []producer;
    private String orderType;
    private String orderField;
    private double fromCost;
    private double toCost;


    @Getter(name = "name", relation = "=")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Getter(name = "category", relation = "=")
    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    @Getter(name = "producer", relation = "=")
    public String[] getProducer() {
        return producer;
    }

    public void setProducer(String[] producer) {
        this.producer = producer;
    }

    @Getter(name = "cost", relation = ">")
    public double getFromCost() {
        return fromCost;
    }

    public void setFromCost(double fromCost) {
        this.fromCost = fromCost;
    }

    @Getter(name = "cost", relation = "<")
    public double getToCost() {
        return toCost;
    }

    public void setToCost(double toCost) {
        this.toCost = toCost;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
}

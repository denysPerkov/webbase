package model;

public class CartProduct {

    private int idProduct;
    private String name;
    private String category;
    private String producer;
    private int count;
    private double cost;
    private double sum;

    public CartProduct(){}

    public CartProduct(String name, String category, String producer, int count, double cost, double sum, int idProduct) {
        this.name = name;
        this.category = category;
        this.producer = producer;
        this.count = count;
        this.cost = cost;
        this.sum = sum;
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartProduct cartProduct = (CartProduct) o;

        return idProduct == cartProduct.idProduct;

    }

    @Override
    public int hashCode() {
        return idProduct;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", producer='" + producer + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                ", sum=" + sum +
                '}';
    }
}

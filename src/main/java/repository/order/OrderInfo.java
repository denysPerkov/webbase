package repository.order;

public class OrderInfo {

    private int idProduct;
    private double cost;
    private int countOfProduct;

    public OrderInfo(int idProduct, double cost, int countOfProduct) {
        this.idProduct = idProduct;
        this.cost = cost;
        this.countOfProduct = countOfProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public double getCost() {
        return cost;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }
}

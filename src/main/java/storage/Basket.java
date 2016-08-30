package storage;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private Map<Product, Integer> basket;

    public Basket(){
        this.basket = new HashMap<>();
    }

    public void addProduct(Product product, int countOfProduct){
       if(basket.containsKey(product)){
            countOfProduct += basket.get(product);
            basket.put(product, countOfProduct);
        }

        this.basket.put(product, countOfProduct);
    }

    public void removeProduct(Product product){
        this.basket.remove(product);
    }

    public void changeCountOfProducts(Product product, int count){
        if(basket.containsKey(product)){
            if(count > 0){
                basket.put(product, count);
            }else {
                basket.remove(product);
            }
        }
    }

    public void clearBasket(){
        this.basket.clear();
    }

    public void removeProductById(int idProduct){
        Product product = null;
        for(Map.Entry<Product, Integer> entry : basket.entrySet()){
            if(entry.getKey().getIdProduct() == idProduct){
                product = entry.getKey();
            }
        }
        if(product != null){
            basket.remove(product);
        }
    }

    public List<Product> getAllProducts(){
        List<Product> selectedItems = new ArrayList<>();
        for(Map.Entry<Product, Integer> entry : basket.entrySet()){
            selectedItems.add(entry.getKey());
        }
        return selectedItems;
    }

    public int getTotalCount(){
        int size = 0;
        for(Map.Entry<Product, Integer> entry : basket.entrySet()){
            size += entry.getValue();
        }
        return size;
    }

    public int getProductCount(Product product){
        return basket.get(product);
    }

    public double getTotalSum(){
        double sum = 0.0;
        for(Map.Entry<Product, Integer> entry : basket.entrySet()){
            sum += entry.getKey().getCost() * entry.getValue();
        }
        return sum;
    }
}

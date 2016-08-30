package repository.product;

import model.CartProduct;
import model.Product;
import storage.Basket;

import java.util.ArrayList;
import java.util.List;

public class CartProductCreator {

    public List<CartProduct> getAllProducts(Basket basket){
        List<CartProduct> products = new ArrayList<>();
        CartProduct cartProduct = null;
        for(Product product : basket.getAllProducts()){
            cartProduct = new CartProduct();
            cartProduct.setIdProduct(product.getIdProduct());
            cartProduct.setName(product.getName());
            cartProduct.setCost(product.getCost());
            cartProduct.setProducer(product.getProducer());
            cartProduct.setCategory(product.getCategory());
            cartProduct.setCount(basket.getProductCount(product));
            cartProduct.setSum(product.getCost() * basket.getProductCount(product));

            products.add(cartProduct);
        }

        return products;
    }
}


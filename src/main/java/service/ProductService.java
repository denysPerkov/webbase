package service;


import model.Product;
import repository.product.Filter;

import java.util.List;

public interface ProductService {

    List<Product> getItems(Filter filter, int offset, int count);

    List<String> getAllCategorys();

    List<String> getAllManufactures();

    int getCountProduct(Filter filter);

}

package db.dao;

import model.Product;
import repository.product.Filter;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Denys_Perkov on 6/10/2016.
 */
public interface ProductDAO {

    List<Product> getItems(Filter filter, int offset, int count, Connection con);

    List<String> getAllCategorys(Connection con);

    List<String> getAllManufactures(Connection con);

    int getCountProduct(Filter filter, Connection con);


}

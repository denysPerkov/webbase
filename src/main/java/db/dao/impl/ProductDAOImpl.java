package db.dao.impl;

import db.dao.DAOFactory;
import db.dao.ProductDAO;
import model.Product;
import repository.product.Filter;
import repository.product.SQLBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl implements ProductDAO {


    @Override
    public List<Product> getItems(Filter filter, int offset, int count, Connection con) {
        List<Product> products = new ArrayList<Product>();
        SQLBuilder builder = new SQLBuilder(filter);

        try {
            builder.setQuery("SELECT * FROM product");
            if(filter.getOrderType() != null && filter.getOrderType().equals("DESC")){
                builder.orderByDESC(filter.getOrderField());
            }else{
                builder.setOrderByASC(filter.getOrderField());
            }
            builder.setQueryWithOffset(offset, count);

            String readSQL = builder.getQuery();
            Product product = null;
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(readSQL);

            while (rs.next()){
                product = new Product();
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setCost(rs.getDouble("cost"));
                product.setProducer(rs.getString("producer"));
                product.setIdProduct(rs.getInt("idProduct"));

                products.add(product);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<String> getAllCategorys(Connection con) {
        List<String> products = new ArrayList<String>();

        Statement statement = null;
        try {

            String readSQL = "SELECT DISTINCT category FROM product";
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(readSQL);

            while (rs.next()){
                products.add(rs.getString("category"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<String> getAllManufactures(Connection con) {
        List<String> products = new ArrayList<String>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DAOFactory.getDataSource().getConnection();
            String readSQL = "SELECT DISTINCT producer FROM webpro.product";
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(readSQL);

            while (rs.next()){
                products.add(rs.getString("producer"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public int getCountProduct(Filter filter, Connection con) {
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DAOFactory.getDataSource().getConnection();
            SQLBuilder builder = new SQLBuilder(filter);
            builder.setQuery("SELECT COUNT(idProduct) FROM product");

            String readSQL = builder.getQuery();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(readSQL);
            rs.next();
            count = rs.getInt(1);

        } catch (Exception e){
            e.printStackTrace();
        }

        return count;
    }
}

package service.impl;

import db.dao.DAOFactory;
import db.dao.ProductDAO;
import db.transaction.Transaction;
import db.transaction.TransactionManager;
import model.Product;
import repository.product.Filter;
import service.ProductService;

import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private TransactionManager transactionManager;
    private DAOFactory daoFactory;
    private ProductDAO productDAO;

    public ProductServiceImpl(TransactionManager transactionManager, DAOFactory daoFactory, ProductDAO productDAO) {
        this.transactionManager = transactionManager;
        this.daoFactory = daoFactory;
        this.productDAO = productDAO;
    }


    @Override
    public List<Product> getItems(final Filter filter, final int offset, final int count) {
        List<Product> result = transactionManager.doTask(new Transaction<List<Product>>() {
            @Override
            public List<Product> execute(Connection con) {
                return productDAO.getItems(filter, offset, count, con);
            }
        });

        return result;
    }

    @Override
    public List<String> getAllCategorys() {
        List<String> result = transactionManager.doTask(new Transaction<List<String>>() {
            @Override
            public List<String> execute(Connection con) {
                return productDAO.getAllCategorys(con);
            }
        });

        return result;
    }

    @Override
    public List<String> getAllManufactures() {
        List<String> result = transactionManager.doTask(new Transaction<List<String>>() {
            @Override
            public List<String> execute(Connection con) {
                return productDAO.getAllManufactures(con);
            }
        });

        return result;
    }

    @Override
    public int getCountProduct(final Filter filter) {
        int result = transactionManager.doTask(new Transaction<Integer>() {
            @Override
            public Integer execute(Connection con) {
                return productDAO.getCountProduct(filter, con);
            }
        });

        return result;
    }
}

package db.dao;

import db.dao.impl.OrderDAOImpl;
import db.dao.impl.ProductDAOImpl;
import db.dao.impl.UserDAOImpl;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOFactory {

    private static final String PATH = "E:\\workspace\\webpro5\\db.properties";

    public static synchronized DataSource getDataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        BasicDataSource ds = new BasicDataSource();

        try {
            fis = new FileInputStream(PATH);
            props.load(fis);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

        ds.setDriverClassName(props.getProperty("DB_DRIVER_CLASS"));
        ds.setUrl(props.getProperty("DB_URL"));
        ds.setUsername(props.getProperty("DB_USERNAME"));
        ds.setPassword(props.getProperty("DB_PASSWORD"));

        return ds;
    }

}

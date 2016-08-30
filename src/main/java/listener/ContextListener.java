package listener;

import db.dao.DAOFactory;
import db.dao.OrderDAO;
import db.dao.ProductDAO;
import db.dao.UserDAO;
import db.dao.impl.OrderDAOImpl;
import db.dao.impl.ProductDAOImpl;
import db.dao.impl.UserDAOImpl;
import db.transaction.TransactionManager;
import i18n.locale.impl.CookieLocaleManager;
import i18n.locale.LocaleManager;
import i18n.locale.impl.SessionLocaleManager;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import util.Constant;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        TransactionManager transactionManager = new TransactionManager();
        DAOFactory daoFactory = new DAOFactory();

        UserDAO userDAO = new UserDAOImpl();
        UserService userService = new UserServiceImpl(transactionManager, daoFactory, userDAO);
        servletContext.setAttribute("userService", userService);

        OrderDAO orderDAO = new OrderDAOImpl();
        OrderService orderService = new OrderServiceImpl(transactionManager, daoFactory, orderDAO);
        servletContext.setAttribute("orderService", orderService);

        ProductDAO productDAO = new ProductDAOImpl();
        ProductService productService = new ProductServiceImpl(transactionManager, daoFactory, productDAO);
        servletContext.setAttribute("productService", productService);

        String localeMode = servletContextEvent.getServletContext().getInitParameter(Constant.LOCALE_MODE);
        LocaleManager localManager = initializeLocalManager(servletContextEvent.getServletContext(), localeMode);
        servletContext.setAttribute(Constant.LOCALE_MANAGER, localManager);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //to do logger
    }

    private LocaleManager initializeLocalManager(ServletContext context, String localeMode) {
        switch (localeMode) {
            case Constant.LOCALE_SESSION:
                return new SessionLocaleManager();
            case Constant.LOCALE_COOKIE:
                int maxCookieAge = Integer.parseInt(context.getInitParameter("cookieMaxAge"));
                return new CookieLocaleManager(maxCookieAge);
            default:
                return new SessionLocaleManager();
        }
    }
}

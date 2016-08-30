package servlet;

import model.Product;
import model.User;
import repository.order.Order;
import repository.order.OrderInfo;
import service.OrderService;
import storage.Basket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deliveryType = req.getParameter("deliveryType");
        String address = req.getParameter("address");
        String requisites = req.getParameter("requisites");
        User user = (User) req.getSession().getAttribute("user");
        Basket basket = (Basket) req.getSession().getAttribute("basket");

        List<Product> products = (List<Product>) req.getSession().getAttribute("products");

        for(Product product : basket.getAllProducts()){
              orderService.insertFullOrder(new Order(user.getIdUser(), address, deliveryType, requisites),
                      new OrderInfo(product.getIdProduct(), product.getCost(), basket.getProductCount(product)));
        }

        resp.sendRedirect("home");
    }

}

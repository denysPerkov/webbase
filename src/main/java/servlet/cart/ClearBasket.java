package servlet.cart;


import repository.product.CartProductCreator;
import storage.Basket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/clearCart")
public class ClearBasket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Basket basket = null;
        if(action != null && action.equals("clear")){
            basket = (Basket) req.getSession().getAttribute("basket");
            basket.clearBasket();
            req.getSession().setAttribute("basket", basket);
        }else if(action != null && action.equals("removeRow")){
            int idProudct = Integer.parseInt(req.getParameter("idProduct"));
            basket = (Basket) req.getSession().getAttribute("basket");
            basket.removeProductById(idProudct);
            req.setAttribute("productBasket", new CartProductCreator().getAllProducts(basket));
            req.getSession().setAttribute("basket", basket);
        }
        req.getSession().setAttribute("totalCount", basket.getTotalCount());
        req.getSession().setAttribute("totalCost", basket.getTotalSum());

        req.getRequestDispatcher("resources/jsp/basket.jsp").forward(req, resp);
    }
}

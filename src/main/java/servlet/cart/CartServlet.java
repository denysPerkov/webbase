package servlet.cart;

import repository.product.CartProductCreator;
import storage.Basket;
import storage.MessageContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Basket basket = null;
        if(req.getSession().getAttribute("basket") == null){
            req.setAttribute("emptyBasket", MessageContainer.getInstance().getMessage("emptyBasket"));
        }else{
            basket = (Basket) req.getSession().getAttribute("basket");
            req.setAttribute("productBasket", new CartProductCreator().getAllProducts(basket));
            req.getSession().setAttribute("basket", basket);
        }

        req.getRequestDispatcher("resources/jsp/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        CartProductCreator cart = new CartProductCreator();
        req.setAttribute("productBasket", cart.getAllProducts(basket));
        req.getSession().setAttribute("basket", basket);

        resp.getWriter().print(5);
    }
}

package servlet.cart;

import repository.product.CartProductCreator;
import model.Product;
import storage.Basket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeCount")
public class ChangeCountProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int changedCount = Integer.parseInt(req.getParameter("changedCount"));
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        basket.changeCountOfProducts(extractProduct(req), changedCount);
        req.getSession().setAttribute("basket", basket);
        req.setAttribute("productBasket", new CartProductCreator().getAllProducts(basket));
        req.getSession().setAttribute("totalCount", basket.getTotalCount());
        req.getSession().setAttribute("totalCost", basket.getTotalSum());

        req.getRequestDispatcher("resources/jsp/basket.jsp").forward(req, resp);
    }

    private Product extractProduct(HttpServletRequest req){
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String producer = req.getParameter("producer");
        double cost = Double.parseDouble(req.getParameter("cost"));

        return new Product(idProduct, name, category, producer, cost);
    }
}
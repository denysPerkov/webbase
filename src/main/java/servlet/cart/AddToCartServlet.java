package servlet.cart;

import model.Product;
import storage.Basket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/basket")
public class AddToCartServlet extends HttpServlet {

    private static final int STEP_COUNT = 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = extractProduct(req);
        Basket basket = null;
        int countProduct = STEP_COUNT;
        if(req.getParameter("countProd") != null && !req.getParameter("countProd").equals("")){
            countProduct = Integer.parseInt(req.getParameter("countProd"));
        }
        if(req.getSession().getAttribute("basket") == null){
            basket = new Basket();
            basket.addProduct(extractProduct(req), countProduct);
            req.getSession().setAttribute("basket", basket);
        }else{
            basket = (Basket) req.getSession().getAttribute("basket");
            basket.addProduct(extractProduct(req), countProduct);
            req.getSession().setAttribute("basket", basket);
        }
        req.getSession().setAttribute("totalCount", basket.getTotalCount());
        req.getSession().setAttribute("totalCost", basket.getTotalSum());

        resp.getWriter().print(basket.getTotalSum() + "~" + basket.getTotalCount());
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

package servlet;

import model.Product;
import model.User;
import repository.product.Filter;
import service.ProductService;
import util.FIleUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
            User user = (User) req.getSession().getAttribute("user");
            req.getSession().setAttribute("firstName", user.getFirstName());
            String image = FIleUtil.getImage(user.getLogin());
            req.getSession().setAttribute("image", image);
        }

        int currentPage = 1;
        int recordsPerPage = 4;

        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getParameter("countProduct") != null && !req.getParameter("countProduct").equals("")) {
            recordsPerPage = Integer.parseInt(req.getParameter("countProduct"));
        }

        Filter filter = getFilter(req);
        int coutProduct = productService.getCountProduct(filter);
        List<Product> products = productService.getItems(filter, (currentPage - 1) * recordsPerPage, recordsPerPage);

        req.setAttribute("productList", products);
        req.setAttribute("currentPage", currentPage);
        req.getSession().setAttribute("countPages", calculatePages(coutProduct, recordsPerPage));
        req.getSession().setAttribute("producers", productService.getAllManufactures());
        req.getSession().setAttribute("categoryes", productService.getAllCategorys());

        req.getRequestDispatcher("resources/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String idProduct = req.getParameter("idProduct");
    }

    private Filter getFilter(HttpServletRequest req) {
        Filter filter = new Filter();

        if(req.getParameter("name") != null && !req.getParameter("name").equals("")) {
            filter.setName(req.getParameter("name"));
        }

        if(req.getParameter("fromPrice") != null && !req.getParameter("fromPrice").equals("")) {
            filter.setFromCost(Double.parseDouble(req.getParameter("fromPrice")));
        }

        if(req.getParameter("toPrice") != null && !req.getParameter("fromPrice").equals("")) {
            filter.setToCost(Double.parseDouble(req.getParameter("toPrice")));
        }

        if(req.getParameter("category") != null && !req.getParameter("category").equals("")) {
            filter.setCategory(req.getParameterValues("category"));
        }

        if(req.getParameter("manufacturer") != null && !req.getParameter("manufacturer").equals("")) {
            filter.setProducer(req.getParameterValues("manufacturer"));
        }

        filter.setOrderField(req.getParameter("sortField"));
        filter.setOrderType(req.getParameter("sortType"));

        return filter;
    }

    private int calculatePages(int countProduct, int recordsPerPage){
        if(countProduct % recordsPerPage == 0){
            return countProduct / recordsPerPage;
        }

        return countProduct/recordsPerPage + 1;
    }

}
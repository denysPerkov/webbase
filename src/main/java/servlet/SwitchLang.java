package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/switch")
public class SwitchLang extends HttpServlet{

    private static final String PATH = "resources/jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prevUrl = req.getParameter("prevUrl");
        String url = prevUrl.substring(prevUrl.lastIndexOf("/"));
        if(url.endsWith(".jsp")){
            url = PATH +  url;
            req.getRequestDispatcher(url).forward(req, resp);
            return;
        }
       resp.sendRedirect(prevUrl);
    }
}

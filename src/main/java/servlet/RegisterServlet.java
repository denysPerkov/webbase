package servlet;

import model.User;
import service.UserService;
import util.Constant;
import util.DateUtil;
import storage.MessageContainer;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/register")
@MultipartConfig(fileSizeThreshold=1024*1024*10,
        maxFileSize=1024*1024*50,
        maxRequestSize=1024*1024*100)
public class RegisterServlet extends HttpServlet {

    private UserService userService;
    private static AtomicInteger id = new AtomicInteger();

    public static int getID(){
        return id.get();
    }

    public static int getIdandIncrement(){
        return id.getAndIncrement();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Captcha.setId(getID());
        req.setAttribute("generateID", getIdandIncrement());
        req.setAttribute("startTime", new Date().getTime());
        req.getRequestDispatcher("/resources/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String load = getServletContext().getInitParameter("loadType");
        if(!checkTimeout(req, resp)){
            invalidData(req, resp, "timeoutError");
        }else if(selectCheckType(req, resp, load)) {
            FileUploadServlet uploadServlet = new FileUploadServlet();
            uploadServlet.setUploadFilePath(getServletContext().getInitParameter("avatarsPath"));

            User user = getInputedUser(req, resp);
            req.getSession().setAttribute("user", user);
            userService.add(user);

            uploadServlet.doPost(req, resp);
            resp.sendRedirect("home");
        }else {
            invalidData(req, resp, "loginError");
        }
    }

    private void invalidData(HttpServletRequest req, HttpServletResponse resp, String error) throws ServletException, IOException{
        req.setAttribute("user", getInputedUser(req, resp));
        req.setAttribute("error", MessageContainer.getInstance().getMessage(error));
        doGet(req, resp);
    }

    private User getInputedUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));

        return user;
    }

    private boolean checkSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String captcha = req.getParameter("captcha");
        if(captcha != null && !captcha.equals(req.getSession().getAttribute("code"))){
            return false;
        }
        return true;
    }

    private boolean checkCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String captcha = req.getParameter("captcha");
        for (Cookie cookie : req.getCookies()) {
            String contextValue = (String) getServletContext().getAttribute(cookie.getValue());
            if(contextValue != null && captcha.equals(contextValue)){
                return true;
            }
        }

        return false;
    }

    private boolean checkContext(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String captcha = req.getParameter("captcha");
        String contextAtrib = (String) getServletContext().getAttribute(req.getParameter("hiddenField"));
        if(contextAtrib != null && contextAtrib.equals(captcha)){
            return true;
        }
        return false;
    }

    private boolean selectCheckType(HttpServletRequest req, HttpServletResponse resp, String type) throws ServletException, IOException{
        switch (type){
            case "session":{
                return checkSession(req, resp);
            }
            case "cookies":{
                return checkCookies(req, resp);
            }
            case "context":{
                return checkContext(req, resp);
            }
        }
        return false;
    }

    private boolean checkTimeout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        long start = Long.parseLong(req.getParameter("hiddenTime"));
        long finish = new Date().getTime();
        long range = Constant.TIMEOUT;
        if(DateUtil.checkTimeout(start, finish, range)){
            return true;
        }
        return false;
    }
}

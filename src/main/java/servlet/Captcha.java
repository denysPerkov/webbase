package servlet;

import com.github.cage.Cage;
import com.github.cage.GCage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


@WebServlet("/generate")
public class Captcha extends HttpServlet{

    private static AtomicInteger id = new AtomicInteger();
    private String load;

    public static void setId(int id){
        Captcha.id.set(id);
    }

    public int getID(){
        return id.get();
    }

    public void setLoadType(String load){
        this.load = load;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cage cage = new GCage();
        cage.getTokenGenerator().next();
        String captchValue = String.valueOf(10000 + (int)(Math.random() * ((99999 - 10000) + 1)));
        String load = getServletContext().getInitParameter("loadType");

        switch (load){
            case "session":{
                req.getSession().setAttribute("code", captchValue);
                break;
            }
            case "cookies":{
                Cookie cookie = new Cookie("userCookies", String.valueOf(getID()));
                getServletContext().setAttribute(String.valueOf(getID()), captchValue);
                cookie.setMaxAge(300);
                resp.addCookie(cookie);
                break;
            }
            case "context":{
                getServletContext().setAttribute(String.valueOf(getID()), captchValue);
                break;
            }
        }
        cage.draw(captchValue, resp.getOutputStream());
    }

}

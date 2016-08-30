package servlet;
import model.User;
import util.FIleUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/avatar")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("image/jpeg");
        String avatarsPath = getServletContext().getInitParameter("avatarsPath") + "\\";
        User user = (User) request.getSession().getAttribute("user");

        String image = FIleUtil.getImagename(user.getLogin());
        File f = new File(avatarsPath + image);

        BufferedImage bi = ImageIO.read(f);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, FIleUtil.getFormat(image), out);
        out.close();
    }
}
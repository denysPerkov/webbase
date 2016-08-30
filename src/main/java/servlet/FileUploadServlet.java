package servlet;

import model.User;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
        maxFileSize=1024*1024*50,          // 50 MB
        maxRequestSize=1024*1024*100)      // 100 MB
public class FileUploadServlet extends HttpServlet {

    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private Matcher matcher;
    private Pattern pattern;
    private String uploadFilePath;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());

        String fileName = null;

        Part partfile = request.getPart("fileName");
        fileName = getFileName(partfile);

        if(validate(fileName)){
            User user = (User) request.getSession().getAttribute("user");
            fileName = user.getLogin() + "." + getFormat(fileName);
            partfile.write(uploadFilePath + File.separator + fileName);
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return null;
    }

    private boolean validate(String image){
        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        return matcher.matches();
    }

    private String getFormat(String image){
        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        String result = null;
        while (matcher.find()){
            result = matcher.group(3);
        }

        return result;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }
}
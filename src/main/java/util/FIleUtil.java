package util;

import java.io.File;

public class FIleUtil {

    public static final String IMAGE_PATH = "D:\\images";
    private static final String FORMAT = ".(jpg|png|gif|bmp)";
    private FIleUtil(){}

    public static String getImagename(String name){
        File[] files = new File(IMAGE_PATH).listFiles();
        for(File file : files){
            if(file.isFile() && file.getName().matches(name.concat(FORMAT))){
                return file.getName();
            }
        }

        return null;
    }

    public static String getFormat(String fullname){
        return fullname.substring(fullname.lastIndexOf(".") + 1);
    }

    public static String getImage(String login){
        return IMAGE_PATH.concat(getImagename(login));
    }
}

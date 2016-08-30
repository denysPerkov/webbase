package storage;

import java.util.HashMap;
import java.util.Map;

public class AnnotationContainer {

    private static AnnotationContainer instance = new AnnotationContainer();
    private static Map<String, String> annotations;

    private AnnotationContainer(){
        annotations = new HashMap<>();
        initAnnotations();
    }

    public static AnnotationContainer getInstance(){
        return instance;
    }

    public Map<String, String> getAnnotations(){
        return annotations;
    }

    private void initAnnotations(){
        annotations.put("firstName", "[A-Za-z-']{2,25}");
        annotations.put("lastName", "[A-Za-z-']{2,25}");
        annotations.put("password", "[A-Za-z0-9_-]{5,25}");
        annotations.put("email", "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        annotations.put("login", "[A-Za-z0-9_-]{5,15}");
    }
}

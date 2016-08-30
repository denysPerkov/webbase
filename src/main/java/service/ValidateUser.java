package service;

import model.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import storage.AnnotationContainer;
import util.Getter;
import util.ValidationAnnotation;

public class ValidateUser {

    private UserService userService;

    public ValidateUser(){}

    public boolean isValidUser(User user) {
        User validUser = new User();

        if(!isUniqueLogin(user.getLogin())){
            return false;
        }

        for(Method method : user.getClass().getMethods()) {
            if (method.isAnnotationPresent(ValidationAnnotation.class)) {
                String fieldValue = null;
                try {
                    fieldValue = (String) method.invoke(user);
                    String annotName =  method.getAnnotation(ValidationAnnotation.class).mask();
                    if (!checkWithRegExp(fieldValue, annotName)){
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return  true;
    }

    private boolean checkWithRegExp(String input, String annotName){
        Pattern p = Pattern.compile(annotName);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    private boolean isUniqueLogin(String login){
        if(userService.getUser(login) == null) {
            return true;
        }

        return false;
    }
}

package storage;

import java.util.HashMap;
import java.util.Map;

public class MessageContainer {

    private static MessageContainer instance = new MessageContainer();
    private static Map<String, String> messages;

    private MessageContainer(){
        messages = new HashMap<>();
        initMessages();
    }

    public static MessageContainer getInstance(){
        return instance;
    }

    public String getMessage(String messageName){
    	return messages.get(messageName);
    }

    private void initMessages(){
        messages.put("loginError", "This login already exists");
        messages.put("timeoutError", "Check-in time has elapsed");
        messages.put("failAuth", "Invalid login or password, please try again");
        messages.put("orderAuth", "For execution of the order requires authorization");
        messages.put("emptyBasket", "Your basket is empty");
    }
}

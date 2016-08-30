package service;

import model.User;

public interface UserService {

    boolean add(User user);

    boolean login(User user);

    boolean login(String login, String pass);

    boolean remove(int id);

    boolean update(User user);

    User getUser(String login);



}

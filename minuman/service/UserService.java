package minuman.service;

import minuman.entities.User;

public interface UserService {
    boolean login(String username, String password);
    boolean register(User user);
    boolean isAdmin(String username);
    boolean isLoggedIn();
}
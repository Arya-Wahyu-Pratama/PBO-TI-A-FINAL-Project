package minuman.service;

import minuman.entities.User;
import minuman.repositories.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean register(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public boolean isAdmin(String username) {
        User user = userRepository.getUserByUsername(username);
        return user != null && "ADMIN".equals(user.getRole());
    }
}
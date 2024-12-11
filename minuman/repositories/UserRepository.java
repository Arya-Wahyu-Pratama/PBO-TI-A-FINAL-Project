package minuman.repositories;

import minuman.entities.User;

public interface UserRepository {
    User getUserByUsername(String username);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
}

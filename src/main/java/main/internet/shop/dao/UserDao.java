package main.internet.shop.dao;

import main.internet.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    boolean deleteById(Long id);

    boolean delete(User user);
}

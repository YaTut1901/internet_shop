package main.internet.shop.service;

import java.util.List;
import main.internet.shop.model.User;

public interface UserService {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User getByLogin(String login);

    User update(User user);

    boolean delete(Long id);
}

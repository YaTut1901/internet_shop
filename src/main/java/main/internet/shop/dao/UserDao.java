package main.internet.shop.dao;

import java.util.Optional;
import main.internet.shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> getByLogin(String login);
}

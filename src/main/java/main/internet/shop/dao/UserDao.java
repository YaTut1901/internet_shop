package main.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import main.internet.shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    boolean deleteById(Long id);

    boolean delete(User user);
}

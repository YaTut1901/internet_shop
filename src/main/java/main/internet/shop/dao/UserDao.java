package main.internet.shop.dao;

import main.internet.shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    boolean deleteByLogin(String login);
}

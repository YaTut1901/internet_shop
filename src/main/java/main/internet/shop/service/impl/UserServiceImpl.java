package main.internet.shop.service.impl;

import java.util.List;
import java.util.Optional;
import main.internet.shop.dao.UserDao;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.User;
import main.internet.shop.service.UserService;
import main.internet.shop.utils.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.deleteById(id);
    }

}

package main.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import main.internet.shop.dao.UserDao;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.User;
import main.internet.shop.storage.Storage;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(index -> Storage.users.get(index).getId().equals(user.getId()))
                .forEach(index -> Storage.users.set(index, user));
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public boolean deleteByLogin(String login) {
        return delete(Storage.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().get());
    }
}

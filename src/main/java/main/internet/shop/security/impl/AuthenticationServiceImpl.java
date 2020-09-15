package main.internet.shop.security.impl;

import java.util.NoSuchElementException;
import main.internet.shop.exception.AuthenticationException;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.User;
import main.internet.shop.security.AuthenticationService;
import main.internet.shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user;
        try {
            user = userService.getByLogin(login);
        } catch (NoSuchElementException e) {
            throw new AuthenticationException("There are no user with such login!");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

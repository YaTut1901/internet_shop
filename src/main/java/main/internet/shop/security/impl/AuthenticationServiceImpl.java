package main.internet.shop.security.impl;

import java.util.Optional;
import main.internet.shop.exception.AuthenticationException;
import main.internet.shop.lib.Inject;
import main.internet.shop.lib.Service;
import main.internet.shop.model.User;
import main.internet.shop.security.AuthenticationService;
import main.internet.shop.service.UserService;
import main.internet.shop.utils.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.getByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new AuthenticationException("There are no user with such login!");
        }
        User user = optionalUser.get();
        if (user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
            return user;
        }
        throw new AuthenticationException("Wrong login for that user!");
    }
}

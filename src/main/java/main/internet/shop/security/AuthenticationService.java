package main.internet.shop.security;

import main.internet.shop.exception.AuthenticationException;
import main.internet.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}

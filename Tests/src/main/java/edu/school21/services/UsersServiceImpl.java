package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.model.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    UsersRepository result;

    public UsersServiceImpl(UsersRepository res) {
        result = res;
    }
    public boolean authenticate(String login, String password) {
        if (result.findByLogin(login) == null) {
            return false;
        }
        if (result.findByLogin(login).getAuthentication()) {
            throw new AlreadyAuthenticatedException("User already authenticate");
        }
        if (!result.findByLogin(login).getPassword().equals(password)) {
            return false;
        }
        User user = result.findByLogin(login);
        user.setAuthentication(true);
        result.update(user);
        return true;
    }
}

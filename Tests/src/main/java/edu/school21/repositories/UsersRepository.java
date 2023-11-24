package edu.school21.repositories;

import edu.school21.model.User;

public interface UsersRepository {
    User findByLogin(String login);
    void update(User user);
}

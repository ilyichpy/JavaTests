package edu.school21.model;

import java.util.List;
import java.util.Objects;

public class User {
    private Long userId;
    private String login;
    private String password;
    private boolean authentication;

    public User(Long id, String login, String pass, boolean authentication) {
        this.userId = id;
        this.login = login;
        this.password = pass;
        this.authentication = authentication;
    }
    public String getName() {
        return this.login;
    }

    public Long getUserId() {return this.userId;}

    public String getPassword() {return this.password;}

    public boolean getAuthentication() {return this.authentication;}

    public void setAuthentication(boolean authentication) {this.authentication = authentication;}
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User u = (User) o;

        return this.userId == u.userId
                && this.login.equals(u.login)
                && this.password.equals(u.password)
                && this.authentication == u.authentication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, authentication);
    }

    @Override
    public String toString() {
        if (this == null) {
            return "null user";
        }
        StringBuilder res = new StringBuilder();
        res.append("Name: " + login);
        res.append(", Password: " + password);
        res.append(", Id: " + String.valueOf(userId));
        res.append(", Authentication " + authentication);
        return res.toString();
    }

}

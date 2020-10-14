package main.internet.shop.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import main.internet.shop.model.role.UserRole;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private Set<UserRole> userRoles;
    private byte[] salt;

    public User() {
        userRoles = new HashSet<>();
    }

    public User(String name, String login, String password) {
        userRoles = new HashSet<>();
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserRole.RoleName> getUserRolesNames() {
        return userRoles.stream().map(UserRole::getRoleName).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", userRoles=" + userRoles
                + '}';
    }
}

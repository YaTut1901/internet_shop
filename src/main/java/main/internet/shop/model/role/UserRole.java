package main.internet.shop.model.role;

import java.util.Objects;

public class UserRole {
    private Long id;
    private RoleName roleName;

    private UserRole(RoleName roleName) {
        this.roleName = roleName;
    }

    public static UserRole of(String role) {
        return new UserRole(RoleName.valueOf(role));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id)
                && roleName == userRole.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    public enum RoleName {
        USER,
        ADMIN;
    }
}



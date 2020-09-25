package main.internet.shop.model.role;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRole {
    private static Map<String, Long> idMap;
    private Long id;
    private RoleName roleName;

    private UserRole(RoleName roleName, Long id) {
        this.roleName = roleName;
        this.id = id;
    }

    public UserRole() {
        idMap = new HashMap<>();
        idMap.put("ADMIN", 1L);
        idMap.put("USER", 2L);
    }

    public static UserRole of(String role) {
        UserRole userRole = new UserRole();
        userRole.setRoleName(RoleName.valueOf(role));
        userRole.setId(idMap.get(role));
        return userRole;
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

    @Override
    public String toString() {
        return "UserRole{"
                + "id=" + id
                + ", roleName=" + roleName
                + '}';
    }
}



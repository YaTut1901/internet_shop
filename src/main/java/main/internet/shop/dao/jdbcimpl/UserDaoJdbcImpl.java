package main.internet.shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import main.internet.shop.dao.UserDao;
import main.internet.shop.exception.DataProcessingException;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.User;
import main.internet.shop.model.role.UserRole;
import main.internet.shop.utils.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public User create(User user) {
        String queryForUsers = "INSERT INTO users (name, login, password, salt) "
                + "VALUES (?, ?, ?, ?)";
        String queryForUsersRoles = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statementForUsers = connection.prepareStatement(queryForUsers,
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementForUsersRoles =
                    connection.prepareStatement(queryForUsersRoles);
            statementForUsers.setString(1, user.getName());
            statementForUsers.setString(2, user.getLogin());
            statementForUsers.setString(3, user.getPassword());
            statementForUsers.setBytes(4, user.getSalt());
            statementForUsers.executeUpdate();
            ResultSet resultSet = statementForUsers.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            for (UserRole role : user.getUserRoles()) {
                statementForUsersRoles.setLong(1, user.getId());
                statementForUsersRoles.setLong(2, role.getId());
                statementForUsersRoles.executeUpdate();
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create new user", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT users.id, users.name, users.login, users.salt, "
                + "users.password, roles.id, roles.name FROM users "
                + "INNER JOIN internet_shop.users_roles ON id = user_id "
                + "INNER JOIN internet_shop.roles ON role_id = roles.id "
                + "WHERE users.id = ? && deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            User user = extractUserFromResultSet(resultSet);
            do {
                UserRole role = UserRole.of(resultSet.getString("roles.name"));
                role.setId(resultSet.getLong("roles.id"));
                user.addUserRole(role);
            } while (resultSet.next());
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user with id = " + id + " from DB", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        String query = "SELECT * FROM users "
                + "WHERE users.deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get users from DB", e);
        }
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            user.setUserRoles(getRolesOfUser(user));
        }
        return allUsers;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        String query = "SELECT users.id, users.name, users.login, users.salt, "
                + "users.password, roles.id, roles.name FROM users "
                + "INNER JOIN internet_shop.users_roles ON id = user_id "
                + "INNER JOIN internet_shop.roles ON role_id = roles.id "
                + "WHERE users.login = ? && deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            User user = extractUserFromResultSet(resultSet);
            do {
                UserRole role = UserRole.of(resultSet.getString("roles.name"));
                role.setId(resultSet.getLong("roles.id"));
                user.addUserRole(role);
            } while (resultSet.next());
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user with login " + login + " from DB", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE id = ? && deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
            updateUserRoles(user.getUserRoles(), user.getId());
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update product with id = "
                    + user.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE users SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete new user", e);
        }
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setSalt(resultSet.getBytes("salt"));
        return user;
    }

    private Set<UserRole> getRolesOfUser(User user) {
        Long userId = user.getId();
        String query = "SELECT roles.id, roles.name FROM roles INNER JOIN users_roles "
                + "ON users_roles.role_id = roles.id WHERE users_roles.user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<UserRole> roles = new HashSet<>();
            while (resultSet.next()) {
                Long roleId = resultSet.getLong("roles.id");
                String roleName = resultSet.getString("roles.name");
                UserRole role = UserRole.of(roleName);
                role.setId(roleId);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get roles of user with ID = "
                    + userId, e);
        }
    }

    private boolean updateUserRoles(Set<UserRole> userRoles, Long userId) {
        String queryToDelete = "DELETE FROM users_roles WHERE user_id = ?";
        String queryToAddNewRoles = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statementToDelete =
                    connection.prepareStatement(queryToDelete);
            statementToDelete.setLong(1, userId);
            statementToDelete.executeUpdate();
            PreparedStatement statementToAddNewRoles =
                    connection.prepareStatement(queryToAddNewRoles);
            for (UserRole userRole : userRoles) {
                statementToAddNewRoles.setLong(1, userId);
                statementToAddNewRoles.setLong(2, userRole.getId());
                statementToAddNewRoles.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get roles of user with ID = "
                    + userId, e);
        }
    }

}

package main.internet.shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.internet.shop.dao.ShoppingCartDao;
import main.internet.shop.exception.DataProcessingException;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.Product;
import main.internet.shop.model.ShoppingCart;
import main.internet.shop.utils.ConnectionUtils;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return generalGet(userId, "user_id");
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create shopping cart with id = "
                    + shoppingCart.getId(), e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return generalGet(id, "id");
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        String query = "SELECT * FROM shopping_carts WHERE deleted = false";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(extractShoppingCartFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get shopping carts from DB", e);
        }
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.setProducts(getShoppingCartProducts(shoppingCart.getId()));
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete products from shopping cart with id = "
                    + shoppingCart.getId(), e);
        }
        insertProducts(shoppingCart);
        return shoppingCart;

    }

    @Override
    public boolean deleteById(Long id) {
        return generalDelete(id);
    }

    @Override
    public boolean delete(ShoppingCart cart) {
        return generalDelete(cart.getId());
    }

    private ShoppingCart extractShoppingCartFromResultSet(ResultSet resultSet)
            throws SQLException {
        ShoppingCart cart = new ShoppingCart(resultSet.getLong("user_id"));
        cart.setId(resultSet.getLong("id"));
        return cart;
    }

    private List<Product> getShoppingCartProducts(Long cartId) {
        String query = "SELECT * FROM products INNER JOIN shopping_carts_products "
                + "ON products.id = shopping_carts_products.product_id "
                + "WHERE shopping_carts_products.cart_id = ?;";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get shopping cart products with ID = "
                    + cartId, e);
        }
    }

    private ShoppingCart insertProducts(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts_products(cart_id, product_id) "
                + "VALUES(?, ?);";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, shoppingCart.getId());
            for (Product product : shoppingCart.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update shopping cart with id = "
                    + shoppingCart.getId(), e);
        }
    }

    private boolean generalDelete(Long id) {
        String queryForCart = "DELETE FROM shopping_carts WHERE id = ?";
        String queryForCartProducts = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtils.getConnection();
                PreparedStatement statementForCart = connection.prepareStatement(queryForCart);
                PreparedStatement statementForCartProducts =
                        connection.prepareStatement(queryForCartProducts)) {
            statementForCart.setLong(1, id);
            if (statementForCart.executeUpdate() == 0) {
                return false;
            }
            statementForCartProducts.setLong(1, id);
            statementForCartProducts.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete shopping cart with id = "
                    + id, e);
        }
    }

    private Optional<ShoppingCart> generalGet(Long parameter, String queryArg) {
        String query = "SELECT * FROM shopping_carts WHERE " + queryArg + " = ?;";
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            ShoppingCart shoppingCart = extractShoppingCartFromResultSet(resultSet);
            statement.close();
            shoppingCart.setProducts(getShoppingCartProducts(shoppingCart.getId()));
            return Optional.of(shoppingCart);
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get shopping cart from DB with "
                    + queryArg + " = "
                    + parameter, e);
        }
    }

}

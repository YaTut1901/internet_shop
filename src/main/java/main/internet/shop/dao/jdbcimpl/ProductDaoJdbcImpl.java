package main.internet.shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.internet.shop.dao.ProductDao;
import main.internet.shop.exception.DataProcessingException;
import main.internet.shop.lib.Dao;
import main.internet.shop.model.Product;
import main.internet.shop.utils.ConnectionUtils;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create new product", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE id = ? && deleted = false";
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get product with id = "
                    + id + " from DB", e);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products WHERE deleted = false";
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allProducts.add(extractProductFromResultSet(resultSet));
            }
            return allProducts;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get products from DB", e);
        }
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products SET name = ?, price = ? "
                + "WHERE id = ? && deleted = false";
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update product with id = "
                    + product.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE products SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete product with id = "
                    + id + " from DB", e);
        }
    }

    @Override
    public boolean delete(Product product) {
        return deleteById(product.getId());
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("name"),
                resultSet.getDouble("price"));
        product.setId(resultSet.getLong("id"));
        return product;
    }
}

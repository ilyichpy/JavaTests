package edu.school21.repositories;

import edu.school21.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    Connection connection;
    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() {
        try (PreparedStatement res = connection.prepareStatement("select * from shop.product")) {
            List<Product> productsList = new ArrayList<>();
            ResultSet resultSet = res.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                );
                productsList.add(product);
            }
            return productsList;
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (PreparedStatement res = connection.prepareStatement("select * from shop.product where product.id = " + id)) {
            ResultSet resultSet = res.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            );
            return Optional.of(product);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement res = connection.prepareStatement("update shop.product set name = ?, price = ? where id = ?")) {
            res.setString(1, product.getName());
            res.setInt(2, product.getPrice());
            res.setLong(3, product.getId());
            res.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement res = connection.prepareStatement( "insert into shop.product(NAME, PRICE, ID) values (?, ?, ?)")) {
            res.setString(1, product.getName());
            res.setInt(2, product.getPrice());
            res.setLong(3, product.getId());
            res.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement res = connection.prepareStatement("delete from shop.product where id =" + id)) {
            res.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}

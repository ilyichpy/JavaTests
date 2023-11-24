package edu.school21.repositories;

import edu.school21.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private static final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Apple", 30),
            new Product(2L, "Orange", 50),
            new Product(3L, "Chicken", 500)
    );
    private static final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L, "Chicken", 500);
    private static final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Apple", 64);
    private static final Product EXPECTED_SAVE_PRODUCT = new Product(4L, "Cocao", 230);
    private static final Long EXPECT_DELETE_PRODUCT = 1L;
    private EmbeddedDatabase database;
    private ProductsRepository productRepository;

    @BeforeEach
    public void init() throws SQLException {
        database = new EmbeddedDatabaseBuilder().
                addScript("/schema.sql").
                addScript("/data.sql").build();
        productRepository = new ProductsRepositoryJdbcImpl(database);
    }

    @AfterEach
    public void close() {
        database.shutdown();
    }

    @Test
    public void findAllProductsTest() {
        List<Product> result = productRepository.findAll();
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, result);
    }

    @Test
    public void findProductByIdTest() {
        Product result = productRepository.findById(3L).get();
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, result);
    }

    @Test
    public void updateMessageTest() {
        productRepository.update(EXPECTED_UPDATED_PRODUCT);
        Product result = productRepository.findById(1L).get();
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, result);
    }

    @Test
    public void saveMessageTest() {
        productRepository.save(EXPECTED_SAVE_PRODUCT);
        Product result = productRepository.findById(4L).get();
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, result);
    }

    @Test
    public void deleteMessageTest() {
        productRepository.delete(EXPECT_DELETE_PRODUCT);
        Assertions.assertFalse(productRepository.findById(1L).isPresent());
    }
}

package com.springframework.services;

import com.springframework.cionfig.JPAIntegrationConfig;
import com.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sbiliaiev on 02/09/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JPAIntegrationConfig.class)
public class ProductServiceDAOImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 6;
    }

    @Test
    public void testGetById() throws Exception {
        Product expected = new Product();
        expected.setId(2);
        expected.setDescription("Product 2");
        expected.setPrice(new BigDecimal("5.99"));
        expected.setImageUrl("http://example.com/product2");

        Product actual = productService.getById(expected.getId());

        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Product expected = productService.getById(2);
        expected.setDescription("Product 22");

        Product actual = productService.saveOrUpdate(expected);
        List<Product> customers = (List<Product>) productService.listAll();

        assert customers.size() == 6;

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
}

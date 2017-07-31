package com.springframework.services;

import com.springframework.domain.IDomain;
import com.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by sbiliaiev on 16/07/17.
 */
@Service
public class ProductServiceImpl extends AbstractService implements ProductService {

    protected void load() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("15.99"));
        product1.setImageURL("http://example.com/product1");

        elements.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("5.99"));
        product2.setImageURL("http://example.com/product2");

        elements.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("19.99"));
        product3.setImageURL("http://example.com/product3");

        elements.put(3, product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageURL("http://example.com/product4");

        elements.put(4, product4);

        Product product5 = new Product();
        product5.setId(5);
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("6.99"));
        product5.setImageURL("http://example.com/product5");

        elements.put(5, product5);

        Product product6 = new Product();
        product6.setId(6);
        product6.setDescription("Product 6");
        product6.setPrice(new BigDecimal("6.99"));
        product6.setImageURL("http://example.com/product5");

        elements.put(6, product6);
    }

    @Override
    public List<IDomain> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return (Product) super.saveOrUpdate(product);
    }
}

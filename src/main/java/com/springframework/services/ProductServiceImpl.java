package com.springframework.services;

import com.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by sbiliaiev on 16/07/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        loadProducts();
    }

    private void loadProducts() {
        products = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("15.99"));
        product1.setImageURL("http://example.com/product1");

        products.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("5.99"));
        product2.setImageURL("http://example.com/product2");

        products.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("19.99"));
        product3.setImageURL("http://example.com/product3");

        products.put(3, product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageURL("http://example.com/product4");

        products.put(4, product4);

        Product product5 = new Product();
        product5.setId(5);
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("6.99"));
        product5.setImageURL("http://example.com/product5");

        products.put(5, product5);

        Product product6 = new Product();
        product6.setId(6);
        product6.setDescription("Product 6");
        product6.setPrice(new BigDecimal("6.99"));
        product6.setImageURL("http://example.com/product5");

        products.put(6, product6);
    }

    @Override
    public List<Product> listAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Integer id) {
        return products.get(id);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        if(product != null) {
            if(product.getId() == null){
                product.setId(getNextId());
            }
            
            products.put(product.getId(), product);
            return product;
        } else {
            throw new IllegalArgumentException("Product can't be null.");
        }
    }

    private Integer getNextId() {
        return products.isEmpty() ? 0 : Collections.max(products.keySet()) + 1;
    }

    @Override
    public void delete(Integer id) {
        products.remove(id);
    }
}

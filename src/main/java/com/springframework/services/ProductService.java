package com.springframework.services;

import com.springframework.domain.Product;

import java.util.List;

/**
 * Created by sbiliaiev on 16/07/17.
 */
public interface ProductService {
    List<Product> listAllProducts();
}

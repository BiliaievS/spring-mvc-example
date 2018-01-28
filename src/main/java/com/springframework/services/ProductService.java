package com.springframework.services;

import com.springframework.commands.ProductForm;
import com.springframework.domain.Product;

/**
 * Created by sbiliaiev on 16/07/17.
 */
public interface ProductService extends CRUDService<Product> {
    Product saveOrUpdateProductForm(ProductForm productForm);
}

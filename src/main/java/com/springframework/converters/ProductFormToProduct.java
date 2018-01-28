package com.springframework.converters;

import com.springframework.commands.ProductForm;
import com.springframework.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sbiliaiev on 1/28/2018.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();
        product.setId(productForm.getId());
        product.setVersion(productForm.getVersion());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(productForm.getImageUrl());
        return product;
    }
}

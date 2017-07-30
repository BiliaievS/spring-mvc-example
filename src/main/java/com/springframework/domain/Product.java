package com.springframework.domain;

import java.math.BigDecimal;

/**
 * Created by sbiliaiev on 16/07/17.
 */
public class Product {

    private Integer id;
    private String description;
    private BigDecimal price;
    private String imageURL;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}

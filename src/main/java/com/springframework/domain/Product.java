package com.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sbiliaiev on 16/07/17.
 */
@Entity
public class Product extends AbstractDomain {

    private String description;
    private BigDecimal price;
    private String imageUrl;

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

    public void setImageUrl(String imageURL) {
        this.imageUrl = imageURL;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

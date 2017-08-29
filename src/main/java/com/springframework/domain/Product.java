package com.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sbiliaiev on 16/07/17.
 */
@Entity
public class Product implements IDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
        this.imageUrl = imageURL;
    }

    public String getImageURL() {
        return imageUrl;
    }
}

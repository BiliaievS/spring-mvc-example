package com.springframework.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbiliaiev on 17/09/17.
 */
@Entity
public class Cart extends AbstractDomain {

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public void addDetail(CartDetail details) {
        cartDetails.add(details);
        details.setCart(this);
    }

    public void removeDetail(CartDetail details) {
        details.setCart(null);
        cartDetails.remove(details);
    }
}

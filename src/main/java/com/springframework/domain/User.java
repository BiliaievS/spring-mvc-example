package com.springframework.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbiliaiev on 06/09/17.
 */
@Entity
public class User extends AbstractDomain {

    private String userName;

    @Transient
    private String password;
    private String encryptedPassword;
    private Boolean enabled = true;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @ManyToMany
    @JoinTable
    private List<Role> roles = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if(customer != null) {
            customer.setUser(this);
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if(!roles.contains(role)){
            roles.add(role);
        }

        if(!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void remove(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }
}

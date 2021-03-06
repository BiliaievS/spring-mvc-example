package com.springframework.domain;

import javax.persistence.*;

/**
 * Created by sbiliaiev on 23/07/17.
 */
@Entity
public class Customer extends AbstractDomain {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="address1", column = @Column(name="billingAddressLine1") ),
            @AttributeOverride(name="address2", column = @Column(name="billingAddressLine2") ),
            @AttributeOverride(name="city", column = @Column(name="billingCity") ),
            @AttributeOverride(name="state", column = @Column(name="billingState") ),
            @AttributeOverride(name="zipCode", column = @Column(name="billingZipCode") )
    } )
    private Address billingAddress = new Address();

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="address1", column = @Column(name="shippingAddressLine1") ),
            @AttributeOverride(name="address2", column = @Column(name="shippingAddressLine2") ),
            @AttributeOverride(name="city", column = @Column(name="shippingCity") ),
            @AttributeOverride(name="state", column = @Column(name="shippingState") ),
            @AttributeOverride(name="zipCode", column = @Column(name="shippingZipCode") )
    } )
    private Address shippingAddress = new Address();

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}

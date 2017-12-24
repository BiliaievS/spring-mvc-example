package com.springframework.domain;

import javax.persistence.Embeddable;

/**
 * Created by sbiliaiev on 22/10/17.
 */
@Embeddable
public class Address {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String addressLine2) {
        this.address2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
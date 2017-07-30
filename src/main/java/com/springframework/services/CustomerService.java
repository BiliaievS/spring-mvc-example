package com.springframework.services;

import com.springframework.domain.Customer;

import java.util.List;

/**
 * Created by sbiliaiev on 23/07/17.
 */
public interface CustomerService {
    List<Customer> listAll();

    Customer getById(Integer id);

    Customer saveOrUpdate(Customer object);

    void delete(Integer id);
}

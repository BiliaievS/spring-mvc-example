package com.springframework.services;

import com.springframework.commands.CustomerForm;
import com.springframework.domain.Customer;

/**
 * Created by sbiliaiev on 23/07/17.
 */
public interface CustomerService extends CRUDService<Customer> {

    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);
}

package com.springframework.converters;

import com.springframework.commands.CustomerForm;
import com.springframework.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sbiliaiev on 1/28/2018.
 */
@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

    @Override
    public CustomerForm convert(Customer customer) {
        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUserName());
        customerForm.setUserVersion(customer.getUser().getVersion());

        return customerForm;
    }
}

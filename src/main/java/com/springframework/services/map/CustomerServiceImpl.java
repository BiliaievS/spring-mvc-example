package com.springframework.services.map;

import com.springframework.commands.CustomerForm;
import com.springframework.converters.CustomerFormToCustomer;
import com.springframework.domain.Address;
import com.springframework.domain.Customer;
import com.springframework.domain.IDomain;
import com.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sbiliaiev on 23/07/17.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractService implements CustomerService {


    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    protected void load() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddress1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");

        elements.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Mark");
        customer2.setLastName("Kern");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddress1("1 Main St");
        customer2.getBillingAddress().setCity("Warsaw");
        customer2.getBillingAddress().setState("Poland");
        customer2.getBillingAddress().setZipCode("28996");
        customer2.setEmail("kern.m@gmail.com");
        customer2.setPhoneNumber("305.883.0101");

        elements.put(2, customer2);
    }

    @Override
    public List<IDomain> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Customer saveOrUpdate(Customer object) {
        return (Customer) super.saveOrUpdate(object);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer customer = customerFormToCustomer.convert(customerForm);

        if (customer.getUser().getId() != null) {
            Customer existingCustomer = getById(customer.getUser().getId());
            customer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }

        return saveOrUpdate(customer);
    }
}

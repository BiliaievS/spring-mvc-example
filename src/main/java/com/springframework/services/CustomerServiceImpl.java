package com.springframework.services;

import com.springframework.domain.Customer;
import com.springframework.domain.IDomain;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sbiliaiev on 23/07/17.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    protected void load() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setAddress1("1 Main St");
        customer1.setCity("Miami");
        customer1.setState("Florida");
        customer1.setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");

        elements.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Mark");
        customer2.setLastName("Kern");
        customer2.setAddress1("1 Main St");
        customer2.setCity("Warsaw");
        customer2.setState("Poland");
        customer2.setZipCode("28996");
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
}

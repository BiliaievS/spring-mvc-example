package com.springframework.services;

import com.springframework.cionfig.JPAIntegrationConfig;
import com.springframework.domain.Customer;
import com.springframework.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sbiliaiev on 03/09/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JPAIntegrationConfig.class)
public class CustomerServiceDAOImplTest {

    public CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 2;
    }

    @Test
    public void testGetById() throws Exception {
        Customer expected = new Customer();
        expected.setId(1);
        expected.setFirstName("Micheal");
        expected.setLastName("Weston");
        expected.setAddress1("1 Main St");
        expected.setCity("Miami");
        expected.setState("Florida");
        expected.setZipCode("33101");
        expected.setEmail("micheal@burnnotice.com");
        expected.setPhoneNumber("305.333.0101");

        Customer actual = customerService.getById(expected.getId());

        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getCity(), actual.getCity());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Customer expected = customerService.getById(1);
        expected.setFirstName("NewMicheal");
        expected.setLastName("NewWeston");
        expected.setPhoneNumber("0101");

        Customer actual = customerService.saveOrUpdate(expected);
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 3;

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void testSaveWithUser() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("Test user");
        user.setPassword("pAssword");
        customer.setUser(user);

        Customer saved = customerService.saveOrUpdate(customer);
        assert saved.getUser().getId() != null;
    }
}

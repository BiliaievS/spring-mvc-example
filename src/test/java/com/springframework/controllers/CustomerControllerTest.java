package com.springframework.controllers;

import com.springframework.domain.Address;
import com.springframework.domain.Customer;
import com.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sbiliaiev on 16/08/17.
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List customers = new ArrayList();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn(customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        Integer id = 1;
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String firstName = "Rayn";
        String lastName = "Kelly";
        String address1 = "UK, London 34";
        String zipCode = "1234";
        String email = "rayn.kelly@gmail.com";
        String phoneNumber = "911";

        Customer testCustomer = new Customer();
        testCustomer.setId(id);
        testCustomer.setFirstName(firstName);
        testCustomer.setLastName(lastName);
        testCustomer.setBillingAddress(new Address());
        testCustomer.getBillingAddress().setAddress1(address1);
        testCustomer.getBillingAddress().setZipCode(zipCode);
        testCustomer.setEmail(email);
        testCustomer.setPhoneNumber(phoneNumber);

        when(customerService.saveOrUpdate(Matchers.any())).thenReturn(testCustomer);

        mockMvc.perform(post("/customer")
                .param("id", id.toString())
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("phoneNumber", phoneNumber)
                .param("billingAddress.address1", address1)
                .param("billingAddress.zipCode", zipCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("address1", is(address1)))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("zipCode", is(zipCode)))));

        ArgumentCaptor<Customer> boundProduct = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(firstName, boundProduct.getValue().getFirstName());
        assertEquals(lastName, boundProduct.getValue().getLastName());
        assertEquals(email, boundProduct.getValue().getEmail());
        assertEquals(phoneNumber, boundProduct.getValue().getPhoneNumber());
        assertEquals("Unexpected address", address1, boundProduct.getValue().getBillingAddress().getAddress1());
        assertEquals("Unexpected zip code", zipCode, boundProduct.getValue().getBillingAddress().getZipCode());
    }
}

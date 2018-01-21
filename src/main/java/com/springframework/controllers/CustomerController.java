package com.springframework.controllers;

import com.springframework.commands.CustomerForm;
import com.springframework.converters.CustomerFormToCustomer;
import com.springframework.domain.Customer;
import com.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by sbiliaiev on 23/07/17.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @RequestMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.listAll());
        return "customer/customers";
    }

    @RequestMapping("/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));

        return "customer/customer";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getById(id);
        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setEmail(customer.getEmail());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUserName());
        customerForm.setUserVersion(customer.getUser().getVersion());

        model.addAttribute("customerForm", customerForm);
        return "customer/customerform";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/customerform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid CustomerForm customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer/customerform";
        }
        Customer savedCustomer = customerService.saveOrUpdateCustomerForm(customer);
        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}

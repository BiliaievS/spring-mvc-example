package com.springframework.services.jpa;

import com.springframework.commands.CustomerForm;
import com.springframework.converters.CustomerFormToCustomer;
import com.springframework.domain.Customer;
import com.springframework.domain.User;
import com.springframework.services.CustomerService;
import com.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by sbiliaiev on 03/09/17.
 */
@Service
@Profile("jpadao")
public class CustomerServiceDAOImpl extends AbstractDAOService implements CustomerService {

    private EncryptionService encryptionService;
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if (customer.getUser() != null && customer.getUser().getPassword() != null) {
            customer.getUser().setEncryptedPassword(
                    encryptionService.encryptString(customer.getUser().getPassword())
            );
        }

        Customer saveCustomer = em.merge(customer);
        em.getTransaction().commit();

        return saveCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        User user = customer.getUser();
        user.setCustomer(null);
        em.merge(user);
//        em.remove(customer);
        em.getTransaction().commit();
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer customer = customerFormToCustomer.convert(customerForm);

        if (customer.getUser().getId() != null) {
            Customer existingCustomer = getById(customer.getId());
            customer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }

        return saveOrUpdate(customer);
    }
}

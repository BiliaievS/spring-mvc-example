package com.springframework.services.jpa;

import com.springframework.domain.Order;
import com.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sbiliaiev on 29/10/17.
 */
@Service
@Profile("jpadao")
public class OrderServiceDAOImpl extends AbstractDAOService implements OrderService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Order savedProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}

package com.springframework.services.jpa;

import com.springframework.domain.Role;
import com.springframework.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sbiliaiev on 13/11/17.
 */
@Service
@Profile("jpadao")
public class RoleServiceDAOImpl extends AbstractDAOService implements RoleService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Role.class, id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role saved = em.merge(role);
        em.getTransaction().commit();
        return saved;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
    }
}

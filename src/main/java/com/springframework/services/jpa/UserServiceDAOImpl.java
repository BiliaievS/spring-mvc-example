package com.springframework.services.jpa;

import com.springframework.domain.User;
import com.springframework.services.UserService;
import com.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sbiliaiev on 06/09/17.
 */
@Service
@Profile("jpadao")
public class UserServiceDAOImpl extends AbstractDAOService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User object) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if(object.getPassword() != null){
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }

        User savedUser = em.merge(object);
        em.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}

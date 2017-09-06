package com.springframework.services.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by sbiliaiev on 06/09/17.
 */
public class AbstractDAOService {

    protected EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }
}

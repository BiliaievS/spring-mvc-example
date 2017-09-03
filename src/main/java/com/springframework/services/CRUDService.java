package com.springframework.services;

import com.springframework.domain.IDomain;

import java.util.List;

/**
 * Created by sbiliaiev on 30/07/17.
 */
public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T object);

    void delete(Integer id);
}

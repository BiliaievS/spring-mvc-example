package com.springframework.services;

import com.springframework.domain.IDomain;

import java.util.*;

/**
 * Created by sbiliaiev on 30/07/17.
 */
public abstract class AbstractService {

    protected Map<Integer, IDomain> elements;

    public AbstractService() {
        elements = new HashMap<>();
        load();
    }

    protected abstract void load();

    public List<IDomain> listAll() {
        return new ArrayList<>(elements.values());
    }

    public IDomain getById(Integer id) {
        return elements.get(id);
    }

    public void delete(Integer id) {
        elements.remove(id);
    }

    public IDomain saveOrUpdate(IDomain object) {
        if (object != null){
            if(object.getId() == null){
                object.setId(getNextId());
            }

            elements.put(object.getId(), object);
            return object;
        } else {
            throw new IllegalArgumentException("Domain object can't be null");
        }
    }

    private Integer getNextId() {
        return elements.isEmpty() ? 0 : Collections.max(elements.keySet())+1;
    }
}

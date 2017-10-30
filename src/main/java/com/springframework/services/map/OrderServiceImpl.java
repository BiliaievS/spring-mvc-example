package com.springframework.services.map;

import com.springframework.domain.IDomain;
import com.springframework.domain.Order;
import com.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sbiliaiev on 29/10/17.
 */
@Service
@Profile("map")
public class OrderServiceImpl extends AbstractService implements OrderService {

    @Override
    protected void load() {

    }

    public List<IDomain> listAll(){
        return super.listAll();
    }

    @Override
    public Order getById(Integer id) {
        return (Order) super.getById(id);
    }

    @Override
    public Order saveOrUpdate(Order object) {
        return (Order) super.saveOrUpdate(object);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}

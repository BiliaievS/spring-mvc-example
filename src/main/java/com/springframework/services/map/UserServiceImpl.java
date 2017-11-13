package com.springframework.services.map;

import com.springframework.domain.IDomain;
import com.springframework.domain.User;
import com.springframework.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sbiliaiev on 06/09/17.
 */
@Service
@Profile("map")
public class UserServiceImpl extends AbstractService implements UserService {

    @Override
    protected void load() {
        //NO any default user//
    }

    @Override
    public List<IDomain> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User object) {
        return (User) super.saveOrUpdate(object);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}

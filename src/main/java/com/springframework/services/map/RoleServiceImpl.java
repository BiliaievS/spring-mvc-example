package com.springframework.services.map;

import com.springframework.domain.IDomain;
import com.springframework.domain.Role;
import com.springframework.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sbiliaiev on 13/11/17.
 */
@Service
@Profile("map")
public class RoleServiceImpl extends AbstractService implements RoleService {
    @Override
    protected void load() {

    }

    @Override
    public List<IDomain> listAll() {
        return super.listAll();
    }

    @Override
    public Role getById(Integer id) {
        return (Role) super.getById(id);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Role saveOrUpdate(Role object) {
        return (Role) super.saveOrUpdate(object);
    }
}

package com.springframework.services.repo;

import com.springframework.domain.User;
import com.springframework.repositories.CustomerRepository;
import com.springframework.repositories.UserRepository;
import com.springframework.services.UserService;
import com.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private CustomerRepository customerRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User object) {
        if(object.getPassword() != null){
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }
        return userRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findOne(id);
        customerRepository.delete(user.getCustomer());
        userRepository.delete(user);
    }
}

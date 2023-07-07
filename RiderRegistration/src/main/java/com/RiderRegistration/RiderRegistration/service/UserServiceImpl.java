package com.RiderRegistration.RiderRegistration.service;

import com.RiderRegistration.RiderRegistration.entity.Role;
import com.RiderRegistration.RiderRegistration.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(User theUser) {
        entityManager.persist(theUser);
    }

    @Override
    @Transactional
    public void save(Role theRole) {
        entityManager.persist(theRole);
    }
}

package com.app.app.service;

import com.app.app.persistense.entity.RoleEntity;
import com.app.app.persistense.entity.UserEntity;
import com.app.app.persistense.entity.repository.RoleRepository;
import com.app.app.persistense.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;



    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return (List<UserEntity>) repository.findAll();
    }



    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

}

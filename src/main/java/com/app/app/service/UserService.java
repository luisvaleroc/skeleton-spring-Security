package com.app.app.service;

import com.app.app.persistense.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();


    boolean existsByUsername(String username);
}

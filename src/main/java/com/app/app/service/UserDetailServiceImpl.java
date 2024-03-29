package com.app.app.service;

import com.app.app.controllers.dto.AuthLoginRequest;
import com.app.app.controllers.dto.AuthResponse;
import com.app.app.persistense.entity.UserEntity;
import com.app.app.persistense.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserEntity userEntity =userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("error al encontrar le usuario"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add((new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
                );
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        return null;
    }
}

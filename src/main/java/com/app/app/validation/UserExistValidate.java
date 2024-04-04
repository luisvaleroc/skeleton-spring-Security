package com.app.app.validation;

import com.app.app.persistense.entity.UserEntity;
import com.app.app.persistense.entity.repository.UserRepository;
import com.app.app.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Component
public class UserExistValidate  implements ConstraintValidator<UserExist, String> {


@Autowired
    UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        if (userService == null) {
            return true;
        }

        return !userService.existsByUsername(username);
    }
}

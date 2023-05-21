package com.example.lab3nguyenhongthai.validator;

import com.example.lab3nguyenhongthai.repository.IUserRepository;
import com.example.lab3nguyenhongthai.validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private final IUserRepository userRepository;

    public ValidUsernameValidator(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository == null)
            return true;
        return userRepository.findByUsername(username) == null;
    }
}

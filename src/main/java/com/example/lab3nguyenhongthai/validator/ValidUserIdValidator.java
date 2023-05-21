package com.example.lab3nguyenhongthai.validator;

import com.example.lab3nguyenhongthai.entity.User;
import com.example.lab3nguyenhongthai.validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null)
            return true;
        return user.getId() != null;
    }
}

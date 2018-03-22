package com.pollapp.validation;

import com.pollapp.entity.UserAccount;
import com.pollapp.repository.UserAccountRepository;
import com.pollapp.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class RegisterValidation {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RegisterResponse registerResponse;

    public boolean validRegister(UserAccount userAccount, BindingResult bindingResult) {
        registerResponse.getErrors().clear();
        registerResponse.setStatus(HttpStatus.OK);
        if (isUsernameTaken(userAccount.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "Username already taken");
        }
        if (isEmailTaken(userAccount.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email already taken");
        }
        if (!isPasswordMatch(userAccount.getPassword(), userAccount.getPasswordConfirmation())) {
            bindingResult.rejectValue("password", "error.password", "Both passwords must match");
        }
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                registerResponse.getErrors().put(f.getField(), f.getDefaultMessage());
            }
            registerResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return false;
        }
        return true;
    }

    private boolean isUsernameTaken(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    private boolean isEmailTaken(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    private boolean isPasswordMatch(String password, String confirmationPassword) {
        return password.equals(confirmationPassword);
    }

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }
}

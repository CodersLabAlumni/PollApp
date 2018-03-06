package com.pollapp.service;

import com.pollapp.entity.UserAccount;
import com.pollapp.response.RegisterResponse;
import org.springframework.validation.BindingResult;

public interface AccountService {

    RegisterResponse register(UserAccount userAccount, BindingResult bindingResult);
}

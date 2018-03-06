package com.pollapp.service;

import com.pollapp.entity.UserAccount;
import com.pollapp.enums.Role;
import com.pollapp.repository.UserAccountRepository;
import com.pollapp.repository.UserRoleRepository;
import com.pollapp.response.RegisterResponse;
import com.pollapp.validation.RegisterValidation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RegisterValidation registerValidation;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public RegisterResponse register(UserAccount userAccount, BindingResult bindingResult) {
        if (registerValidation.validRegister(userAccount, bindingResult)) {
            userAccount.setPassword(BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt()));
            userAccount.setUserRole(userRoleRepository.findByRole(Role.USER));
            userAccountRepository.save(userAccount);
        }
        return registerValidation.getRegisterResponse();
    }
}

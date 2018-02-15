package com.pollapp.controller;

import com.pollapp.entity.UserAccount;
import com.pollapp.entity.UserRole;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class UserRoleController {

    @GetMapping("")
    public List<UserRole> getRoles() {
        // TODO
        return null;
    }

    @GetMapping("/{roleId}")
    public UserRole getRole(@PathVariable int roleId) {
        // TODO
        return null;
    }

    @GetMapping("/{roleId}/accounts")
    public List<UserAccount> getAccountsByRole(@PathVariable int roleId) {
        // TODO
        return null;
    }

    @GetMapping("/{roleId}/accounts/{accountId}")
    public UserAccount getAccountByRole(@PathVariable int roleId, @PathVariable int accountId) {
        // TODO
        return null;
    }

    @PostMapping("/{roleId}/accounts")
    public UserAccount createAccountByRole(@PathVariable int roleId) {
        // TODO
        return null;
    }

    @PutMapping("/{roleId}/accounts/{accountId}")
    public UserAccount updateAccountByRole(@PathVariable int roleId, @PathVariable int accountId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{roleId}/accounts/{accountId}")
    public UserAccount deleteAccountByRole(@PathVariable int roleId, @PathVariable int accountId) {
        // TODO
        return null;
    }
}

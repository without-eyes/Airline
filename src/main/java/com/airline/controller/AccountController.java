package com.airline.controller;

import com.airline.model.Account;
import com.airline.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AccountController {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public AccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login() {
        return "Login successful!";
    }
}
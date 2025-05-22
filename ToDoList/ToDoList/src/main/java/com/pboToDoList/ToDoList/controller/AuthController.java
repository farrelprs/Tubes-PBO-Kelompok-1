package com.pboToDoList.ToDoList.controller;

import com.pboToDoList.ToDoList.repository.RuserRepository;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private RuserRepository ruserRepository;

    @Autowired
    public AuthController(RuserRepository ruserRepository) {
        this.ruserRepository = ruserRepository;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody RegularUser ruser) {
        if (ruserRepository.findByEmail(ruser.getEmail()).isPresent()) {
            return "Email already registered!";
        }
        ruserRepository.save(ruser);
        return "User registered successfully.";
    }

    @PostMapping("/login")
    public String login(@RequestBody RegularUser loginData) {
        return ruserRepository.findByUsername(loginData.getUsername())
                .filter(user -> user.getPassword().equals(loginData.getPassword()))
                .map(user -> "Login successful for: " + user.getUsername())
                .orElse("Invalid username or password.");
    }
}

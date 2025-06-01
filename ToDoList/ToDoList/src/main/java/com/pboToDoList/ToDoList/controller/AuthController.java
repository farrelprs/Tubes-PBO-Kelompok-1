package com.pboToDoList.ToDoList.controller;

import com.pboToDoList.ToDoList.repository.RuserRepository;
import com.pboToDoList.ToDoList.secure.LoginRequest;
import com.pboToDoList.ToDoList.user.RegularUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private RuserRepository ruserRepository;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(RuserRepository ruserRepository) {
        this.ruserRepository = ruserRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model
    )
    {
        if (ruserRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email is already in use.");
            return "signup";
        }
        if (ruserRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username is already in use.");
            return "signup";
        }

        String hashedPassword = passwordEncoder.encode(password);
        RegularUser newUser = new RegularUser(username, hashedPassword, email);

        ruserRepository.save(newUser);
        return "redirect:/auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody RegularUser loginData) {
        return ruserRepository.findByUsername(loginData.getUsername())
                .filter(user -> user.getPassword().equals(loginData.getPassword()))
                .map(user -> "Login successful for: " + user.getUsername())
                .orElse("Invalid username or password.");
    }
}

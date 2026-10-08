package com.example.spring_security_jwt.controller;
import com.example.spring_security_jwt.entity.User;
import com.example.spring_security_jwt.repository.UserRepository;
import com.example.spring_security_jwt.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth")
public class AuthController {
    private final UserRepository repo; private final PasswordEncoder encoder;
    private final JwtService jwtService; private final AuthenticationManager authManager;
    public AuthController(UserRepository repo, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authManager){
        this.repo=repo; this.encoder=encoder; this.jwtService=jwtService; this.authManager=authManager;
    }
    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user); return "User Registered";
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return jwtService.generateToken(user.getUsername());
    }
}
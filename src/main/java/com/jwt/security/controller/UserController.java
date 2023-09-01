package com.jwt.security.controller;

import com.jwt.security.dto.AuthRequest;
import com.jwt.security.entities.Role;
import com.jwt.security.entities.User;
import com.jwt.security.repository.UserRepository;
import com.jwt.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        user.setRoles(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         this.userRepository.save(user);
        return "User added successfully!!!";
    }

    @GetMapping("/normal")
    public String handler(){
        return "this is the normal method all can access it.";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception{
      Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
       if(authentication.isAuthenticated()){
           System.out.println(authentication.isAuthenticated());
          return jwtService.generateToken(authRequest.getUsername());
       }else{
           throw new UsernameNotFoundException("Invalid Request!!!!");
       }
    }
}

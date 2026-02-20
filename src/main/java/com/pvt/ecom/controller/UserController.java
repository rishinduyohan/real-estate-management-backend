package com.pvt.ecom.controller;

import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;


    @GetMapping("/")
    public String getUser(HttpServletRequest request){
        return "User API is working! " + request.getSession().getId();
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        String response = userService.verifyUser(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser){
        User user = userService.updateUserProfile(updatedUser);
        return ResponseEntity.ok(user);
    }

}

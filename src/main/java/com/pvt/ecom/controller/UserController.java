package com.pvt.ecom.controller;

import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


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
    public String login(@RequestBody User user){
        return userService.verifyUser(user);
    }
}

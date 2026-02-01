package com.pvt.realestate.controller;

import com.pvt.realestate.model.dto.UserDTO;
import com.pvt.realestate.model.entity.User;
import com.pvt.realestate.service.UserService;
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

//    @GetMapping("/csrf-token")
//    public CsrfToken getTocken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}

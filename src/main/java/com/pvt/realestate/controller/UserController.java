package com.pvt.realestate.controller;

import com.pvt.realestate.model.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    List<User> users = new ArrayList<>( List.of(
            new User("Rishindu Yohan","rishindu@gmail.com","0721652388"),
            new User("Amal Perera","amal@gmail.com","0712345678")
    ));


    @GetMapping("/")
    public String getUser(HttpServletRequest request){
        return "User API is working! " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getTocken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return users;
    }

    @PostMapping("/user")
    public User putUser(@RequestBody User user){
        users.add(user);
        return user;
    }
}

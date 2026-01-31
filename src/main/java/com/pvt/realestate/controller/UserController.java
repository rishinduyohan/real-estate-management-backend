package com.pvt.realestate.controller;

import com.pvt.realestate.model.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    List<UserDTO> users = new ArrayList<>( List.of(
            new UserDTO("Rishindu Yohan","rishindu@gmail.com","0721652388"),
            new UserDTO("Amal Perera","amal@gmail.com","0712345678")
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
    public List<UserDTO> getUsers(){
        return users;
    }

    @PostMapping("/user")
    public UserDTO putUser(@RequestBody UserDTO user){
        users.add(user);
        return user;
    }
}

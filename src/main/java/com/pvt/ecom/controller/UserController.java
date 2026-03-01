package com.pvt.ecom.controller;

import com.pvt.ecom.model.dto.UserDTO;
import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserServiceImpl userServiceImpl;


    @GetMapping("/")
    public String getUser(HttpServletRequest request){
        return "User API is working! " + request.getSession().getId();
    }

    @GetMapping("/all")
    public List<UserDTO> getUsers(){
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userServiceImpl.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String jwtToken = userServiceImpl.verifyUser(user);

        if (jwtToken == null || jwtToken.startsWith("fail")) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }

        UserDTO loggedInUser = userServiceImpl.getUserByEmail(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwtToken);
        response.put("user", loggedInUser);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUserProfile(@RequestBody UserDTO updatedUser){
        UserDTO user = userServiceImpl.updateUserProfile(updatedUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userServiceImpl.getUserById(id);
        return ResponseEntity.ok(user);
    }

}

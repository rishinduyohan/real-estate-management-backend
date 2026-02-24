package com.pvt.ecom.service;

import com.pvt.ecom.model.dto.UserDTO;
import com.pvt.ecom.model.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<UserDTO> getAllUsers();

    String verifyUser(User user);

    UserDTO updateUserProfile(UserDTO updatedUser);

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);
}

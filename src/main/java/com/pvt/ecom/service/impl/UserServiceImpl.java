package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.dto.UserDTO;
import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.model.mapper.UserMapper;
import com.pvt.ecom.repository.UserRepository;
import com.pvt.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    @Override
    public String verifyUser(User user) {
        log.info("Trying to login user: " + user.getEmail());
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
                    );

            if(authentication.isAuthenticated())
                return jwtService.genarateToken(user.getEmail());
        } catch (Exception e) {
            log.info("Login Failed: " + e.getMessage());
            return "fail: " + e.getMessage();
        }
        return "fail";
    }
    @Override
    public UserDTO updateUserProfile(UserDTO updatedUser) {
        if (updatedUser.getId() != null){
            User existingUser = userRepository.findById(updatedUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setImageUrl(updatedUser.getImageUrl());
            return userMapper.toDTO(userRepository.save(existingUser));
        }else {
            throw new RuntimeException("User id is required");
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user=userRepository.findById(id);
        return user.map(userMapper::toDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if (user == null){
            throw new RuntimeException("User not found");
        }
        return userMapper.toDTO(user);
    }
}

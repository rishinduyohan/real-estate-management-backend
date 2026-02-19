package com.pvt.ecom.repository;

import com.pvt.ecom.model.Role;
import com.pvt.ecom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    List<User> findByRole(Role role);
}

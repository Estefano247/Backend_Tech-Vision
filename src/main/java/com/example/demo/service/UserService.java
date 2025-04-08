package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    boolean authenticateUser(String email, String password);
    Optional<User> findByEmail(String email);
    List<User> getAllUsers();
    long countUsers();
    User registerAdmin(User user);
    boolean deleteUser(Long id);
    Optional<User> updateUser(Long id, Map<String, Object> updates);
    boolean updatePassword(Long id, String currentPassword, String newPassword);
}
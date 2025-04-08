
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> updateUser(Long id, Map<String, Object> updates) {
        return userRepository.findById(id).map(user -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        user.setName((String) value);
                        break;
                    case "lastname":
                        user.setLastname((String) value);
                        break;
                    case "number":
                        user.setNumber((Integer) value);
                        break;
                    case "email":
                        user.setEmail((String) value);
                        break;
                }
            });
            return userRepository.save(user);
        });
    }

    public User registerAdmin(User user) {
        User registeredUser = registerUser(user); 
        registeredUser.setAdmin(true);
        return userRepository.save(registeredUser);
    }

    @Override
    public boolean updatePassword(Long id, String currentPassword, String newPassword) {
        return userRepository.findById(id)
                .filter(user -> passwordEncoder.matches(currentPassword, user.getPassword()))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }
}
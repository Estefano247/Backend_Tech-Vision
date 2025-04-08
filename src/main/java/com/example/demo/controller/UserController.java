package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-admin")
    public User registerAdmin(@RequestBody User user) {
    return userService.registerAdmin(user);
}
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    
    boolean isAuthenticated = userService.authenticateUser(user.getEmail(), user.getPassword());
    
    if (!isAuthenticated) {
        response.put("error", "Email o contraseña incorrectos");
        return response;
    }
    
    Optional<User> dbUserOpt = userService.findByEmail(user.getEmail());
    if (dbUserOpt.isEmpty()) {
        response.put("error", "Usuario no encontrado");
        return response;
    }
    
    User dbUser = dbUserOpt.get();
    response.put("message", "Login exitoso");
    response.put("isAdmin", dbUser.isAdmin()); // Solo envía si es admin
    response.put("user", dbUser);
    return response;
    }

    @GetMapping("/profile/{email}")
    public Map<String, Object> getUserProfile(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userOpt = userService.findByEmail(email);
        
        if (userOpt.isPresent()) {
            response.put("user", userOpt.get());
        } else {
            response.put("error", "Usuario no encontrado");
        }
        return response;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        boolean deleted = userService.deleteUser(id);
        
        if (deleted) {
            response.put("message", "Usuario eliminado");
        } else {
            response.put("error", "Usuario no encontrado");
        }
        return response;
    }

    @PatchMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Map<String, Object> response = new HashMap<>();
        updates.remove("password");
        updates.remove("isAdmin");
        
        Optional<User> updatedUser = userService.updateUser(id, updates);
        
        if (updatedUser.isPresent()) {
            response.put("user", updatedUser.get());
        } else {
            response.put("error", "Usuario no encontrado");
        }
        return response;
    }

    @GetMapping("/count")
    public Map<String, Long> countUsers() {
        return Map.of("count", userService.countUsers());
    }
    
    @PatchMapping("/{id}/password")
    public Map<String, String> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
        Map<String, String> response = new HashMap<>();
        String currentPassword = passwords.get("currentPassword");
        String newPassword = passwords.get("newPassword");
        
        if (currentPassword == null || newPassword == null) {
            response.put("error", "Se requieren currentPassword y newPassword");
            return response;
        }
        
        boolean passwordUpdated = userService.updatePassword(id, currentPassword, newPassword);
        
        if (passwordUpdated) {
            response.put("message", "Contraseña actualizada");
        } else {
            response.put("error", "Credenciales inválidas");
        }
        return response;
    }
}
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.entity.ServiceBasic;
import com.example.demo.service.ServiceBasicService;

@RestController
@RequestMapping("/api/service-basic")
public class ServiceBasicController {

    @Autowired
    private ServiceBasicService serviceBasicService;
    

    @PostMapping("/register")
    public Map<String, Object> registerService(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
    
        String name = (String) request.get("name");
        String description = (String) request.get("description");
        Object providerIdObj = request.get("providerId");
    
        if (providerIdObj == null) {
            response.put("error", "providerId es requerido");
            return response;
        }
    
        Long providerId;
        try {
            providerId = ((Number) providerIdObj).longValue();
        } catch (Exception e) {
            response.put("error", "providerId inválido");
            return response;
        }
    
        ServiceBasic service = new ServiceBasic();
        service.setName(name);
        service.setDescription(description);
    
        Optional<ServiceBasic> saved = serviceBasicService.registerServiceWithProvider(service, providerId);
    
        if (saved.isPresent()) {
            response.put("service", saved.get());
        } else {
            response.put("error", "Proveedor no encontrado");
        }
    
        return response;
    }
    
    @GetMapping("/all")
    public List<ServiceBasic> getAllServices() {
        return serviceBasicService.getAllServices();
    }

    @GetMapping("/count")
    public Map<String, Long> countServices() {
        return Map.of("count", serviceBasicService.countServices());
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteService(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = serviceBasicService.deleteService(id);

        if (deleted) {
            response.put("message", "Servicio eliminado");
        } else {
            response.put("error", "Servicio no encontrado");
        }
        return response;
    }

    @PatchMapping("/{id}")
    public Map<String, Object> updateService(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Map<String, Object> response = new HashMap<>();
        Optional<ServiceBasic> updatedService = serviceBasicService.updateService(id, updates);

        if (updatedService.isPresent()) {
            response.put("service", updatedService.get());
        } else {
            response.put("error", "Servicio no encontrado");
        }
        return response;
    }   
}
 
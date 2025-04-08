package com.example.demo.service;

import com.example.demo.entity.ServiceBasic;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceBasicService {
    Optional<ServiceBasic> registerServiceWithProvider(ServiceBasic service, Long providerId);    
    Optional<ServiceBasic> findByName(String name);
    List<ServiceBasic> getAllServices();
    boolean deleteService(Long id);
    Optional<ServiceBasic> updateService(Long id, Map<String, Object> updates);
    long countServices();
}

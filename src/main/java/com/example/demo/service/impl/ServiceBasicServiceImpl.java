package com.example.demo.service.impl;

import com.example.demo.entity.ServiceBasic;
import com.example.demo.repository.ServiceBasicRepository;
import com.example.demo.service.ServiceBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.repository.ProviderRepository;

@Service
public class ServiceBasicServiceImpl implements ServiceBasicService {

    @Autowired
    private ServiceBasicRepository serviceBasicRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Optional<ServiceBasic> registerServiceWithProvider(ServiceBasic service, Long providerId) {
        return providerRepository.findById(providerId).map(provider -> {
            service.setProvider(provider);
            return serviceBasicRepository.save(service);
        });
    }

    @Override
    public List<ServiceBasic> getAllServices() {
        return serviceBasicRepository.findAll();
    }

    @Override
    public long countServices() {
        return serviceBasicRepository.count();
    }

    @Override
    public boolean deleteService(Long id) {
        if (serviceBasicRepository.existsById(id)) {
            serviceBasicRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<ServiceBasic> updateService(Long id, Map<String, Object> updates) {
        return serviceBasicRepository.findById(id).map(serviceBasic -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        serviceBasic.setName((String) value);
                        break;
                    case "description":
                        serviceBasic.setDescription((String) value);
                        break;
                }
            });
            return serviceBasicRepository.save(serviceBasic);
        });
    }

    @Override
    public Optional<ServiceBasic> findByName(String name) {
        return serviceBasicRepository.findByName(name);
    }
}

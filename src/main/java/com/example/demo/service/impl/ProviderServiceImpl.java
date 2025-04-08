package com.example.demo.service.impl;

import com.example.demo.entity.Provider;
import com.example.demo.repository.ProviderRepository;
import com.example.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider registerProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public long countProviders() {
        return providerRepository.count();
    }

    @Override
    public boolean deleteProvider(Long ruc) {
        if (providerRepository.existsById(ruc)) {
            providerRepository.deleteById(ruc);
            return true;
        }
        return false;
    }

    @Override
    public Optional <Provider> findByRuc(Long ruc) {
        return providerRepository.findByRuc(ruc);
    }

    @Override
    public Optional<Provider> updateProvider(Long id, java.util.Map<String, Object> updates) {
        throw new UnsupportedOperationException("No implementado");
    }
}

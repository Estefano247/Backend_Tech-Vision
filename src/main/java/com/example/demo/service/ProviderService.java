package com.example.demo.service;

import java.util.Map;
import java.util.Optional;
import com.example.demo.entity.Provider;
import java.util.List;

public interface ProviderService {

    Provider registerProvider(Provider provider);
    Optional<Provider> findByRuc(Long ruc);
    List<Provider> getAllProviders();
    boolean deleteProvider(Long ruc);
    Optional<Provider> updateProvider(Long id, Map<String, Object> updates);
    long countProviders();
}

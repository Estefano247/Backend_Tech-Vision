package com.example.demo.controller;

import com.example.demo.entity.Provider;
import com.example.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // Registrar proveedor
    @PostMapping
    public Provider registrarProveedor(@RequestBody Provider provider) {
        return providerService.registerProvider(provider);
    }

    // Obtener todos los proveedores
    @GetMapping("/all")
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    // Obtener proveedor por RUC
    @GetMapping("/{ruc}")
    public ResponseEntity<Provider> getProviderByRuc(@PathVariable Long ruc) {
        return providerService.findByRuc(ruc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Contar proveedores
    @GetMapping("/count")
    public Map <String, Long> countProviders() {
        return Map.of("count", providerService.countProviders());
    }

    // Borrar Proveedor
   @DeleteMapping("/{ruc}")
    public Map<String, String> deleteProvider(@PathVariable Long ruc) {
        Map<String, String> response = new HashMap<>();
        boolean deleted = providerService.deleteProvider(ruc);
        
        if (deleted) {
            response.put("message", "Proveedor eliminado");
        } else {
            response.put("error", "Proveedor no encontrado");
        }
        return response;
    }
}

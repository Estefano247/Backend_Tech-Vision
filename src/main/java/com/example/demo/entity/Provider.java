package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Table(name = "providers") 
public class Provider {
    @Id
    private Long ruc;
    
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String direccion;
    private String telefono;

    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL,orphanRemoval = true)
    private ProviderCard cards;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<ServiceBasic> services;
}
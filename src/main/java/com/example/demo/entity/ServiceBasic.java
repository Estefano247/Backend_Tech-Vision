package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class ServiceBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "ruc", referencedColumnName = "ruc")
    @JsonBackReference
    private Provider provider;
}
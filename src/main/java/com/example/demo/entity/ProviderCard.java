package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@DiscriminatorValue("PROVIDER")
public class ProviderCard extends Card {

    @OneToOne
    @JoinColumn(name = "ruc", referencedColumnName = "ruc")
    @JsonBackReference
    private Provider provider;
}

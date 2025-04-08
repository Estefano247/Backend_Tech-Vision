package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonBackReference;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@DiscriminatorValue("USER")
public class UserCard extends Card {

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @JsonBackReference
    private User user;

}

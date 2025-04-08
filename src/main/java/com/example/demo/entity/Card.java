package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type")
@Table(name = "cards")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "card_type"
)

@JsonSubTypes({
    @JsonSubTypes.Type(value = UserCard.class, name = "USER"),
    @JsonSubTypes.Type(value = ProviderCard.class, name = "PROVIDER")
})

public abstract class Card {
    @Id
    private Long pan;
    private Double balance;
    private Timestamp timeCreation;
}

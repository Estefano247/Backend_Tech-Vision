package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idTransaccion;
    
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;
    
    private Double monto;
    private String descripcion;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "nroTarjeta", referencedColumnName = "pan")
    private Card tarjetaOrigen;
    
    @ManyToOne
    @JoinColumn(name = "nroTarjetaDestino", referencedColumnName = "pan")
    private Card tarjetaDestino;
    
    @ManyToOne
    @JoinColumn(name = "id_Servicio", referencedColumnName = "id")
    private ServiceBasic serviceBasic;
    
    @ManyToOne
    @JoinColumn(name = "ruc", referencedColumnName = "ruc")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User usuario; 

    public enum TipoTransaccion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA,
        PAGO_SERVICIO,
        RECARGA
    }
}

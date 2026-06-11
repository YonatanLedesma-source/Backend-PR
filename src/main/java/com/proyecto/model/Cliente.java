package com.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;

@Entity
@Table(name="Cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_cli;
    

    private String nombre;
    private String tel;
    @Column(unique=true)
    private Integer cedula;
    @Column(unique=true)
    private String email;
    private Float lectura;
    private String direccion;
    private Integer estado;
    private String password;

    

    @Column(name = "numero_medidor", unique = true)
    private Integer numeroMedidor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoRol rol = TipoRol.CLIENTE;
}


package com.proyecto.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Administrador")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_adm;
    
    private String nombre;
    private String tel;
    @Column(unique=true)
    private Integer cedula;
    @Column(unique=true)
    private String direccion; 
    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;
    @Column(unique=true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoRol rol = TipoRol.ADMINISTRADOR;
}

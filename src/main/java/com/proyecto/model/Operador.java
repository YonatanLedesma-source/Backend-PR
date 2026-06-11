package com.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Operador")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operador {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_oper;

    private String nombre;
    private String tel;
    @Column(unique=true)
    private Integer cedula;
    @Column(unique=true)
    private String email;
    private String password;
    private String direccion;
    @Column(name = "salario")
    private Float salario;
    @Column(name = "zona_asignada", unique = true)
    private String zonaAsignada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoRol rol = TipoRol.OPERADOR;
}

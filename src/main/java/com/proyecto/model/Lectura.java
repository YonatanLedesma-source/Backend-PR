package com.proyecto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Lectura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lectura {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_lec;

    private String zona;
    private BigDecimal valorAnterior;
    private BigDecimal valorActual;
    private BigDecimal consumoM3;
    private LocalDate fechaLectura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_med", nullable = false)
    private Medidor medidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oper", nullable = false)
    private Operador operador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adm", nullable = false)
    private Administrador administrador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presi", nullable = false)
    private Presidente presidente;
    
}
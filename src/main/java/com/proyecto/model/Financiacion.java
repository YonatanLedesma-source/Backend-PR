package com.proyecto.model;

import jakarta.persistence.Column;
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
@Table(name="Financiacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Financiacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_finan;

    private String concepto;
    @Column(name = "monto_total")
    private Float montoTotal;
    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;
    @Column(name = "cuota_mensual")
    private Float cuotaMensual;
    @Column(name = "saldo_pendiente")
    private Float saldoPendiente;
    
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
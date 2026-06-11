package com.proyecto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_fac;

    private String numero;
    private String periodo;
    private LocalDate fechaEmision;
    private Integer estado;
    private String zona;
    private BigDecimal totalCuotas;
    private BigDecimal totalPagar;
    
    private LocalDate fechaVencimiento;
    private BigDecimal valorCuota;
    private BigDecimal lecturaNueva;
    private BigDecimal lecturaAnterior;
    private BigDecimal consumo;


    @ManyToOne
    @JoinColumn(name = "id_cli")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_oper")
    private Operador operador;

    @ManyToOne
    @JoinColumn(name = "id_adm")
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "id_finan")
    private Financiacion financiacion;

    @ManyToOne
    @JoinColumn(name = "id_hiscon")
    private HistorialConsumo historialConsumo;

    @ManyToOne
    @JoinColumn(name = "id_med")
    private Medidor medidor;

    @OneToOne
    @JoinColumn(name = "id_lec")
    private Lectura lectura;
}

package com.proyecto.model;

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
@Table(name="Pagos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id_pago;

        private LocalDate fechaMonto;
        private String metodoPago;
        private Float monto;
        private Integer estado;
        private LocalDate fechaPago;
        private LocalDate fechaVencimiento;




        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_finan", nullable = true)
        private Financiacion financiacion;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_fac", nullable = true)
        private Factura factura;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_presi", nullable = true)
        private Presidente presidente;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_cli", nullable = false)
        private Cliente cliente;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_oper", nullable = true)
        private Operador operador;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_adm", nullable = true)
        private Administrador administrador;
    
    
}
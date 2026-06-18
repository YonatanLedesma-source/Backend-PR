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
@Table(name="reporte_danos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dano;

    private String descripcion;
    
    private LocalDate fechaReporte;
    
    private String estadoReparacion; // e.g., "Pendiente", "En proceso", "Reparado"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_med", nullable = false)
    private Medidor medidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oper", nullable = true) // The operator who reported it
    private Operador operador;
}

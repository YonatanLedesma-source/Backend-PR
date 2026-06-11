package com.proyecto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="HistorialConsumo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialConsumo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_hiscon;

    private String periodo;
    @Column(name = "consumo_m3")
    private BigDecimal consumoM3;
    private BigDecimal costo;
    private LocalDate fechaLectura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente cliente;
}

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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    // Campos transitorios para compatibilidad con el frontend
    @Transient
    @JsonProperty("valorLectura")
    private BigDecimal valorLectura;

    @Transient
    @JsonProperty("idMedidor")
    private Long idMedidor;

    @Transient
    @JsonProperty("idOperador")
    private Long idOperador;

    @Transient
    @JsonProperty("fechaToma")
    private LocalDate fechaToma;

    @Transient
    @JsonProperty("observaciones")
    private String observaciones;

    // Getters y setters personalizados para compatibilidad bidireccional

    @JsonProperty("valorLectura")
    public BigDecimal getValorLectura() {
        return this.valorActual != null ? this.valorActual : this.valorLectura;
    }

    @JsonProperty("valorLectura")
    public void setValorLectura(BigDecimal valorLectura) {
        this.valorLectura = valorLectura;
        if (this.valorActual == null) {
            this.valorActual = valorLectura;
        }
    }

    @JsonProperty("idMedidor")
    public Long getIdMedidor() {
        return this.medidor != null ? this.medidor.getId_med() : this.idMedidor;
    }

    @JsonProperty("idMedidor")
    public void setIdMedidor(Long idMedidor) {
        this.idMedidor = idMedidor;
    }

    @JsonProperty("idOperador")
    public Long getIdOperador() {
        return this.operador != null ? this.operador.getId_oper() : this.idOperador;
    }

    @JsonProperty("idOperador")
    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    @JsonProperty("fechaToma")
    public LocalDate getFechaToma() {
        return this.fechaLectura != null ? this.fechaLectura : this.fechaToma;
    }

    @JsonProperty("fechaToma")
    public void setFechaToma(LocalDate fechaToma) {
        this.fechaToma = fechaToma;
        if (this.fechaLectura == null) {
            this.fechaLectura = fechaToma;
        }
    }

    @JsonProperty("observaciones")
    public String getObservaciones() {
        return this.zona != null ? this.zona : this.observaciones;
    }

    @JsonProperty("observaciones")
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
        if (this.zona == null) {
            this.zona = observaciones;
        }
    }
}
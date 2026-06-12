package com.proyecto.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Financiacion;

@Repository
public interface FinanciacionRepository extends JpaRepository<Financiacion, Long> {

/* Buscar Financiacion por concepto */

Optional<Financiacion> findByConcepto(String concepto);

/* Buscar Financiacion por Monto total */

Optional<Financiacion> findByMontoTotal(Float montoTotal);

/* Buscar Financiacion por Número de cuotas */

Optional<Financiacion> findByNumeroCuotas(Integer numeroCuotas);

/* Buscar Financiacion por cuota mensual*/
Optional<Financiacion> findByCuotaMensual(Float cuotaMensual);

/* Buscar Financiacion por saldo pendiente */
Optional<Financiacion> findBySaldoPendiente(Float saldoPendiente);

@Query("SELECT f FROM Financiacion f WHERE f.cliente.id_cli = :clienteId AND f.saldoPendiente > 0 AND f.numeroCuotas > 0")
List<Financiacion> findActiveByClienteId(@Param("clienteId") Long clienteId);
}


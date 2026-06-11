package com.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
}

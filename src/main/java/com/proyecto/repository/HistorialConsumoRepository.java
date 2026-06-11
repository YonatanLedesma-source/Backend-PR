package com.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.HistorialConsumo;
import java.math.BigDecimal;
import java.time.LocalDate;


@Repository
public interface HistorialConsumoRepository extends JpaRepository<HistorialConsumo, Long> {

/* Buscar Historial de Consumo por periodo */

Optional<HistorialConsumo> findByPeriodo(String periodo);

/* Buscar Historial de Consumo por consumo m3 */

Optional<HistorialConsumo> findByConsumoM3(BigDecimal consumo_m3);

/* Buscar Historial de Consumo por costo */

Optional<HistorialConsumo> findByCosto(BigDecimal costo);

/* Buscar Historial de Consumo por fecha de lectura*/

Optional<HistorialConsumo> findByFechaLectura(LocalDate fechaLectura);

}

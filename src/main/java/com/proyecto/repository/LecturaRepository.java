package com.proyecto.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Lectura;

@Repository
public interface LecturaRepository extends JpaRepository<Lectura, Long>{

    /* Buscar Lectura por zona */

    Optional<Lectura> findByZona(String zona);

    /* Buscar Lectura por valor actual */

    Optional<Lectura> findByValorActual(BigDecimal valorActual);
    
    /* Buscar Lectura por consumo m3*/

    Optional<Lectura> findByConsumoM3(BigDecimal consumoM3);

    /* Buscar Lectura por fecha de lectura */

    Optional<Lectura> findByFechaLectura(LocalDate fechaLectura);

    /* Buscar la lectura más reciente de un medidor */
    @Query("SELECT l FROM Lectura l WHERE l.medidor.id_med = :idMed ORDER BY l.fechaLectura DESC, l.id_lec DESC")
    List<Lectura> findLatestByMedidorId(@Param("idMed") Long idMed, org.springframework.data.domain.Pageable pageable);
}

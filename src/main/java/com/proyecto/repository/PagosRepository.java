package com.proyecto.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Pagos;


@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long> {
    
    /*Buscar pagos por fecha de pago*/
    Optional<Pagos> findByFechaPago(LocalDate fechaPago);

    /* Buscar pagos por estado */
    Optional<Pagos> findByEstado(Integer estado);
}


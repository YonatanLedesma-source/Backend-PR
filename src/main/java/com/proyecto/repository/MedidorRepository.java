package com.proyecto.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Medidor;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Long>  {


    /* Buscar medidor por numero de medidor */

    Optional<Medidor> findByNumeroMedidor(Integer numeroMedidor);

    /* Buscar medidor por estado */

    Optional<Medidor> findByEstado(String estado);

    /* Buscar medidor por fecha de instalacion */

    Optional<Medidor> findByFechaInstalacion(LocalDate fechaInstalacion);

}


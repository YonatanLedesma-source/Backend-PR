package com.proyecto.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Presidente;
import com.proyecto.model.TipoRol;

@Repository
public interface PresidenteRepository extends JpaRepository<Presidente,Long>{

    /* Buscar operador por correo */

    Optional<Presidente> findByEmail(String correo);

    /* Buscar operador por nombre */

    Optional<Presidente> findByNombre(String nombre);

    /* Buscar operador por cedula */

    Optional<Presidente> findByCedula(Integer cedula);

    /* Buscar presidente por fecha de eleccion */
    Optional<Presidente> findByFechaEleccion(LocalDate fechaEleccion);

    List<Presidente> findByRol(TipoRol rol);
}
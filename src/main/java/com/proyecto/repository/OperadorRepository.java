package com.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Operador;
import com.proyecto.model.TipoRol;

@Repository
public interface OperadorRepository extends JpaRepository<Operador,Long>{

    /* Buscar operador por correo */

    Optional<Operador> findByEmail(String correo);

    /* Buscar operador por nombre */

    Optional<Operador> findByNombre(String nombre);

    /* Buscar operador por zona asignada */

    Optional<Operador> findByZonaAsignada(String zonaAsignada);

    /* Buscar operador por cedula */

    Optional<Operador> findByCedula(Integer cedula);

    List<Operador> findByRol(TipoRol rol);

}

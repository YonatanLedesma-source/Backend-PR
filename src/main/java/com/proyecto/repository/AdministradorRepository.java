package com.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Administrador;
import com.proyecto.model.TipoRol;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Long>{

    /* Buscar admin por correo */

    Optional<Administrador> findByEmail(String correo);

    /* Buscar admin por nombre */

    Optional<Administrador> findByNombre(String nombre);

    /* Buscar admin por cedula */
    Optional<Administrador> findByCedula(Integer cedula);

    List<Administrador> findByRol(TipoRol rol);
}

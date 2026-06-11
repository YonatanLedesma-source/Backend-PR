package com.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Cliente;
import com.proyecto.model.TipoRol;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    /* Buscar cliente por correo */
    Optional<Cliente> findByEmail(String correo);

    /* Buscar cliente por nombre */
    Optional<Cliente> findByNombre(String nombre);

    /* Buscar cliente por Numero Medidor */
    Optional<Cliente> findByNumeroMedidor(Integer numeroMedidor);

    /* Buscar cliente por cedula */
    Optional<Cliente> findByCedula(Integer cedula);

    List<Cliente> findByRol(TipoRol rol);








    
    



}

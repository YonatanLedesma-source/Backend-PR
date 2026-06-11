package com.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    

    /* Buscar factura por numero */
    Optional<Factura> findByNumero(String numero);


    /* Buscar factura por estado */
    List<Factura> findByEstado(Integer estado);

    /* Buscar factura por zona */
    List<Factura> findByZona(String zona);

}
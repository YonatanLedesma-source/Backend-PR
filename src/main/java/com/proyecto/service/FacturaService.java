package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Factura;
import com.proyecto.repository.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    /* Para obtener todas las facturas */
    public List<Factura> listarFacturas(){
        return facturaRepository.findAll();
    }

    /* Para obtener una factura por Id */
    public Optional<Factura> obtenerPorId(Long id){
        return facturaRepository.findById(id);
    }

    /* Para obtener una factura por numero */
    public Optional<Factura> obtenerPorNumero(String numero) {
    return facturaRepository.findByNumero(numero);
    }

    /* Para crear una nueva Factura */
    public Factura crearFactura(Factura factura){
        // Cálculo automático
        if (factura.getLecturaNueva() != null && factura.getLecturaAnterior() != null) {
            factura.setConsumo(factura.getLecturaNueva().subtract(factura.getLecturaAnterior()));
        }
        
        java.math.BigDecimal subtotal = java.math.BigDecimal.ZERO;
        if (factura.getConsumo() != null) {
            // Tarifa base ejemplo: 1500 por m3
            subtotal = factura.getConsumo().multiply(new java.math.BigDecimal("1500"));
        }
        
        if (factura.getValorCuota() != null) {
            factura.setTotalPagar(subtotal.add(factura.getValorCuota()));
        } else {
            factura.setTotalPagar(subtotal);
            factura.setValorCuota(java.math.BigDecimal.ZERO);
        }
        
        return facturaRepository.save(factura);
    }

    /* Para guardar una Factura */
    public Factura guardarFactura(Factura factura){
        return facturaRepository.save(factura);
    }

    /* Para eliminar una factura */
    public void eliminarFactura(Long id){
        facturaRepository.deleteById(id);
    }

    /* Para actualizar una factura existente */
    public Factura actualizarFactura(Factura factura){
        return facturaRepository.save(factura);
    }
}
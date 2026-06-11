package com.proyecto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Pagos;
import com.proyecto.repository.PagosRepository;

@Service
public class PagosService {
    @Autowired
    private PagosRepository pagosRepository;

    /* Para obtener todos los pagos */

    public List<Pagos> ListarPagos(){
        return pagosRepository.findAll();
    }

    /* Para obtener un pago por su Id */

    public Optional<Pagos> obtenerPorId(Long id){
        return pagosRepository.findById(id);
    }

    /* Para obtener un pago por fecha de pago */

    public Optional<Pagos> obtenerPorFechaPago(LocalDate fechaPago){
        return pagosRepository.findByFechaPago(fechaPago);
    }

    /* Para obtener un pago por su estado */

    public Optional<Pagos> obtenerPorEstado(Integer estado){
        return pagosRepository.findByEstado(estado);
    }

    /* Para crear un nuevo pago */
    
    public Pagos crearPago(Pagos pagos){
        return pagosRepository.save(pagos);
    }

    /* Para guardar un pago */

    public Pagos guardarPago(Pagos pagos){
        return pagosRepository.save(pagos);
    }

    /* para eliminar un pago */

    public void eliminarPago(Long id){
        pagosRepository.deleteById(id);
    }

    /* Para actualizar un pago existente */
    public Pagos actualizarPago(Pagos pagos){
        return pagosRepository.save(pagos);
    }
}

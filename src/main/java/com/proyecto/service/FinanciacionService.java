package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Financiacion;
import com.proyecto.repository.FinanciacionRepository;

@Service
public class FinanciacionService {
    @Autowired
    private FinanciacionRepository financiacionRepository;

    /* Para obtener todas la financiaciones */

    public List<Financiacion> listarFinanciaciones(){
        return financiacionRepository.findAll();
    }

    /* Para obtener una financiacion por concepto */

    public Optional<Financiacion> obtenerPorConcepto(String concepto){
        return financiacionRepository.findByConcepto(concepto);
    }
    
    /* Para obtener una financiacion por su Id */

    public Optional<Financiacion> obtenerPorId(Long id){
        return financiacionRepository.findById(id);
    }

    /* Para obtener una financiacion por numero de cuotas */

    public Optional<Financiacion> obtenerPorNumeroCuotas(Integer numeroCuotas){
        return financiacionRepository.findByNumeroCuotas(numeroCuotas);
    }

    /* Para crear una nueva financiacion */

    public Financiacion crearFinanciacion(Financiacion financiacion) {
        return financiacionRepository.save(financiacion);
    }

    /* Para guardar una financiacion */
    public Financiacion guardarFinanciacion(Financiacion financiacion){
        return financiacionRepository.save(financiacion);
    }

    /* Para eliminar una financiacion */
    public void eliminarFinanciacion(Long id) {
        financiacionRepository.deleteById(id);
    }

    /* Para actualizar una financiacion */
    public Financiacion actualizarFinanciacion(Financiacion financiacion) {
        return financiacionRepository.save(financiacion);
    }
}

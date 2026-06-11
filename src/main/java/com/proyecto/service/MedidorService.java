package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Medidor;
import com.proyecto.repository.MedidorRepository;

@Service
public class MedidorService {
    @Autowired
    private MedidorRepository medidorRepository;

    /* Para obtener todos los medidores */
    public List<Medidor> listarMedidores(){
        return medidorRepository.findAll();
    }

    /* Para obtener un medidor por Id */
    public Optional<Medidor> obtenerPorId(Long id){
        return medidorRepository.findById(id);
    }

    /* Para obtener un medidor por numero de medidor */
    public Optional<Medidor> obtenerPorNumeroMedidor(Integer numeroMedidor){
        return medidorRepository.findByNumeroMedidor(numeroMedidor);
    }

    /* Para obtener un medidor por estado */
    public Optional<Medidor> obtenerPorEstado(String estado){
        return medidorRepository.findByEstado(estado);
    }

    /* Para crear un nuevo medidor */
    public Medidor crearMedidor(Medidor medidor){
        return medidorRepository.save(medidor);
    }

    /* Para guardar un medidor */
    public Medidor guardarMedidor(Medidor medidor){
        return medidorRepository.save(medidor);
    }

    /* Para eliminar un medidor */
    public void eliminarMedidor(Long id){
        medidorRepository.deleteById(id);
    }

    /* Para actualizar un medidor existente */
    public Medidor actualizarMedidor(Medidor medidor){
        return medidorRepository.save(medidor);
    }
}

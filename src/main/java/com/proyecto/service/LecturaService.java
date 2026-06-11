package com.proyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Lectura;
import com.proyecto.repository.LecturaRepository;

@Service
public class LecturaService {
    @Autowired
    private LecturaRepository lecturaRepository;

    /* Para obtener todos las lecturas */
    public List<Lectura> listarLecturas(){
        return lecturaRepository.findAll();
    }

    /* Para obtener un lectura por zona */
    public Optional<Lectura> obtenerPorZona(String zona){
        return lecturaRepository.findByZona(zona);
    }

    /* Para obtener una lectura por Id */
    public Optional<Lectura> obtenerPorId(Long id){
        return lecturaRepository.findById(id);
    }

    /* Para obtener una lectura por valor actual */
    public Optional<Lectura> obtenerPorValorActual(BigDecimal valorActual){
        return lecturaRepository.findByValorActual(valorActual);
    }

    /* Para obtener una lectura por consumo m3 */
    public Optional<Lectura> obtenerPorConsumoM3(BigDecimal consumoM3){
        return lecturaRepository.findByConsumoM3(consumoM3);
    }

    /* Para obtener una lectura por fecha de lectura */
    public Optional<Lectura> obtenerPorFechaLectura(LocalDate fechaLectura){
        return lecturaRepository.findByFechaLectura(fechaLectura);
    }

    /* Para crear una nueva Lectura */
    public Lectura crearLectura(Lectura lectura){
        return lecturaRepository.save(lectura);
    }

    /* Para guardar una Lectura */
    public Lectura guardarLectura(Lectura lectura){
        return lecturaRepository.save(lectura);
    }

    /* Para eliminar una lectura */
    public void eliminarLectura(Long id){
        lecturaRepository.deleteById(id);
    }

    /* Para actualizar una lectura existente */
    public Lectura actualizarLectura(Lectura lectura){
        return lecturaRepository.save(lectura);
    }

}
package com.proyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.HistorialConsumo;
import com.proyecto.repository.HistorialConsumoRepository;

@Service
public class HistorialConsumoService {
    @Autowired
    private HistorialConsumoRepository historialConsumoRepository;

    /* Para obtener todos los registros de historial de consumo */
    public List<HistorialConsumo> listarHistorialConsumo(){
        return historialConsumoRepository.findAll();
    }

    /* Para obtener un historial de consumo por periodo  */
    public Optional<HistorialConsumo> obtenerPorPeriodo(String periodo){
        return historialConsumoRepository.findByPeriodo(periodo);
    }

    /* Para obtener un historial de consumo por Id */

    public Optional<HistorialConsumo> obtenerPorId(Long id){
        return historialConsumoRepository.findById(id);
    }

    /* Para obtener un historial de consumo por consumo M3 */

    public Optional<HistorialConsumo> obtenerPorConsumoM3(BigDecimal consumoM3){
        return historialConsumoRepository.findByConsumoM3(consumoM3);
    }

    /* Para obtener un historial de consumo por fecha de Lectura */

    public Optional<HistorialConsumo> obtenerPorFechaLectura(LocalDate fechaLectura){
        return historialConsumoRepository.findByFechaLectura(fechaLectura);
    }

    /* Para crear un nuevo historial de consumo */

    public HistorialConsumo crearHistorialConsumo(HistorialConsumo historialConsumo){
        return historialConsumoRepository.save(historialConsumo);
    }

    /* Para guardar un historial de consumo */

    public HistorialConsumo guardarHistorialConsumo(HistorialConsumo historialConsumo){
        return historialConsumoRepository.save(historialConsumo);
    }

    /* Para eliminar un historial de consumo */

    public void eliminarHistorialConsumo(Long id){
        historialConsumoRepository.deleteById(id);
    }

    /* Para actualizar un historual de consumo */

    public HistorialConsumo actualizarHistorialConsumo(HistorialConsumo historialConsumo){
        return historialConsumoRepository.save(historialConsumo);
    }
}
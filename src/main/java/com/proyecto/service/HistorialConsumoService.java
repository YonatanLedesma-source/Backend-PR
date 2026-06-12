package com.proyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.HistorialConsumo;
import com.proyecto.model.Cliente;
import com.proyecto.model.Medidor;
import com.proyecto.repository.HistorialConsumoRepository;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.MedidorRepository;

@Service
public class HistorialConsumoService {
    @Autowired
    private HistorialConsumoRepository historialConsumoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MedidorRepository medidorRepository;

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
        // Si el cliente no está asociado, intentamos resolverlo usando idMedidor
        if (historialConsumo.getCliente() == null) {
            Long idMed = historialConsumo.getIdMedidor();
            if (idMed != null) {
                // 1. Intentar buscar Cliente por ID de cliente
                Optional<Cliente> clienteOpt = clienteRepository.findById(idMed);
                if (clienteOpt.isPresent()) {
                    historialConsumo.setCliente(clienteOpt.get());
                } else {
                    // 2. Intentar buscar Cliente por número de medidor
                    clienteOpt = clienteRepository.findByNumeroMedidor(idMed.intValue());
                    if (clienteOpt.isPresent()) {
                        historialConsumo.setCliente(clienteOpt.get());
                    } else {
                        // 3. Intentar buscar Medidor por ID de medidor
                        Optional<Medidor> medidorOpt = medidorRepository.findById(idMed);
                        if (medidorOpt.isPresent()) {
                            historialConsumo.setCliente(medidorOpt.get().getCliente());
                        } else {
                            // 4. Intentar buscar Medidor por número de medidor
                            medidorOpt = medidorRepository.findByNumeroMedidor(idMed.intValue());
                            if (medidorOpt.isPresent()) {
                                historialConsumo.setCliente(medidorOpt.get().getCliente());
                            } else {
                                throw new IllegalArgumentException("No se encontró ningún cliente o medidor para el ID/número de medidor: " + idMed);
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("El cliente o el ID del medidor es obligatorio para registrar en el historial.");
            }
        }

        // Asignar fecha de lectura por defecto si es nula
        if (historialConsumo.getFechaLectura() == null) {
            historialConsumo.setFechaLectura(LocalDate.now());
        }

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
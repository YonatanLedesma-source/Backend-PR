package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.ReporteDano;
import com.proyecto.repository.ReporteDanoRepository;

@Service
public class ReporteDanoService {

    @Autowired
    private ReporteDanoRepository reporteDanoRepository;

    public List<ReporteDano> listarTodos() {
        return reporteDanoRepository.findAll();
    }

    public List<ReporteDano> listarPorMedidor(Long idMedidor) {
        return reporteDanoRepository.findByMedidorId(idMedidor);
    }

    public Optional<ReporteDano> obtenerPorId(Long id) {
        return reporteDanoRepository.findById(id);
    }

    public ReporteDano crear(ReporteDano reporte) {
        return reporteDanoRepository.save(reporte);
    }

    public ReporteDano actualizar(ReporteDano reporte) {
        return reporteDanoRepository.save(reporte);
    }

    public void eliminar(Long id) {
        reporteDanoRepository.deleteById(id);
    }
}

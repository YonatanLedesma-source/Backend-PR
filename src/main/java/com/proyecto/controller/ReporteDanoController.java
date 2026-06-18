package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.ReporteDano;
import com.proyecto.service.ReporteDanoService;

@RestController
@RequestMapping("/api/danos")
@CrossOrigin("*")
public class ReporteDanoController {

    @Autowired
    private ReporteDanoService reporteDanoService;

    @GetMapping
    public List<ReporteDano> listarTodos() {
        return reporteDanoService.listarTodos();
    }

    @GetMapping("/medidor/{idMedidor}")
    public List<ReporteDano> listarPorMedidor(@PathVariable Long idMedidor) {
        return reporteDanoService.listarPorMedidor(idMedidor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDano> obtenerPorId(@PathVariable Long id) {
        return reporteDanoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReporteDano crear(@RequestBody ReporteDano reporte) {
        return reporteDanoService.crear(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDano> actualizar(@PathVariable Long id, @RequestBody ReporteDano reporteDetails) {
        return reporteDanoService.obtenerPorId(id)
                .map(reporte -> {
                    reporte.setDescripcion(reporteDetails.getDescripcion());
                    reporte.setEstadoReparacion(reporteDetails.getEstadoReparacion());
                    reporte.setFechaReporte(reporteDetails.getFechaReporte());
                    reporte.setOperador(reporteDetails.getOperador());
                    // Keep medidor the same or update it
                    ReporteDano actualizado = reporteDanoService.actualizar(reporte);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return reporteDanoService.obtenerPorId(id)
                .map(reporte -> {
                    reporteDanoService.eliminar(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

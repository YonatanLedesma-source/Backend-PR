package com.proyecto.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.HistorialConsumo;
import com.proyecto.service.HistorialConsumoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/historialConsumo")
@CrossOrigin(origins = "*")
@Tag(name = "HistorialConsumo", description = "API para gestión de historial de consumo")
public class HistorialConsumoController {
    @Autowired
    private HistorialConsumoService historialConsumoService;

    /* GET para obtener todos los registros de historial de consumo */
    @GetMapping
    public ResponseEntity<List<HistorialConsumo>>listarHistorialConsumo(){
        List<HistorialConsumo> historialConsumos = historialConsumoService.listarHistorialConsumo();
        return ResponseEntity.ok(historialConsumos);
    }

    /* GET para obtener un historial de consumo por Id */
    @GetMapping("/{id}")
    public ResponseEntity<HistorialConsumo> obtenerPorId(@PathVariable Long id){
        return historialConsumoService.obtenerPorId(id)
        .map(historialConsumo -> ResponseEntity.ok(historialConsumo))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un historial de consumo por periodo */
    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<HistorialConsumo> obtenerPorPeriodo(@PathVariable String periodo){
        return historialConsumoService.obtenerPorPeriodo(periodo)
        .map(historialConsumo -> ResponseEntity.ok(historialConsumo))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un historial de consumo por consumo M3 */
    @GetMapping("/consumoM3/{consumoM3}")
    public ResponseEntity<HistorialConsumo> obtenerPorConsumoM3(@PathVariable BigDecimal consumoM3){
        return historialConsumoService.obtenerPorConsumoM3(consumoM3)
        .map(historialConsumo -> ResponseEntity.ok(historialConsumo))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un historial de consumo por fecha de lectura */
    @GetMapping("/fechaLectura/{fechaLectura}")
    public ResponseEntity<HistorialConsumo> obtenerPorFechaLectura(@PathVariable LocalDate fechaLectura){
        return historialConsumoService.obtenerPorFechaLectura(fechaLectura)
        .map(historialConsumo -> ResponseEntity.ok(historialConsumo))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear un nuevo historial de consumo */
    @PostMapping
    public HistorialConsumo crearHistorialConsumo(@RequestBody HistorialConsumo historialConsumo){
        return historialConsumoService.crearHistorialConsumo(historialConsumo);
    }

    /* PUT para actualizar un historial de consumo existente */
    @PutMapping("/{id}")
    public HistorialConsumo actualizarHistorialConsumo(@PathVariable Long id, @RequestBody HistorialConsumo historialConsumo) {
        historialConsumo.setId_hiscon(id);
        return historialConsumoService.guardarHistorialConsumo(historialConsumo);
    }

    /* DELETE para eliminar un historial de consumo */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorialConsumo(@PathVariable Long id) {
        historialConsumoService.eliminarHistorialConsumo(id);
        return ResponseEntity.noContent().build();
    }
}

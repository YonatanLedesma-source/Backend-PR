package com.proyecto.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Lectura;
import com.proyecto.service.LecturaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/lecturas")
@CrossOrigin(origins = "*")
@Tag(name = "Lecturas", description = "API para gestión de lecturas")
public class LecturaController {
    @Autowired
    private LecturaService lecturaService;

    /* GET para obtener todas las lecturas */
    @GetMapping
    public ResponseEntity<List<Lectura>> listarLecturas() {
        List<Lectura> lecturas = lecturaService.listarLecturas();
        return ResponseEntity.ok(lecturas);
    }

    /* GET para obtener una lectura por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Lectura> obtenerPorId(@PathVariable Long id) {
        return lecturaService.obtenerPorId(id)
        .map(lectura -> ResponseEntity.ok(lectura))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener lecturas por medidor */
    @GetMapping("/medidor/{idMedidor}")
    public ResponseEntity<List<Lectura>> obtenerPorMedidor(@PathVariable Long idMedidor) {
        List<Lectura> lecturas = lecturaService.listarLecturasPorMedidor(idMedidor);
        return ResponseEntity.ok(lecturas);
    }

    /* GET para obtener una lectura por zona */
    @GetMapping("/zona/{zona}")
    public ResponseEntity<Lectura> obtenerPorZona(@PathVariable String zona) {
        return lecturaService.obtenerPorZona(zona)
        .map(lectura -> ResponseEntity.ok(lectura))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener una lectura por valor actual */
    @GetMapping("/valorActual/{valorActual}")
    public ResponseEntity<Lectura> obtenerPorValorActual(@PathVariable BigDecimal valorActual) {
        return lecturaService.obtenerPorValorActual(valorActual)
        .map(lectura -> ResponseEntity.ok(lectura))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener una lectura por consumo m3 */
    @GetMapping("/consumoM3/{consumoM3}")
    public ResponseEntity<Lectura> obtenerPorConsumoM3(@PathVariable BigDecimal consumoM3) {
        return lecturaService.obtenerPorConsumoM3(consumoM3)
        .map(lectura -> ResponseEntity.ok(lectura))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener una lectura por fecha de lectura */
    @GetMapping("/fechaLectura/{fechaLectura}")
    public ResponseEntity<Lectura> obtenerPorFechaLectura(@PathVariable LocalDate fechaLectura) {
        return lecturaService.obtenerPorFechaLectura(fechaLectura)
        .map(lectura -> ResponseEntity.ok(lectura))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear una nueva lectura */
    @PostMapping
    public Lectura crearLectura(@RequestBody Lectura lectura) {
        return lecturaService.crearLectura(lectura);
    }

    /* PUT para actualizar una lectura existente */
    @PutMapping("/{id}")
    public Lectura actualizarLectura(@PathVariable Long id, @RequestBody Lectura lectura) {
        return lecturaService.actualizarLectura(id, lectura);
    }

    /* DELETE para eliminar una lectura */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLectura(@PathVariable Long id) {
    lecturaService.eliminarLectura(id);
    return ResponseEntity.noContent().build();
    }
}
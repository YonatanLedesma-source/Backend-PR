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

import com.proyecto.model.Medidor;
import com.proyecto.service.MedidorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/medidores")
@CrossOrigin(origins = "*")
@Tag(name = "Medidor", description = "API para gestión de medidores")
public class MedidorController {
    @Autowired
    private MedidorService medidorService;

    /* GET para obtener todos los medidores */
    @GetMapping
    public ResponseEntity<List<Medidor>> listarMedidores() {
        List<Medidor> medidores = medidorService.listarMedidores();
        return ResponseEntity.ok(medidores);
    }

    /* GET para obtener un medidor por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Medidor> obtenerPorId(@PathVariable Long id) {
        return medidorService.obtenerPorId(id)
        .map(medidor -> ResponseEntity.ok(medidor))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un medidor por número de medidor */
    @GetMapping("/numero/{numeroMedidor}")
    public ResponseEntity<Medidor> obtenerPorNumeroMedidor(@PathVariable Integer numeroMedidor) {
        return medidorService.obtenerPorNumeroMedidor(numeroMedidor)
        .map(medidor -> ResponseEntity.ok(medidor))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear un nuevo medidor */
    @PostMapping
    public Medidor crearMedidor(@RequestBody Medidor medidor) {
        return medidorService.crearMedidor(medidor);
    }

    /* PUT para actualizar un medidor existente */
    @PutMapping("/{id}")
    public Medidor actualizarMedidor(@PathVariable Long id, @RequestBody Medidor medidor) {
        medidor.setId_med(id);
        return medidorService.guardarMedidor(medidor);
    }

    /* DELETE para eliminar un medidor */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedidor(@PathVariable Long id) {
        medidorService.eliminarMedidor(id);
        return ResponseEntity.noContent().build();
    }

    
}
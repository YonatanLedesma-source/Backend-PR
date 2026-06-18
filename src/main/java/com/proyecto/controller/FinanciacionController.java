package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Financiacion;
import com.proyecto.service.FinanciacionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/financiaciones")
@CrossOrigin(origins = "*")
@Tag(name = "Financiaciones", description = "API para gestión de financiaciones")
public class FinanciacionController {
    @Autowired
    private FinanciacionService financiacionService;

    /* GET para obtener todas las financiaciones */
    @GetMapping
    public ResponseEntity<List<Financiacion>>listarFinanciaciones(){
        List<Financiacion> financiaciones = financiacionService.listarFinanciaciones();
        return ResponseEntity.ok(financiaciones);
    }

    /* GET para obtener una financiación por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Financiacion> obtenerPorId(@PathVariable Long id){
        return financiacionService.obtenerPorId(id)
        .map(financiacion -> ResponseEntity.ok(financiacion))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener financiacion por concepto */
    @GetMapping("/concepto/{concepto}")
    public ResponseEntity<Financiacion> obtenerPorConcepto(@PathVariable String concepto) {
        return financiacionService.obtenerPorConcepto(concepto)
        .map(financiacion -> ResponseEntity.ok(financiacion))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener una financiacion por número de cuotas */
    @GetMapping("/numero/{numero_cuotas}")
    public ResponseEntity<Financiacion> obtenerPorNumeroCuotas(@PathVariable Integer numero_cuotas) {
        return financiacionService.obtenerPorNumeroCuotas(numero_cuotas)
        .map(financiacion -> ResponseEntity.ok(financiacion))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear una nueva financiacion */
    @PostMapping
    public Financiacion crearFinanciacion(@RequestBody Financiacion financiacion) {
        return financiacionService.crearFinanciacion(financiacion);
    }

    /* PUT para actualizar una financiacion existente */
    @PutMapping("/{id}")
    public Financiacion actualizarFinanciacion(@PathVariable Long id, @RequestBody Financiacion financiacion) {
        return financiacionService.actualizarFinanciacion(id, financiacion);
    }

    /* DELETE para eliminar una financiacion */
    @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarFinanciacion(@PathVariable Long id) {
            financiacionService.eliminarFinanciacion(id);
            return ResponseEntity.noContent().build();
    }
}
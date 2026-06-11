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

import com.proyecto.model.Factura;
import com.proyecto.service.FacturaService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
@Tag(name = "Facturas", description = "API para gestión de facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

/* GET para listar todas las facturas */
@GetMapping
public ResponseEntity<List<Factura>> listarFacturas() {
    List<Factura> facturas = facturaService.listarFacturas();
    return ResponseEntity.ok(facturas);
}

/* GET para obtener una factura por su Id */
@GetMapping("/{id}")
public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id){
    return facturaService.obtenerPorId(id)
    .map(factura -> ResponseEntity.ok(factura))
    .orElse(ResponseEntity.notFound().build());
}

/* GET para obtener factura por su numero */
/* Endpoint para buscar una factura única por su número */
@GetMapping("/numero/{numero}")
public ResponseEntity<Factura> obtenerPorNumero(@PathVariable String numero) {
    return facturaService.obtenerPorNumero(numero)
        .map(factura -> ResponseEntity.ok(factura)) // Si existe, devuelve 200 OK con la factura
        .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
}

/* POST para crear una nueva factura */
@PostMapping
public Factura crearFactura(@RequestBody Factura factura) {
    return facturaService.crearFactura(factura);
}

/* PUT para actualizar una factura existente */
@PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable Long id, @RequestBody Factura factura) {
        factura.setId_fac(id);
        return facturaService.guardarFactura(factura);
}

/* DELETE para eliminar una factura */
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
    facturaService.eliminarFactura(id);
    return ResponseEntity.noContent().build();
}
}

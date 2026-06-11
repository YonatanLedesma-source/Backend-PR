package com.proyecto.controller;

import java.time.LocalDate;
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

import com.proyecto.model.Pagos;
import com.proyecto.service.PagosService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
@Tag(name = "Pagos", description = "API para gestión de pagos")
public class PagosController {
    @Autowired
    private PagosService pagosService;

    /* GET para obtener todos los pagos */
    @GetMapping
    public ResponseEntity<List<Pagos>> ListarPagos(){
        List<Pagos> pagos = pagosService.ListarPagos();
        return ResponseEntity.ok(pagos);
    }

    /* GET para obtener un pago por su Id */
    @GetMapping("/{id}")
    public ResponseEntity<Pagos> obtenerPorId(@PathVariable Long id){
        return pagosService.obtenerPorId(id)
        .map(pagos -> ResponseEntity.ok(pagos))
        .orElse(ResponseEntity.notFound().build());
    } 

    /* GET para obtener un pago por fecha de pago */
    @GetMapping("/fechaPago/{fechaPago}")
    public ResponseEntity<Pagos> obtenerPorFechaPago(@PathVariable LocalDate fechaPago){
        return pagosService.obtenerPorFechaPago(fechaPago)
        .map(pagos -> ResponseEntity.ok(pagos))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un pago por su estado */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Pagos> obtenerPorEstado(@PathVariable Integer estado){
        return pagosService.obtenerPorEstado(estado)
        .map(pagos -> ResponseEntity.ok(pagos))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear un nuevo pago */
    @PostMapping
    public Pagos crearPago(@RequestBody Pagos pagos) {
        return pagosService.crearPago(pagos);
    }

    /* PUT para actualizar un pago existente */
    @PutMapping("/{id}")
    public Pagos actualizarPago(@PathVariable Long id, @RequestBody Pagos pagos) {
        return pagosService.guardarPago(pagos);
    }

    /* DELETE para eliminar un pago */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagosService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.proyecto.model.Operador;
import com.proyecto.model.TipoRol;
import com.proyecto.service.OperadorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/operadores")
@CrossOrigin(origins = "*")
@Tag(name = "Operadores", description = "API para gestión de operadores")
public class OperadorController {
    @Autowired
    private OperadorService operadorService;

    /* GET para obtener todos los operadores */
    @GetMapping
    public ResponseEntity<List<Operador>>listarOperadores(){
        List<Operador> operadores = operadorService.listarOperadores();
        return ResponseEntity.ok(operadores);
    }

    /*GET para obtener operador por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Operador> ObtenerPorId(@PathVariable Long id){
        return operadorService.obtenerPorId(id)
        .map(operador -> ResponseEntity.ok(operador))
        .orElse(ResponseEntity.notFound().build());        
    }

    /* GET para obtener operador por correo */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Operador> obtenerPorCorreo(@PathVariable String correo){
        return operadorService.obtenerPorCorreo(correo)
        .map(operador -> ResponseEntity.ok(operador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener operador por cedula */
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Operador> ObtenerPorCedula(@PathVariable Integer cedula){
        return operadorService.obtenerPorCedula(cedula)
        .map(operador -> ResponseEntity.ok(operador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un cliente por su rol */
        @GetMapping("/rol/{rol}")
        public List<Operador> obtenerPorRol(@PathVariable TipoRol rol) {
        return operadorService.obtenerPorRol(rol);
}
    
    /* GET para obtener operador por zona asignada */
    @GetMapping("/zona/{zonaAsignada}")
    public ResponseEntity<Operador> obtenerPorZonaAsignada(@PathVariable String zonaAsignada){
        return operadorService.obtenerPorZonaAsignada(zonaAsignada)
        .map(operador -> ResponseEntity.ok(operador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* POST para crear un nuevo operador */
    @PostMapping
    public Operador crearOperador(@RequestBody Operador operador) {
        return operadorService.registrarOperadorNuevo(operador);
    }

    /* PUT para actualizar un operador */
    @PutMapping("/{id}")
    public Operador actualizarOperador(@PathVariable Long id, @RequestBody Operador operador) {
        operador.setId_oper(id);
        return operadorService.guardarOperador(operador);
    }

    /* DELETE para eliminar un operador */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOperador(@PathVariable Long id) {
        operadorService.eliminarOperador(id);
        return ResponseEntity.noContent().build();
    }
    
    
}   
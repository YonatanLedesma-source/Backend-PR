package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Presidente;
import com.proyecto.model.TipoRol;
import com.proyecto.service.PresidenteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/presidentes")
@CrossOrigin(origins = "*")
@Tag(name = "Presidentes", description = "API para gestión de presidentes")   
public class PresidenteController {
    @Autowired
    private PresidenteService presidenteService;


    /* GET para listar todos los presidentes */
    @GetMapping
    public ResponseEntity<List<Presidente>>listarPresidentes() {
        List<Presidente> presidentes = presidenteService.listarPresidentes();
        return ResponseEntity.ok(presidentes);
    }

    /* GET para obtener presidente por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Presidente> obtenerPorId(@PathVariable Long id){
        return presidenteService.obtenerPorId(id)
        .map(presidente -> ResponseEntity.ok(presidente))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener presidente por correo */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Presidente> obtenerPorCorreo(@PathVariable String correo){
        return presidenteService.obtenerPorCorreo(correo)
        .map(presidente -> ResponseEntity.ok(presidente))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener presidente por cedula */
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Presidente> obtenerPorCedula(@PathVariable Integer cedula){
        return presidenteService.obtenerPorCedula(cedula)
        .map(presidente -> ResponseEntity.ok(presidente))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un presidente por su rol */
        @GetMapping("/rol/{rol}")
        public List<Presidente> obtenerPorRol(@PathVariable TipoRol rol) {
        return presidenteService.obtenerPorRol(rol);
}

    /* POST para crear un nuevo presidente */
    @PostMapping
    public Presidente crearPresidente(@RequestBody Presidente presidente) {
        return presidenteService.registrarPresidenteNuevo(presidente);
    }

    /* PUT para actualizar presidente */
    @PutMapping("/{id}")
    public Presidente actualizarPresidente(@PathVariable Long id, @RequestBody Presidente presidente) {
        presidente.setId_presi(id);
        return presidenteService.guardarPresidente(presidente);
    }

    /* DELETE para eliminar presidente */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPresidente(@PathVariable Long id) {
        presidenteService.eliminarPresidente(id);
        return ResponseEntity.noContent().build();
    }
    
}
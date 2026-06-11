package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.proyecto.model.Administrador;
import com.proyecto.model.TipoRol;
import com.proyecto.service.AdministradorService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
@Tag(name = "Administradores", description = "API para gestión de administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    /* GET para obtener todos los administradores */
    @GetMapping
    public ResponseEntity<List<Administrador>>listarAdministradores(){
        List<Administrador> administradores = administradorService.listarAdministradores();
        return ResponseEntity.ok(administradores);
    }

    /* GET para obtener administrador por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerPorId(@PathVariable Long id){
        return administradorService.obtenerPorId(id)
        .map(administrador -> ResponseEntity.ok(administrador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener administrador por Correo */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Administrador> obtenerPorCorreo(@PathVariable String correo){
        return administradorService.obtenerPorCorreo(correo)
        .map(administrador -> ResponseEntity.ok(administrador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener administrador por cedula */
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Administrador> obtenerPorCedula(@PathVariable Integer cedula){
        return administradorService.obtenerPorCedula(cedula)
        .map(administrador -> ResponseEntity.ok(administrador))
        .orElse(ResponseEntity.notFound().build());
    }

    /* GET para obtener un administrador por su rol */
        @GetMapping("/rol/{rol}")
        public List<Administrador> obtenerPorRol(@PathVariable TipoRol rol) {
        return administradorService.obtenerPorRol(rol);
}

    /* POST para crear administrador */
    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return administradorService.registrarAdministradorNuevo(administrador);
    }

    /* PUT para actualizar administrador */
    @PutMapping("/{id}")
    public Administrador actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId_adm(id);
        return administradorService.guardarAdministrador(administrador);
    }

    /* DELETE para eliminar administrador por Id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return ResponseEntity.noContent().build();
    }
    
}

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

import com.proyecto.model.Cliente;
import com.proyecto.model.TipoRol;
import com.proyecto.service.ClienteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@Tag(name = "Clientes", description = "API para gestión de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    /* GET para obtener todo los usuarios */
    @GetMapping
    public ResponseEntity<List<Cliente>>listarClientes(){
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    /* GET para obtener cliente por Id */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id){
        return clienteService.obtenerPorId(id)
        .map(cliente -> ResponseEntity.ok(cliente))
        .orElse(ResponseEntity.notFound().build());
    }
    
    /* GET para obtener cliente por Correo */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Cliente> obtenerPorCorreo(@PathVariable String correo){
        return clienteService.obtenerPorCorreo(correo)
        .map(cliente -> ResponseEntity.ok(cliente))
        .orElse(ResponseEntity.notFound().build());
    }

        /* GET para obtener cliente por cedula */
        @GetMapping("/cedula/{cedula}")
        public ResponseEntity<Cliente> obtenerPorCedula(@PathVariable Integer cedula){
            return clienteService.obtenerPorCedula(cedula)
            .map(cliente -> ResponseEntity.ok(cliente))
            .orElse(ResponseEntity.notFound().build());
        }

        /* GET para obtener un cliente por su rol */
        @GetMapping("/rol/{rol}")
        public List<Cliente> obtenerPorRol(@PathVariable TipoRol rol) {
        return clienteService.obtenerPorRol(rol);
}

    /* POST para crear cliente  */
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.registrarClienteNuevo(cliente);
    }

    /* PUT para actualizar cliente */
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId_cli(id);
        return clienteService.guardarCliente(cliente);
    }

    /* DELETE para eliminar cliente por Id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
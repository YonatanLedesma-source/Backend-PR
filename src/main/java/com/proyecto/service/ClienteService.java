package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.model.Cliente;
import com.proyecto.model.TipoRol;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Para obtener todos los clientes */

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    /* Para obtener cliente por Id */

    public Optional<Cliente> obtenerPorId(Long id){
        return clienteRepository.findById(id);
    }

    /* Para obtener cliente por correo */

    public Optional<Cliente> obtenerPorCorreo(String email){
        return clienteRepository.findByEmail(email);
    }

    /* Para obtener cliente por cedula */

    public Optional<Cliente> obtenerPorCedula(Integer cedula){
        return clienteRepository.findByCedula(cedula);
    }

    public Cliente registrarClienteNuevo(Cliente cliente) {
        // Cifrar contraseña con BCrypt antes de guardar
        if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
            cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        }
        // Asignar el rol directamente usando el Enum de texto
        cliente.setRol(TipoRol.CLIENTE);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerPorRol(TipoRol rol){
        return clienteRepository.findByRol(rol);
}
    
    /* Para crear un nuevo cliente del acueducto */

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    /* Para eliminar un cliente por Id */

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    /* Para actualizar un cliente */

    public Cliente actualizarCliente(Cliente cliente) {
    /* Si el 'cliente' trae su ID, JPA buscará ese ID y actualizará los campos */
        return clienteRepository.save(cliente);
    }
}

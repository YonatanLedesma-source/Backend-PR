package com.proyecto.service;

import com.proyecto.model.Administrador;
import com.proyecto.model.TipoRol;
import com.proyecto.repository.AdministradorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Para obtener todos los administradores */

    public List<Administrador> listarAdministradores(){
        return administradorRepository.findAll();
    }

        /* Para obtener administrador por Id */

    public Optional<Administrador> obtenerPorId(Long id){
        return administradorRepository.findById(id);
    }

    /* Para obtener administrador por correo */

    public Optional<Administrador> obtenerPorCorreo(String email){
        return administradorRepository.findByEmail(email);
    }

    /* Para obtener administrador por cedula */

    public Optional<Administrador> obtenerPorCedula(Integer cedula){
        return administradorRepository.findByCedula(cedula);
    }

    public Administrador registrarAdministradorNuevo(Administrador administrador) {
        // Cifrar contraseña con BCrypt antes de guardar
        if (administrador.getPassword() != null && !administrador.getPassword().isEmpty()) {
            administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        }
        // Asignar el rol directamente usando el Enum de texto
        administrador.setRol(TipoRol.ADMINISTRADOR);
        return administradorRepository.save(administrador);
    }

    public List<Administrador> obtenerPorRol(TipoRol rol){
        return administradorRepository.findByRol(rol);
}

    /* Para crear un nuevo administrador del acueducto */

    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /* Para guardar un administrador existente */

    public Administrador guardarAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }

    /* Para eliminar un administrador por Id */

    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }

    /* Para actualizar un administrador */

    public Administrador actualizarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }
}

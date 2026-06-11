package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.model.Presidente;
import com.proyecto.model.TipoRol;
import com.proyecto.repository.PresidenteRepository;

@Service
public class PresidenteService {
    @Autowired
    private PresidenteRepository presidenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Para obtener presidente por Id */

    public Optional<Presidente> obtenerPorId(Long id){
        return presidenteRepository.findById(id);
    }

    /* Para obtener presidente por correo */

    public Optional<Presidente> obtenerPorCorreo(String email){
        return presidenteRepository.findByEmail(email);
    }

    /* Para obtener presidente por cedula */

    public Optional<Presidente> obtenerPorCedula(Integer cedula){
        return presidenteRepository.findByCedula(cedula);
    }

    /* Para crear un nuevo presidente */

    public Presidente crearPresidente(Presidente presidente) {
        return presidenteRepository.save(presidente);
    }

    /* Para guardar un presidente */

    public Presidente guardarPresidente(Presidente presidente){
        return presidenteRepository.save(presidente);
    }

    public Presidente registrarPresidenteNuevo(Presidente presidente) {
        // Cifrar contraseña con BCrypt antes de guardar
        if (presidente.getPassword() != null && !presidente.getPassword().isEmpty()) {
            presidente.setPassword(passwordEncoder.encode(presidente.getPassword()));
        }
        // Asignar el rol directamente usando el Enum de texto
        presidente.setRol(TipoRol.PRESIDENTE);
        return presidenteRepository.save(presidente);
    }

    public List<Presidente> obtenerPorRol(TipoRol rol){
        return presidenteRepository.findByRol(rol);
}

    /* Para eliminar un presidente */

    public void eliminarPresidente(Long id){
        presidenteRepository.deleteById(id);
    }

    /* Para actualizar un presidente */

    public Presidente actualizarPresidente(Presidente presidente){
        return presidenteRepository.save(presidente);
    }

    public List<Presidente> listarPresidentes() {
        throw new UnsupportedOperationException("Unimplemented method 'listarPresidentes'");
    }
}
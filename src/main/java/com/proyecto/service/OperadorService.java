package com.proyecto.service;

import com.proyecto.model.Operador;
import com.proyecto.model.TipoRol;
import com.proyecto.repository.OperadorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OperadorService {
    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Para obtener todos los operadores */

    public List<Operador> listarOperadores(){
        return operadorRepository.findAll();
    }

        /* Para obtener operador por Id */

    public Optional<Operador> obtenerPorId(Long id){
        return operadorRepository.findById(id);
    }

    /* Para obtener operador por correo */

    public Optional<Operador> obtenerPorCorreo(String email){
        return operadorRepository.findByEmail(email);
    }

    /* Para obtener operador por zona asignada */

    public Optional<Operador> obtenerPorZonaAsignada(String zonaAsignada){
        return operadorRepository.findByZonaAsignada(zonaAsignada);
    }

    /* Para obtener operador por cedula */

    public Optional<Operador> obtenerPorCedula(Integer cedula){
        return operadorRepository.findByCedula(cedula);
    }

    public Operador registrarOperadorNuevo(Operador operador) {
        // Cifrar contraseña con BCrypt antes de guardar
        if (operador.getPassword() != null && !operador.getPassword().isEmpty()) {
            operador.setPassword(passwordEncoder.encode(operador.getPassword()));
        }
        // Asignar el rol directamente usando el Enum de texto
        operador.setRol(TipoRol.OPERADOR);
        return operadorRepository.save(operador);
    }

    public List<Operador> obtenerPorRol(TipoRol rol){
        return operadorRepository.findByRol(rol);
}

    /* Para crear un nuevo operador del acueducto */

    public Operador crearOperador(Operador operador) {
        return operadorRepository.save(operador);
    }

    /* Para guardar un operador existente */

    public Operador guardarOperador(Operador operador){
        return operadorRepository.save(operador);
    }

    /* Para eliminar un operador por Id */

    public void eliminarOperador(Long id) {
        operadorRepository.deleteById(id);
    }

    /* Para actualizar un operador */

    public Operador actualizarOperador(Operador operador) {
        return operadorRepository.save(operador);
    }
}